/**
 * Clase Producto
 * Representa un producto que se guarda en el almacén (por ejemplo una caja,
 * una tarima o un artículo suelto). Cada objeto Producto guarda sus propios
 * datos y sabe hacer sus operaciones básicas: recibir y sacar existencias.
 */
public class Producto {

    // ---------- Atributos ----------
    // Son privados para que nadie los cambie directo desde fuera de la clase.
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;        // piezas que hay ahorita en el almacén
    private int stockMinimo;  // si el stock baja de aquí, hay que reabastecer

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
     * Texto de una línea para mostrar el producto en la tabla del inventario.
     */
    @Override
    public String toString() {
        return String.format("%-8s %-24s %-12s $%9.2f %6d %6d",
                codigo, nombre, categoria, precio, stock, stockMinimo);
    }
}
