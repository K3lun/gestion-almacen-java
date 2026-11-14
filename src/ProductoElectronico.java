/**
 * Clase ProductoElectronico (CLASE DERIVADA de Producto)
 * Es un producto electrónico (escáner, báscula, radio, etc.) que tiene marca
 * y garantía.
 *
 * HERENCIA: con "extends Producto" reutiliza todos los atributos y métodos de
 * la clase base (código, nombre, precio, stock, agregarStock(), retirarStock()...)
 * y solo agrega lo propio de un electrónico: marca y meses de garantía.
 */
public class ProductoElectronico extends Producto {

    // Atributos exclusivos de esta clase derivada
    private String marca;
    private int garantiaMeses;

    /**
     * Constructor: los datos comunes se mandan a la clase base con super(...);
     * la marca y la garantía se guardan aquí.
     */
    public ProductoElectronico(String codigo, String nombre, String categoria,
                               double precio, int stock, int stockMinimo,
                               String marca, int garantiaMeses) {
        super(codigo, nombre, categoria, precio, stock, stockMinimo);
        this.marca = marca;
        this.garantiaMeses = garantiaMeses < 0 ? 0 : garantiaMeses;
    }

    public String getMarca() {
        return marca;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    // ---------- Método exclusivo de la clase derivada ----------

    /**
     * Suma meses a la garantía del producto.
     * @return true si se pudo, false si los meses no eran mayores a cero
     */
    public boolean extenderGarantia(int meses) {
        if (meses <= 0) {
            return false;
        }
        garantiaMeses = garantiaMeses + meses;
        return true;
    }

    // ---------- Método sobrescrito de la clase base ----------

    /**
     * SOBRESCRITURA: en la tabla del inventario muestra marca y garantía.
     */
    @Override
    public String getDetalle() {
        return marca + ", garantía " + garantiaMeses + " meses";
    }
}
