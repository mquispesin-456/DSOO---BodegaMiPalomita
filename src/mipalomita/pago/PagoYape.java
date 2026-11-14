package mipalomita.pago;

public class PagoYape extends MetodoPago {

    public PagoYape() {
        super("Yape");
    }

    @Override
    public double calcularVuelto(double totalCompra, double pagaCliente) {
        return 0;   // con Yape se paga el monto exacto, no hay vuelto
    }
}