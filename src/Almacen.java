import java.util.ArrayList;

/**
 * Clase Almacen
 * Administra el conjunto de productos. Aquí van las operaciones que trabajan
 * con toda la lista: agregar, buscar, eliminar, listar y sacar totales.
 */
public class Almacen {

    // ---------- Atributos ----------
    private String nombre;
    private ArrayList<Producto> productos;

    // ---------- Constructor ----------

    /**
     * Crea un almacén vacío con el nombre indicado.
     */
    public Almacen(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int cantidadProductos() {
        return productos.size();
    }

    // ---------- Métodos ----------

    /**
     * Agrega un producto nuevo al almacén.
     * No permite dos productos con el mismo código.
     * @return true si se agregó, false si el código ya existía
     */
    public boolean agregarProducto(Producto nuevo) {
        if (buscarPorCodigo(nuevo.getCodigo()) != null) {
            return false;
        }
        productos.add(nuevo);
        return true;
    }

    /**
     * Busca un producto por su código.
     * @return el producto, o null si no existe
     */
    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Elimina un producto del almacén usando su código.
     * @return true si lo eliminó, false si no lo encontró
     */
    public boolean eliminarProducto(String codigo) {
        Producto p = buscarPorCodigo(codigo);
        if (p == null) {
            return false;
        }
        productos.remove(p);
        return true;
    }

    /**
     * Imprime en pantalla todos los productos en forma de tabla.
     */
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El almacén no tiene productos registrados.");
            return;
        }
        imprimirEncabezado();
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    /**
     * Imprime solo los productos que ya están en el mínimo o por debajo.
     */
    public void listarBajoStock() {
        boolean hayAlguno = false;
        for (Producto p : productos) {
            if (p.estaBajoStock()) {
                if (!hayAlguno) {
                    imprimirEncabezado();
                    hayAlguno = true;
                }
                System.out.println(p);
            }
        }
        if (!hayAlguno) {
            System.out.println("Ningún producto está por debajo del stock mínimo.");
        }
    }

    /**
     * Suma el valor de todos los productos del almacén.
     */
    public double valorTotalInventario() {
        double total = 0;
        for (Producto p : productos) {
            total = total + p.valorTotal();
        }
        return total;
    }

    // Método auxiliar para no repetir el encabezado de la tabla
    private void imprimirEncabezado() {
        System.out.println(String.format("%-8s %-24s %-12s %10s %6s %6s",
                "CÓDIGO", "NOMBRE", "CATEGORÍA", "PRECIO", "STOCK", "MÍN."));
        System.out.println("-".repeat(72));
    }
}
