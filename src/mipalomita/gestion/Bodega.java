package mipalomita.gestion;

public class Bodega {
    private Inventario inventario;

    public Bodega() {
        this.inventario = new Inventario();
    }

    public Inventario getInventario() {
        return inventario;
    }
}