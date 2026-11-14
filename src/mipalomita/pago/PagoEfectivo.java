package mipalomita.pago;

public class PagoEfectivo extends MetodoPago {

    public PagoEfectivo() {
        super("Efectivo");
    }

    @Override
    public double calcularVuelto(double totalCompra, double pagaCliente) {
        if (pagaCliente < totalCompra) {
            return -1;   // -1 = el dinero no alcanza
        }
        return pagaCliente - totalCompra;
    }
}