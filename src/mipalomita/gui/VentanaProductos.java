package mipalomita.gui;

import mipalomita.gestion.Inventario;
import mipalomita.modelo.ProductoNoPerecible;
import mipalomita.modelo.ProductoPerecible;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.time.LocalDate;

public class VentanaProductos extends JFrame {

    public VentanaProductos(Inventario inventario) {
        super("Bodega MiPalomita - Productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(new PanelProductos(inventario));
        setSize(950, 620);
        setLocationRelativeTo(null);
    }

    // Para probar la ventana sola, con datos de ejemplo
    public static void main(String[] args) {
        Inventario inv = new Inventario();
        inv.agregarProducto(new ProductoNoPerecible("A01", "Arroz", 4.50, 30, 10));
        inv.agregarProducto(new ProductoPerecible("L01", "Leche", 4.20, 5, 8,
                LocalDate.now().plusDays(3)));
        inv.agregarProducto(new ProductoNoPerecible("F01", "Fideos", 3.00, 12, 10));

        SwingUtilities.invokeLater(() -> new VentanaProductos(inv).setVisible(true));
    }
}