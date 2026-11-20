package mipalomita.modelo;
import java.util.*;

public class Carrito {
    private ArrayList<ItemVenta> items;

    public Carrito() {
        items = new ArrayList<>(); //se crea la lista vacia para el carrito
    }

    //devuelve true si se agregó, false si no
    public boolean agregarItem(Producto p, int cantidad) {
    //evitar productos inexistentes, cantidades invalidas o stock insuficiente

        if (p == null || cantidad <= 0) {
            return false;
        }

        for(ItemVenta i : items) {
            String itemCodigo = i.getProducto().getCodigoProducto();
            //verificamos si el ya estaba en el carrito, si es asi revisamos el stock
            if (itemCodigo.equals(p.getCodigoProducto())) {
                int cantidadNueva = i.getCantidad() + cantidad;
                if (cantidadNueva > p.getStock()) {
                    return false;
                }
                i.setCantidad(cantidadNueva);
                return true;
            }
        }
        
        //si el producto no estaba en el carrito
        if (cantidad > p.getStock()) {
            return false;
        }
        items.add(new ItemVenta(p, cantidad));
        return true;
    }

    //quitar el producto completo del carrito
    public boolean quitarItem(String codigo){
        for(ItemVenta i : items) {
            String itemCodigo = i.getProducto().getCodigoProducto();
            //buscamos el item en el carrito
            if (itemCodigo.equals(codigo)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    //quitar solo cierta cantidad del producto
    public boolean quitarItem(String codigo, int cantidad){
        if (cantidad <= 0) {
            return false; 
        }

        for(ItemVenta i : items) {
            String itemCodigo = i.getProducto().getCodigoProducto();
            if (itemCodigo.equals(codigo)) {
                int cantidadNueva = i.getCantidad() - cantidad;
                if (cantidadNueva > 0) {
                    i.setCantidad(cantidadNueva);
                } 
                else {
                    items.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemVenta i : items) {
            total += i.getSubtotal();
        }
        return total;
    }

    public ArrayList<ItemVenta> getItems() {
        return items;
    }

    public void vaciar() {
        items.clear();
    }
}