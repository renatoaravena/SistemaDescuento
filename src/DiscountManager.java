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

//    Metodo para calcular el descuento, se vera el decuento como si fueran promos al ingresar un codigo
    public double aplicarDescuento(int precio, String codigoPromocional) {

        double descuento;

        // Simulamos un descuento basado en el código promocional
        switch (codigoPromocional) {
            case "PROMO10":
                descuento = 0.10; // 10% de descuento
                break;
            case "PROMO20":
                descuento = 0.20; // 20% de descuento
                break;
            case "PROMO30":
                descuento = 0.30; // 30% de descuento
                break;
            default:
                System.out.println("Código promocional no válido.");
                return precio; // Sin descuento
        }

        return precio - (precio * descuento); //Retorna un double por lo que el precio se veria 1050.00 arreglar con stringf

    }
}
