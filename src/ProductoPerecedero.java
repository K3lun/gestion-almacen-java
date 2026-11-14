import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase ProductoPerecedero (CLASE DERIVADA de Producto)
 * Es un producto que caduca (alimentos, medicinas, etc.).
 *
 * HERENCIA: con "extends Producto" esta clase ya trae código, nombre, categoría,
 * precio, stock y stockMinimo, además de agregarStock(), retirarStock(),
 * estaBajoStock() y los getters. Aquí solo se agrega lo que la hace distinta:
 * la fecha de caducidad.
 */
public class ProductoPerecedero extends Producto {

    // Atributo exclusivo de esta clase derivada
    private LocalDate fechaCaducidad;

    /**
     * Constructor: manda los datos comunes a la clase base con super(...)
     * y guarda aquí la fecha de caducidad.
     */
    public ProductoPerecedero(String codigo, String nombre, String categoria,
                              double precio, int stock, int stockMinimo,
                              LocalDate fechaCaducidad) {
        super(codigo, nombre, categoria, precio, stock, stockMinimo);
        this.fechaCaducidad = fechaCaducidad;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    // ---------- Métodos exclusivos de la clase derivada ----------

    /**
     * Dice si el producto ya pasó su fecha de caducidad.
     */
    public boolean estaVencido() {
        return LocalDate.now().isAfter(fechaCaducidad);
    }

    /**
     * Días que faltan para que caduque (negativo si ya caducó).
     */
    public long diasParaCaducar() {
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaCaducidad);
    }

    // ---------- Métodos sobrescritos de la clase base ----------

    /**
     * SOBRESCRITURA: un producto vencido ya no vale nada para el inventario,
     * así que regresa 0. Si no está vencido, usa el cálculo de la clase base
     * con super.valorTotal().
     */
    @Override
    public double valorTotal() {
        if (estaVencido()) {
            return 0;
        }
        return super.valorTotal();
    }

    /**
     * SOBRESCRITURA: en la tabla del inventario muestra la caducidad.
     */
    @Override
    public String getDetalle() {
        if (estaVencido()) {
            return "VENCIDO desde " + fechaCaducidad;
        }
        return "Caduca " + fechaCaducidad + " (" + diasParaCaducar() + " días)";
    }
}
