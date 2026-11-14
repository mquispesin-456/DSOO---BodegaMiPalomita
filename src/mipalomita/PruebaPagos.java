package mipalomita;

import mipalomita.pago.MetodoPago;
import mipalomita.pago.PagoEfectivo;
import mipalomita.pago.PagoYape;

public class PruebaPagos {
    public static void main(String[] args) {
        MetodoPago[] pagos = { new PagoEfectivo(), new PagoYape() };

        double total = 18.50;
        double paga = 20.00;

        for (MetodoPago pago : pagos) {
            System.out.println("Método: " + pago.getNombre()
                    + " | Vuelto: S/ " + pago.calcularVuelto(total, paga));
        }

        System.out.println("--- Pago insuficiente en efectivo ---");
        try {
            new PagoEfectivo().calcularVuelto(18.50, 10.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}