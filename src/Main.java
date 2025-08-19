public class Main {

    public static void main(String[] args) {

        // Crear una instancia de DiscountManager
        DiscountManager discountManager = DiscountManager.getInstance();

        // Precio original del producto
        int precioOriginal = 10000;

        // Aplicar un descuento usando un código promocional
        String codigoPromocional = "PROMO20";
        double precioConDescuento = discountManager.aplicarDescuento(precioOriginal, codigoPromocional);

        // Mostrar el precio con descuento
        System.out.println("Precio original: " + precioOriginal);
        System.out.printf("Precio con descuento (%s): %.0f%n", codigoPromocional, precioConDescuento);


    }
}
