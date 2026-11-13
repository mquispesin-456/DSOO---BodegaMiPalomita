package mipalomita.modelo;

public class ProductoNoPerecible extends Producto {

    public ProductoNoPerecible(String codigoProducto, String nombre, double precio,
                               int stock, int stockMinimo) {
        super(codigoProducto, nombre, precio, stock, stockMinimo);
    }

    @Override
    public String getDescripcion() {
        return getNombre() + " (no perecible) - S/ " + getPrecio();
    }
}