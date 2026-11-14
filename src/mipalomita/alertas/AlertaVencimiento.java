public class AlertaVencimiento implements Alertable {
    private ProductoPerecible producto;
    private int diasAviso;

    public AlertaVencimiento(ProductoPerecible producto, int diasAviso) {
        this.producto = producto;
        this.diasAviso = diasAviso;
    }

    @Override
    public String generarAlerta() {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), producto.getFechaVencimiento());
        if (dias < 0)
            return "VENCIDO: " + producto.getNombre();
        if (dias <= diasAviso)
            return "POR VENCER: " + producto.getNombre()
                    + " vence en " + dias + " día(s)";
        return "";
    }
}