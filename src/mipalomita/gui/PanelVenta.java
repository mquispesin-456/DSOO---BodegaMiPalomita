package mipalomita.gui;

import mipalomita.gestion.Inventario;
import mipalomita.modelo.Carrito;
import mipalomita.modelo.ItemVenta;
import mipalomita.modelo.Producto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelVenta extends JPanel implements ActionListener {
    private Inventario inventario;
    private Carrito carrito;

    private JTextField txtCodigo;
    private JTextField txtCantidad;
    private JButton btnAgregar;
    private JButton btnQuitar;
    private JButton btnVaciar;
    private JTextArea areaCarrito;
    private JLabel lblTotal;

    public PanelVenta(Inventario inventario) {
        this.inventario = inventario;
        this.carrito = new Carrito();

        setLayout(new BorderLayout());

        //crear los componentes
        txtCodigo = new JTextField(8);
        txtCantidad = new JTextField(4);
        btnAgregar = new JButton("Agregar");
        btnQuitar = new JButton("Quitar");
        btnVaciar = new JButton("Vaciar");
        areaCarrito = new JTextArea();
        areaCarrito.setEditable(false);
        lblTotal = new JLabel("Total: S/ 0.00", JLabel.CENTER);

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Codigo:"));
        panelSuperior.add(txtCodigo);
        panelSuperior.add(new JLabel("Cantidad:"));
        panelSuperior.add(txtCantidad);
        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnQuitar);
        panelSuperior.add(btnVaciar);

        add(panelSuperior, BorderLayout.NORTH);
        add(areaCarrito, BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);

        btnAgregar.addActionListener(this);
        btnQuitar.addActionListener(this);
        btnVaciar.addActionListener(this);
        txtCantidad.addActionListener(this);

        actualizarVista();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar || e.getSource() == txtCantidad) {
            agregar();

        } else if (e.getSource() == btnQuitar) {
            quitar();

        } else if (e.getSource() == btnVaciar) {
            carrito.vaciar();
            actualizarVista();
        }
    }

    private void agregar(){
        String codigo = txtCodigo.getText().trim();
        int cantidad;

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escribe el código del producto.");
            return;
        }

        try{
            cantidad = Integer.parseInt(txtCantidad.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero");
            return;
        }

        Producto p = inventario.buscarPorCodigo(codigo);
        if(p == null) {
            JOptionPane.showMessageDialog(null, "El producto no existe");
            return;
        }

        if (carrito.agregarItem(p, cantidad)) {
            actualizarVista();
            txtCodigo.setText("");
            txtCantidad.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Cantidad invalida o sin stock disponible (stock: " + p.getStock() + ")");

        }
    }

    private void quitar() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escribe el código del producto.");
            return;
        }

        String textoCantidad = txtCantidad.getText().trim();
        boolean quitado;
        if (textoCantidad.isEmpty()) {
            quitado = carrito.quitarItem(codigo);
        } else {
            int cantidad;
            try {
                cantidad = Integer.parseInt(textoCantidad);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero");
                return;
            }
            quitado = carrito.quitarItem(codigo, cantidad);
        }

        if (quitado) {
            actualizarVista();
            txtCodigo.setText("");
            txtCantidad.setText("");
        }
        else {
            JOptionPane.showMessageDialog(null, "Ese producto no está en el carrito o la cantidad es inválida.");
        }
    }

    private void actualizarVista() {
        String texto = "";
        for (ItemVenta i : carrito.getItems()) {
            texto += i.getProducto().getCodigoProducto() + " | " + i.getProducto().getNombre() + " | x" + i.getCantidad()
                + " | S/ " + String.format("%.2f", i.getSubtotal()) + "\n";
        }

        areaCarrito.setText(texto);
        lblTotal.setText("Total: S/ " + String.format("%.2f", carrito.calcularTotal()));
    }

    public Carrito getCarrito() {
        return carrito;
    }
}