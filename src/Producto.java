/**
 * Clase Producto (CLASE BASE)
 * Representa un producto que se guarda en el almacén (por ejemplo una caja,
 * una tarima o un artículo suelto). Cada objeto Producto guarda sus propios
 * datos y sabe hacer sus operaciones básicas: recibir y sacar existencias.
 *
 * HERENCIA: de esta clase se derivan ProductoPerecedero y ProductoElectronico.
 * Las clases derivadas reutilizan estos atributos y métodos con "extends" y
 * pueden sobrescribir algunos (valorTotal y getDetalle).
 */
public class Producto {

    // ---------- Atributos ----------
    // Ahora son "protected": nadie de fuera de la clase los puede tocar, pero
    // las clases derivadas (hijas) sí pueden usarlos directamente (herencia).
    protected String codigo;
    protected String nombre;
    protected String categoria;
    protected double precio;
    protected int stock;        // piezas que hay ahorita en el almacén
    protected int stockMinimo;  // si el stock baja de aquí, hay que reabastecer

    // ---------- Constructores ----------

    /**
     * Constructor completo: recibe todos los datos del producto.
     */
    public Producto(String codigo, String nombre, String categoria,
                    double precio, int stock, int stockMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        // Si mandan valores negativos los dejamos en cero para evitar datos raros
        this.precio = precio < 0 ? 0 : precio;
        this.stock = stock < 0 ? 0 : stock;
        this.stockMinimo = stockMinimo < 0 ? 0 : stockMinimo;
    }

    /**
     * Constructor corto: solo código, nombre y precio.
     * Lo demás se llena con valores por omisión (sobrecarga de constructores).
     */
    public Producto(String codigo, String nombre, double precio) {
        this(codigo, nombre, "General", precio, 0, 5);
    }

    // ---------- Getters y setters ----------

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    /**
     * Cambia el precio, siempre que el nuevo valor no sea negativo.
     * @return true si se pudo cambiar, false si el precio no era válido
     */
    public boolean setPrecio(double nuevoPrecio) {
        if (nuevoPrecio < 0) {
            return false;
        }
        this.precio = nuevoPrecio;
        return true;
    }

    // ---------- Métodos de comportamiento ----------

    /**
     * Registra una entrada de mercancía (suma piezas al stock).
     * @return true si se registró, false si la cantidad no era mayor a cero
     */
    public boolean agregarStock(int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        stock = stock + cantidad;
        return true;
    }

    /**
     * Registra una salida de mercancía (resta piezas al stock).
     * No deja sacar más de lo que hay.
     * @return true si se registró, false si la cantidad no es válida o no alcanza
     */
    public boolean retirarStock(int cantidad) {
        if (cantidad <= 0 || cantidad > stock) {
            return false;
        }
        stock = stock - cantidad;
        return true;
    }

    /**
     * Calcula cuánto dinero hay en este producto (precio por existencias).
     */
    public double valorTotal() {
        return precio * stock;
    }

    /**
     * Dice si el producto ya está en el mínimo o por debajo de él.
     */
    public boolean estaBajoStock() {
        return stock <= stockMinimo;
    }

    /**
     * Dato extra que se muestra en la última columna de la tabla.
     * En la clase base no hay nada especial; las clases derivadas lo
     * sobrescriben para mostrar su información propia (caducidad, garantía...).
     */
    public String getDetalle() {
        return "-";
    }

    /**
     * Texto de una línea para mostrar el producto en la tabla del inventario.
     * Usa getDetalle(), así cada tipo de producto muestra su propio detalle.
     */
    @Override
    public String toString() {
        return String.format("%-8s %-24s %-12s $%9.2f %6d %6d  %s",
                codigo, nombre, categoria, precio, stock, stockMinimo, getDetalle());
    }
}
