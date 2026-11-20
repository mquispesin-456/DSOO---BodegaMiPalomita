package mipalomita;

import mipalomita.modelo.Carrito;
import mipalomita.modelo.Producto;
import mipalomita.modelo.ProductoNoPerecible;

public class PruebaCarrito {
    public static void main(String[] args) {
        Producto arroz = new ProductoNoPerecible("A01", "Arroz", 4.50, 30, 10);
        Producto fideos = new ProductoNoPerecible("F01", "Fideos", 3.00, 12, 10);

        Carrito carrito = new Carrito();
        carrito.agregarItem(arroz, 2);
        carrito.agregarItem(fideos, 1);
        carrito.agregarItem(arroz, 3);                  // suma: arroz queda en 5
        System.out.println("Total: S/ " + carrito.calcularTotal());   // 25.5

        System.out.println("Agregar 100 arroz (stock 30): " + carrito.agregarItem(arroz, 100));  // false

        carrito.quitarItem("A01", 2);                   // arroz queda en 3
        System.out.println("Total: S/ " + carrito.calcularTotal());   // 16.5

        carrito.quitarItem("F01");                      // quita fideos entero
        System.out.println("Total: S/ " + carrito.calcularTotal());   // 13.5
    }
}