package mipalomita.gui;

import mipalomita.gestion.Inventario;
import mipalomita.modelo.Producto;
import mipalomita.modelo.ProductoNoPerecible;
import mipalomita.modelo.ProductoPerecible;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PanelProductos extends JPanel {
    private Inventario inventario;

    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField txtBuscar;

    private JTextField txtCodigo, txtNombre, txtPrecio, txtStock, txtStockMin, txtFecha;
    private JComboBox<String> cmbTipo;

    public PanelProductos(Inventario inventario) {
        this.inventario = inventario;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(crearPanelBusqueda(), BorderLayout.NORTH);
        add(crearTabla(), BorderLayout.CENTER);
        add(crearFormulario(), BorderLayout.SOUTH);

        actualizarTabla();
    }

    // ---------- Construcción de la ventana ----------

    private JPanel crearPanelBusqueda() {
        txtBuscar = new JTextField(12);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscar());

        JButton btnTodos = new JButton("Mostrar todos");
        btnTodos.addActionListener(e -> {
            txtBuscar.setText("");
            actualizarTabla();
        });

        JButton btnReponer = new JButton("Reponer stock");
        btnReponer.addActionListener(e -> reponerStock());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Código:"));
        panel.add(txtBuscar);
        panel.add(btnBuscar);
        panel.add(btnTodos);
        panel.add(btnReponer);
        return panel;
    }

    private JScrollPane crearTabla() {
        String[] columnas = {"Código", "Nombre", "Tipo", "Precio (S/)",
                             "Stock", "Stock mín.", "Vence", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;  // la tabla solo muestra, no se edita
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(24);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return new JScrollPane(tabla);
    }

    private JPanel crearFormulario() {
        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();
        txtStockMin = new JTextField();
        txtFecha = new JTextField();
        txtFecha.setEnabled(false);  // solo se usa si el producto es perecible

        cmbTipo = new JComboBox<>(new String[]{"No perecible", "Perecible"});
        cmbTipo.addActionListener(e -> txtFecha.setEnabled(cmbTipo.getSelectedIndex() == 1));

        JPanel campos = new JPanel(new GridLayout(0, 4, 8, 8));
        campos.add(new JLabel("Código:"));         campos.add(txtCodigo);
        campos.add(new JLabel("Nombre:"));         campos.add(txtNombre);
        campos.add(new JLabel("Precio (S/):"));    campos.add(txtPrecio);
        campos.add(new JLabel("Stock inicial:"));  campos.add(txtStock);
        campos.add(new JLabel("Stock mínimo:"));   campos.add(txtStockMin);
        campos.add(new JLabel("Tipo:"));           campos.add(cmbTipo);
        campos.add(new JLabel("Vence (aaaa-mm-dd):")); campos.add(txtFecha);

        JButton btnAgregar = new JButton("Agregar producto");
        btnAgregar.addActionListener(e -> agregar());
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.add(btnLimpiar);
        botones.add(btnAgregar);

        JPanel formulario = new JPanel(new BorderLayout(5, 5));
        formulario.setBorder(BorderFactory.createTitledBorder("Agregar producto"));
        formulario.add(campos, BorderLayout.CENTER);
        formulario.add(botones, BorderLayout.SOUTH);
        return formulario;
    }

    // ---------- Acciones ----------

    // Público: la ventana principal puede llamarlo al cambiar de pestaña
    public void actualizarTabla() {
        cargarTabla(inventario.ordenarPorNombre());
    }

    private void cargarTabla(Producto[] lista) {
        modeloTabla.setRowCount(0);
        for (Producto p : lista) {
            boolean perecible = p instanceof ProductoPerecible;
            modeloTabla.addRow(new Object[]{
                p.getCodigoProducto(),
                p.getNombre(),
                perecible ? "Perecible" : "No perecible",
                String.format("%.2f", p.getPrecio()),
                p.getStock(),
                p.getStockMinimo(),
                perecible ? ((ProductoPerecible) p).getFechaVencimiento().toString() : "-",
                estadoDe(p)
            });
        }
    }

    private String estadoDe(Producto p) {
        String estado = "";
        if (p.estaPorAgotarse()) {
            estado = "Stock bajo";
        }
        if (p instanceof ProductoPerecible && ((ProductoPerecible) p).estaPorVencer()) {
            estado = estado.isEmpty() ? "Por vencer" : estado + " / Por vencer";
        }
        return estado.isEmpty() ? "OK" : estado;
    }

    private void buscar() {
        String codigo = txtBuscar.getText().trim();
        if (codigo.isEmpty()) {
            actualizarTabla();
            return;
        }
        Producto p = inventario.buscarPorCodigo(codigo);
        if (p == null) {
            JOptionPane.showMessageDialog(this,
                    "No existe un producto con el código " + codigo,
                    "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        cargarTabla(new Producto[]{p});
    }

    private void agregar() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        if (codigo.isEmpty() || nombre.isEmpty()) {
            mostrarError("El código y el nombre son obligatorios.");
            return;
        }

        double precio;
        int stock;
        int stockMin;
        try {
            precio = Double.parseDouble(txtPrecio.getText().trim().replace(',', '.'));
            stock = Integer.parseInt(txtStock.getText().trim());
            stockMin = Integer.parseInt(txtStockMin.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("El precio, el stock y el stock mínimo deben ser números válidos.");
            return;
        }
        if (precio < 0 || stock < 0 || stockMin < 0) {
            mostrarError("Los valores no pueden ser negativos.");
            return;
        }

        Producto p;
        if (cmbTipo.getSelectedIndex() == 1) {
            LocalDate fecha;
            try {
                fecha = LocalDate.parse(txtFecha.getText().trim());
            } catch (DateTimeParseException e) {
                mostrarError("Fecha inválida. Usa el formato aaaa-mm-dd, por ejemplo 2026-12-15.");
                return;
            }
            p = new ProductoPerecible(codigo, nombre, precio, stock, stockMin, fecha);
        } else {
            p = new ProductoNoPerecible(codigo, nombre, precio, stock, stockMin);
        }

        try {
            inventario.agregarProducto(p);
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());  // por ejemplo: código repetido
            return;
        }
        actualizarTabla();
        limpiarFormulario();
        JOptionPane.showMessageDialog(this, "Producto agregado: " + nombre,
                "Productos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reponerStock() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            mostrarError("Selecciona un producto de la tabla.");
            return;
        }
        String codigo = (String) modeloTabla.getValueAt(fila, 0);
        Producto p = inventario.buscarPorCodigo(codigo);

        String texto = JOptionPane.showInputDialog(this,
                "Cantidad a agregar al stock de " + p.getNombre() + ":");
        if (texto == null) {
            return;  // canceló
        }
        try {
            int cantidad = Integer.parseInt(texto.trim());
            if (!p.aumentarStock(cantidad)) {
                mostrarError("La cantidad debe ser mayor que 0.");
                return;
            }
            actualizarTabla();
        } catch (NumberFormatException e) {
            mostrarError("Ingresa un número entero válido.");
        }
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtStockMin.setText("");
        txtFecha.setText("");
        cmbTipo.setSelectedIndex(0);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}