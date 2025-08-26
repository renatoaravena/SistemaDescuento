public class DiscountManager {

//    Instancia Unica
    private static DiscountManager instance;

//    Contructor privado
    private DiscountManager() {}

//    Metodo para obtener la instancia unica
    public static DiscountManager getInstance() {
        if (instance == null) {
            instance = new DiscountManager();
        }
        return instance;
    }

// Nuevo metodo que utiliza el patrón Decorator para aplicar descuentos

    public Component aplicarDescuento(Component producto, String codigoPromocional) {
        return switch (codigoPromocional) {
            case "PROMO10" -> new DescuentoPorcentajeDecorator(producto, 10);
            case "PROMO20" -> new DescuentoPorcentajeDecorator(producto, 20);
            case "PROMO30" -> new DescuentoPorcentajeDecorator(producto, 30);
            default -> {
                System.out.println("Código promocional no válido.");
                yield producto;
            }
        };
    }
}
