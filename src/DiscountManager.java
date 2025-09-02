/*

  Clase DiscountManager que implementa Singleton y utiliza los patrones Decorator y Command
  con validaciones mejoradas

 */
public class DiscountManager {
    private static DiscountManager instance;
    private Invoker invoker;

    private DiscountManager() {
        this.invoker = new Invoker();
    }

    public static DiscountManager getInstance() {
        if (instance == null) {
            instance = new DiscountManager();
        }
        return instance;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    // Metodo original mantenido para compatibilidad
    public double aplicarDescuento(int precio, String codigoPromocional) {

        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        double descuento;
        switch (codigoPromocional.toUpperCase()) {
            case "PROMO10":
                descuento = 0.10;
                break;
            case "PROMO20":
                descuento = 0.20;
                break;
            case "PROMO30":
                descuento = 0.30;
                break;
            default:
                System.out.println(" Código promocional no válido: " + codigoPromocional);
                return precio;
        }

        double precioFinal = precio - (precio * descuento);
        if (precioFinal < 0) {
            throw new IllegalStateException("El precio final no puede ser negativo");
        }

        return precioFinal;
    }

    // Nuevos métodos que utilizan Decorator y Command
    public Component aplicarDescuentoGeneral(Component producto, double porcentaje) {
        Command comando = new DescuentoGeneralCommand(producto, porcentaje);
        return invoker.ejecutarComando(comando);
    }

    public Component aplicarDescuentoPorCategoria(Component producto, double porcentaje, String categoria) {
        Command comando = new DescuentoPorCategoriaCommand(producto, porcentaje, categoria);
        return invoker.ejecutarComando(comando);
    }

    public Component aplicarDescuentoCombo(Component producto, double descuentoGeneral, double descuentoCategoria, String categoriaEspecial) {
        System.out.println("\n APLICANDO COMBO DE DESCUENTOS:");
        // Aplicar descuento general
        Component conDescuentoGeneral = aplicarDescuentoGeneral(producto, descuentoGeneral);

        // Aplicar descuento adicional por categoría
        return aplicarDescuentoPorCategoria(conDescuentoGeneral, descuentoCategoria, categoriaEspecial);
    }

    public Component deshacerUltimaOperacion() {
        return invoker.deshacerUltimoComando();
    }

    public Component rehacerUltimaOperacion() {
        return invoker.rehacerUltimoComando();
    }

    public void mostrarHistorialDescuentos() {
        invoker.mostrarHistorial();
    }
}