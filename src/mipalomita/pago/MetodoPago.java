package mipalomita.pago;

public abstract class MetodoPago {
    private String nombre;

    public MetodoPago(String nombre) {
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularVuelto(double totalCompra, double pagaCliente);
}
