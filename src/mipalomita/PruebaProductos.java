package mipalomita;

import mipalomita.alertas.Alertable;
import mipalomita.alertas.AlertaStock;
import mipalomita.alertas.AlertaVencimiento;
import mipalomita.gestion.Inventario;
import mipalomita.modelo.Producto;
import mipalomita.modelo.ProductoNoPerecible;
import mipalomita.modelo.ProductoPerecible;

import java.time.LocalDate;
import java.util.ArrayList;

public class PruebaProductos {
    public static void main(String[] args) {
        Inventario inv = new Inventario();

        inv.agregarProducto(new ProductoNoPerecible("A01", "Arroz", 4.50, 30, 10));
        inv.agregarProducto(new ProductoPerecible("L01", "Leche", 4.20, 5, 8,
                LocalDate.now().plusDays(3)));
        inv.agregarProducto(new ProductoNoPerecible("F01", "Fideos", 3.00, 12, 10));

        System.out.println("--- Búsqueda por código ---");
        Producto p = inv.buscarPorCodigo("L01");
        System.out.println(p.getDescripcion());
        System.out.println("¿Por agotarse? " + p.estaPorAgotarse());
        if (p instanceof ProductoPerecible) {
            System.out.println("¿Por vencer? " + ((ProductoPerecible) p).estaPorVencer());
        }

        System.out.println("--- Ordenado por nombre ---");
        for (Producto x : inv.ordenarPorNombre()) {
            System.out.println(x.getDescripcion());
        }

        System.out.println("--- Ordenado por stock ---");
        for (Producto x : inv.ordenarPorStock()) {
            System.out.println(x.getNombre() + ": " + x.getStock());
        }

        System.out.println("--- Alertas ---");
        for (Producto x : inv.ordenarPorNombre()) {
            ArrayList<Alertable> alertas = new ArrayList<>();
            alertas.add(new AlertaStock(x));
            if (x instanceof ProductoPerecible) {
                alertas.add(new AlertaVencimiento((ProductoPerecible) x, 7));
            }
            for (Alertable a : alertas) {
                String msg = a.generarAlerta();
                if (!msg.isEmpty())
                    System.out.println(msg);
            }
        }

        System.out.println("--- Descontar stock ---");
        System.out.println("Vender 3 arroz: " + inv.buscarPorCodigo("A01").descontarStock(3));
        System.out.println("Vender 100 arroz: " + inv.buscarPorCodigo("A01").descontarStock(100));
        System.out.println("Stock actual: " + inv.buscarPorCodigo("A01").getStock());
    }
}