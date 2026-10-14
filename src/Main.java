import java.util.Scanner;

/**
 * Clase Main
 * Programa principal. Primero crea el almacén y varios objetos Producto para
 * probar los constructores y los métodos, y después abre un menú en consola
 * para que el usuario pueda usar la aplicación.
 */
public class Main {

    public static void main(String[] args) {
        // Instanciación del almacén
        Almacen almacen = new Almacen("Almacén Central");

        System.out.println("=== " + almacen.getNombre() + " ===");
        System.out.println();

        // 1) Instanciación de objetos con los dos constructores
        System.out.println("--- Prueba 1: crear productos ---");
        Producto p1 = new Producto("P001", "Caja de cartón mediana", "Empaque", 18.50, 120, 30);
        Producto p2 = new Producto("P002", "Cinta adhesiva 48mm", "Empaque", 32.00, 45, 20);
        Producto p3 = new Producto("P003", "Tarima de madera", "Embalaje", 210.00, 12, 10);
        Producto p4 = new Producto("P004", "Guantes de carga", 65.00);   // constructor corto
        Producto p5 = new Producto("P005", "Playo industrial", "Embalaje", 95.00, 8, 15);

        Producto[] iniciales = {p1, p2, p3, p4, p5};
        for (Producto p : iniciales) {
            boolean ok = almacen.agregarProducto(p);
            System.out.println("Agregar " + p.getCodigo() + ": " + (ok ? "listo" : "no se pudo"));
        }

        // Probar que no deja repetir códigos
        Producto repetido = new Producto("P001", "Producto duplicado", 10);
        System.out.println("Agregar código repetido P001: "
                + (almacen.agregarProducto(repetido) ? "listo" : "rechazado (ya existe)"));
        System.out.println();

        // 2) Uso de métodos: entradas y salidas de mercancía
        System.out.println("--- Prueba 2: entradas y salidas ---");
        System.out.println("Entrada de 50 piezas a P004: " + (p4.agregarStock(50) ? "listo" : "error"));
        System.out.println("Salida de 20 piezas de P001: " + (p1.retirarStock(20) ? "listo" : "error"));
        System.out.println("Salida de 500 piezas de P002: " + (p2.retirarStock(500) ? "listo" : "no alcanza el stock"));
        System.out.println("Entrada de -5 piezas a P003: " + (p3.agregarStock(-5) ? "listo" : "cantidad no válida"));
        System.out.println();

        // 3) Listados y totales
        System.out.println("--- Prueba 3: inventario actual ---");
        almacen.listarProductos();
        System.out.println();
        System.out.printf("Valor total del inventario: $%.2f%n", almacen.valorTotalInventario());
        System.out.println();
        System.out.println("--- Productos con stock bajo ---");
        almacen.listarBajoStock();
        System.out.println();

        // 4) Menú interactivo
        menu(almacen);
    }

    /**
     * Menú en consola. Se repite hasta que el usuario elige la opción 0.
     */
    private static void menu(Almacen almacen) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("========== MENÚ ==========");
            System.out.println("1. Ver inventario");
            System.out.println("2. Agregar producto");
            System.out.println("3. Registrar entrada");
            System.out.println("4. Registrar salida");
            System.out.println("5. Buscar producto");
            System.out.println("6. Ver productos con stock bajo");
            System.out.println("7. Valor total del inventario");
            System.out.println("8. Eliminar producto");
            System.out.println("0. Salir");
            opcion = leerEntero(sc, "Elige una opción: ");

            switch (opcion) {
                case 1:
                    almacen.listarProductos();
                    break;
                case 2:
                    agregarProducto(sc, almacen);
                    break;
                case 3:
                    moverStock(sc, almacen, true);
                    break;
                case 4:
                    moverStock(sc, almacen, false);
                    break;
                case 5:
                    buscarProducto(sc, almacen);
                    break;
                case 6:
                    almacen.listarBajoStock();
                    break;
                case 7:
                    System.out.printf("Valor total del inventario: $%.2f%n", almacen.valorTotalInventario());
                    break;
                case 8:
                    eliminarProducto(sc, almacen);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
            System.out.println();
        }
        sc.close();
    }

    // Pide los datos de un producto nuevo y lo agrega al almacén
    private static void agregarProducto(Scanner sc, Almacen almacen) {
        System.out.print("Código: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Categoría: ");
        String categoria = sc.nextLine().trim();
        double precio = leerDecimal(sc, "Precio: ");
        int stock = leerEntero(sc, "Stock inicial: ");
        int minimo = leerEntero(sc, "Stock mínimo: ");

        if (codigo.isEmpty() || nombre.isEmpty()) {
            System.out.println("El código y el nombre no pueden ir vacíos.");
            return;
        }
        if (categoria.isEmpty()) {
            categoria = "General";
        }

        Producto nuevo = new Producto(codigo, nombre, categoria, precio, stock, minimo);
        if (almacen.agregarProducto(nuevo)) {
            System.out.println("Producto agregado correctamente.");
        } else {
            System.out.println("Ya existe un producto con ese código.");
        }
    }

    // Registra una entrada (entrada = true) o una salida (entrada = false)
    private static void moverStock(Scanner sc, Almacen almacen, boolean entrada) {
        System.out.print("Código del producto: ");
        Producto p = almacen.buscarPorCodigo(sc.nextLine().trim());
        if (p == null) {
            System.out.println("No se encontró ese producto.");
            return;
        }
        int cantidad = leerEntero(sc, "Cantidad de piezas: ");
        boolean ok = entrada ? p.agregarStock(cantidad) : p.retirarStock(cantidad);
        if (ok) {
            System.out.println("Movimiento registrado. Stock actual de " + p.getNombre() + ": " + p.getStock());
        } else {
            System.out.println(entrada ? "La cantidad no es válida."
                                       : "La cantidad no es válida o no alcanza el stock.");
        }
    }

    // Busca un producto y muestra sus datos
    private static void buscarProducto(Scanner sc, Almacen almacen) {
        System.out.print("Código del producto: ");
        Producto p = almacen.buscarPorCodigo(sc.nextLine().trim());
        if (p == null) {
            System.out.println("No se encontró ese producto.");
        } else {
            System.out.println("Nombre:    " + p.getNombre());
            System.out.println("Categoría: " + p.getCategoria());
            System.out.printf("Precio:    $%.2f%n", p.getPrecio());
            System.out.println("Stock:     " + p.getStock() + " (mínimo " + p.getStockMinimo() + ")");
            System.out.printf("Valor:     $%.2f%n", p.valorTotal());
        }
    }

    // Elimina un producto por código
    private static void eliminarProducto(Scanner sc, Almacen almacen) {
        System.out.print("Código del producto a eliminar: ");
        String codigo = sc.nextLine().trim();
        if (almacen.eliminarProducto(codigo)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No se encontró ese producto.");
        }
    }

    // Lee un entero del teclado; si escriben otra cosa, vuelve a preguntar
    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número entero válido.");
            }
        }
    }

    // Lee un decimal del teclado; si escriben otra cosa, vuelve a preguntar
    private static double leerDecimal(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número válido (ejemplo: 45.50).");
            }
        }
    }
}
