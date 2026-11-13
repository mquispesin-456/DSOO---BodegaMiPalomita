package mipalomita.gestion;

import mipalomita.modelo.Producto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Inventario {
    private HashMap<String, Producto> productos = new HashMap<>();

    public void agregarProducto(Producto p) {
        if (productos.containsKey(p.getCodigoProducto())) {
            throw new IllegalArgumentException(
                "Ya existe un producto con el código " + p.getCodigoProducto());
        }
        productos.put(p.getCodigoProducto(), p);
    }

    // Devuelve null si no existe
    public Producto buscarPorCodigo(String c) {
        return productos.get(c);
    }

    public Producto[] ordenarPorNombre() {
        Producto[] lista = productos.values().toArray(new Producto[0]);
        Arrays.sort(lista, Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }

    public Producto[] ordenarPorStock() {
        Producto[] lista = productos.values().toArray(new Producto[0]);
        Arrays.sort(lista, Comparator.comparingInt(Producto::getStock));
        return lista;
    }
}