# Sistema de gestión de inventario de almacén

Aplicación de consola hecha en Java para la **Actividad formativa 2** (Desarrollo de una Aplicación Simple en Línea).
Sirve para llevar el control de los productos de un almacén: altas, entradas, salidas, búsquedas y avisos de stock bajo.

## Conceptos que se aplican

- Declaración de clases y atributos (`Producto`, `Almacen`, `Main`)
- Constructores, incluyendo sobrecarga (`Producto` tiene dos)
- Instanciación de objetos con `new`
- Métodos con parámetros y valores de retorno
- Encapsulamiento (atributos `private` con getters)

## Estructura

```
gestion-almacen-java/
├── src/
│   ├── Producto.java   -> un artículo del almacén y sus operaciones
│   ├── Almacen.java    -> la lista de productos y las operaciones generales
│   └── Main.java       -> programa principal (pruebas + menú)
├── .gitignore
└── README.md
```

## Cómo ejecutarlo

Se necesita tener instalado el JDK (probado con Java 21).

Desde la carpeta del proyecto:

```bash
mkdir out
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

También se puede abrir la carpeta en IntelliJ IDEA, Eclipse o NetBeans y ejecutar la clase `Main`.

## Qué hace al arrancar

1. Crea el almacén y cinco productos de ejemplo (con los dos constructores).
2. Prueba entradas y salidas, incluyendo casos que deben rechazarse.
3. Muestra el inventario, el valor total y los productos con stock bajo.
4. Abre un menú para usar la aplicación.

## Autor

Cristian Adrian Villanueva Ortega
Ingeniería en Desarrollo de Software, Universidad Ciudadana de Nuevo León
