/*
  Clase principal para demostrar el uso de los patrones Decorator y Command.
 */

public class Main {
    public static void main(String[] args) {
        // Usando el DiscountManager singleton
        DiscountManager discountManager = DiscountManager.getInstance();

        // Crear un producto
        Component producto = new Producto("Camisa", 10000);
        System.out.println("Producto original: " + producto.getDescripcion());
        System.out.println("Precio original: " + producto.getPrecio());

        // Aplicar descuento usando codigo promocional con DiscountManager (Decorator)
        Component productoConDescuento = discountManager.aplicarDescuento(producto, "PROMO20");
        System.out.println("Producto con descuento: " + productoConDescuento.getDescripcion());
        System.out.println("Precio con descuento: " + productoConDescuento.getPrecio());

        // Usando Command para aplicar descuentos
        Command comandoDescuento10 = new AplicarDescuentoPorcentajeCommand(producto, 10);
        Component productoConDescuento10 = comandoDescuento10.ejecutar();
        System.out.println("Producto con descuento 10%: " + productoConDescuento10.getDescripcion());
        System.out.println("Precio con descuento 10%: " + productoConDescuento10.getPrecio());

        Command comandoDescuentoFijo = new AplicarDescuentoFijoCommand(producto, 2000);
        Component productoConDescuentoFijo = comandoDescuentoFijo.ejecutar();
        System.out.println("Producto con descuento fijo: " + productoConDescuentoFijo.getDescripcion());
        System.out.println("Precio con descuento fijo: " + productoConDescuentoFijo.getPrecio());
    }
}