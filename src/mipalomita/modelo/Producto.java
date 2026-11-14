package mipalomita.modelo;

public abstract class Producto {
    private String codigoProducto;
    private String nombre;
    private double precio;
    private int stock;
    private int stockMinimo;

    public Producto(String codigoProducto, String nombre, double precio, int stock, int stockMinimo) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public String getCodigoProducto() { return codigoProducto; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public int getStockMinimo() { return stockMinimo; }  // extra: la usará AlertaStock

    // Devuelve false si la cantidad no es válida o no alcanza el stock
    public boolean descontarStock(int cantidad) {
        if (cantidad <= 0 || cantidad > stock) {
            return false;
        }
        stock -= cantidad;
        return true;
    }

    // Suma unidades al stock (reposición). Devuelve false si la cantidad no es válida
    public boolean aumentarStock(int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        stock += cantidad;
        return true;
    }

    public boolean estaPorAgotarse() {
        return stock <= stockMinimo;
    }

    // Cada tipo de producto da su propia descripción
    public abstract String getDescripcion();
}