package mipalomita.alertas;

import mipalomita.modelo.Producto;

public class AlertaStock implements Alertable {
    private Producto producto;

    public AlertaStock(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String generarAlerta() {
        if (!producto.estaPorAgotarse())
            return "";
        return "STOCK BAJO: " + producto.getNombre() + " tiene " + producto.getStock()
                + " unidades (mínimo: " + producto.getStockMinimo() + ")";
    }
}