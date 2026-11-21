package mipalomita.gui;

import mipalomita.gestion.Bodega;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(Bodega bodega) {
        super("Bodega MiPalomita");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Productos", new PanelProductos(bodega.getInventario()));
        pestanas.addTab("Venta", new PanelVenta(bodega.getInventario()));

        setContentPane(pestanas);
        setSize(950, 620);
        setLocationRelativeTo(null);
    }
}