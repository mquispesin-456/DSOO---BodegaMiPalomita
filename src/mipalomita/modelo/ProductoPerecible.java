package mipalomita.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProductoPerecible extends Producto {
    private static final int DIAS_AVISO = 7;  // días antes de vencer para avisar

    private LocalDate fechaVencimiento;

    public ProductoPerecible(String codigoProducto, String nombre, double precio,
                             int stock, int stockMinimo, LocalDate fechaVencimiento) {
        super(codigoProducto, nombre, precio, stock, stockMinimo);
        this.fechaVencimiento = fechaVencimiento;
    }

    // true si ya venció o vence dentro de DIAS_AVISO días
    public boolean estaPorVencer() {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), fechaVencimiento);
        return dias <= DIAS_AVISO;
    }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }

    @Override
    public String getDescripcion() {
        return getNombre() + " (vence: " + fechaVencimiento + ") - S/ " + getPrecio();
    }
}