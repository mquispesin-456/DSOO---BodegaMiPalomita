package mipalomita;

import mipalomita.gestion.Bodega;
import mipalomita.gestion.Inventario;
import mipalomita.gui.VentanaPrincipal;
import mipalomita.modelo.ProductoNoPerecible;
import mipalomita.modelo.ProductoPerecible;

import javax.swing.SwingUtilities;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Bodega bodega = new Bodega();
        Inventario inv = bodega.getInventario();

        inv.agregarProducto(new ProductoNoPerecible("A01", "Arroz", 4.50, 30, 10));
        inv.agregarProducto(new ProductoNoPerecible("F01", "Fideos", 3.00, 12, 10));
        inv.agregarProducto(new ProductoNoPerecible("G01", "Galletas", 1.50, 40, 10));
        inv.agregarProducto(new ProductoPerecible("L01", "Leche", 4.20, 5, 8,
                LocalDate.now().plusDays(3)));

        SwingUtilities.invokeLater(() -> new VentanaPrincipal(bodega).setVisible(true));
    }
}