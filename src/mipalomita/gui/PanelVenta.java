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

        // PASO 2: armar el panel de arriba (FlowLayout) y agregarlo en NORTH
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Codigo:"));
        panelSuperior.add(txtCodigo);
        panelSuperior.add(new JLabel("Cantidad:"));
        panelSuperior.add(txtCantidad);
        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnQuitar);
        panelSuperior.add(btnVaciar);

        // PASO 3: agregar areaCarrito en CENTER (con setEditable(false))
        // PASO 4: agregar lblTotal en SOUTH
        // PASO 5: btnAgregar.addActionListener(this); y lo mismo con btnQuitar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            // 1. leer código y cantidad
            // 2. convertir la cantidad (try/catch si no es número)
            // 3. buscar el producto con inventario.buscarPorCodigo(...)
            // 4. si es null -> JOptionPane "producto no existe" y return
            // 5. si carrito.agregarItem(...) devuelve false -> JOptionPane "cantidad inválida o sin stock"
            // 6. actualizarVista()
        } else if (e.getSource() == btnQuitar) {
            // lo mismo, pero con quitarItem (con o sin cantidad)
        }
    }

    private void actualizarVista() {
        // recorrer carrito.getItems() y armar el texto: nombre, cantidad, subtotal
        // areaCarrito.setText(...)
        // lblTotal.setText("Total: S/ " + carrito.calcularTotal());
    }
}