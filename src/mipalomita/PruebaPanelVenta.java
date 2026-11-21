package mipalomita;

import mipalomita.gestion.Inventario;
import mipalomita.gui.PanelVenta;
import mipalomita.modelo.ProductoNoPerecible;
import javax.swing.*;

public class PruebaPanelVenta extends JFrame {
    public PruebaPanelVenta() {
        Inventario inv = new Inventario();
        inv.agregarProducto(new ProductoNoPerecible("A01", "Arroz", 4.50, 30, 10));
        inv.agregarProducto(new ProductoNoPerecible("F01", "Fideos", 3.00, 12, 10));

        setTitle("Prueba - Panel de venta");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new PanelVenta(inv));
        setVisible(true); 
    }

    public static void main(String[] args) {
        new PruebaPanelVenta();
    }
}