package mipalomita;

import mipalomita.gestion.Inventario;
import mipalomita.modelo.Producto;
import mipalomita.modelo.ProductoNoPerecible;
import mipalomita.modelo.ProductoPerecible;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuProductos {
    private Inventario inventario;
    private Scanner sc = new Scanner(System.in);

    public MenuProductos(Inventario inventario) {
        this.inventario = inventario;
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== PRODUCTOS - BODEGA MIPALOMITA ===");
            System.out.println("1. Agregar producto no perecible");
            System.out.println("2. Agregar producto perecible");
            System.out.println("3. Buscar producto por código");
            System.out.println("4. Listar productos por nombre");
            System.out.println("5. Listar productos por stock");
            System.out.println("6. Reponer stock de un producto");
            System.out.println("0. Salir");
            opcion = leerNumero("Opción: ");

            switch (opcion) {
                case 1: agregarProducto(false); break;
                case 2: agregarProducto(true); break;
                case 3: buscarProducto(); break;
                case 4: listar(inventario.ordenarPorNombre()); break;
                case 5: listar(inventario.ordenarPorStock()); break;
                case 6: reponerStock(); break;
                case 0: System.out.println("Hasta pronto."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarProducto(boolean perecible) {
        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        double precio = leerDecimal("Precio (S/): ");
        int stock = leerNumero("Stock inicial (cantidad de unidades): ");
        int stockMinimo = leerNumero("Stock mínimo: ");

        Producto p;
        if (perecible) {
            LocalDate fecha = leerFecha("Fecha de vencimiento (aaaa-mm-dd): ");
            p = new ProductoPerecible(codigo, nombre, precio, stock, stockMinimo, fecha);
        } else {
            p = new ProductoNoPerecible(codigo, nombre, precio, stock, stockMinimo);
        }

        try {
            inventario.agregarProducto(p);
            System.out.println("Producto agregado: " + p.getDescripcion()
                    + " | Stock: " + p.getStock());
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo agregar: " + e.getMessage());
            System.out.println("Si el producto ya existe, usa la opción 6 para reponer stock.");
        }
    }

    private void buscarProducto() {
        String codigo = leerTexto("Código a buscar: ");
        Producto p = inventario.buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("No existe un producto con ese código.");
            return;
        }
        System.out.println(p.getDescripcion());
        System.out.println("Stock: " + p.getStock() + " (mínimo: " + p.getStockMinimo() + ")");
        if (p.estaPorAgotarse()) {
            System.out.println("¡Atención: stock bajo!");
        }
        if (p instanceof ProductoPerecible && ((ProductoPerecible) p).estaPorVencer()) {
            System.out.println("¡Atención: producto por vencer!");
        }
    }

    private void listar(Producto[] lista) {
        if (lista.length == 0) {
            System.out.println("El inventario está vacío.");
            return;
        }
        for (Producto p : lista) {
            String aviso = p.estaPorAgotarse() ? "  <- stock bajo" : "";
            System.out.println(p.getCodigoProducto() + " | " + p.getDescripcion()
                    + " | Stock: " + p.getStock() + aviso);
        }
    }

    private void reponerStock() {
        String codigo = leerTexto("Código del producto: ");
        Producto p = inventario.buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("No existe un producto con ese código.");
            return;
        }
        int cantidad = leerNumero("Cantidad a agregar: ");
        if (p.aumentarStock(cantidad)) {
            System.out.println(p.getNombre() + " ahora tiene " + p.getStock() + " unidades.");
        } else {
            System.out.println("La cantidad debe ser mayor que 0.");
        }
    }

    // ---- Lectura segura de datos (no se cae si se escribe algo incorrecto) ----

    private String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
    }

    private int leerNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n >= 0) return n;
            } catch (NumberFormatException e) {
                // cae al mensaje de abajo
            }
            System.out.println("Ingresa un número entero válido (0 o mayor).");
        }
    }

    private double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double n = Double.parseDouble(sc.nextLine().trim());
                if (n >= 0) return n;
            } catch (NumberFormatException e) {
                // cae al mensaje de abajo
            }
            System.out.println("Ingresa un número válido (0 o mayor), por ejemplo 4.50");
        }
    }

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return LocalDate.parse(sc.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Usa aaaa-mm-dd, por ejemplo 2026-12-15");
            }
        }
    }

    // Prueba momentànea
    public static void main(String[] args) {
        new MenuProductos(new Inventario()).iniciar();
    }
}