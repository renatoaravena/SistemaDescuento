/**
 * Comando que aplica un descuento general a un producto
 * Versión corregida y optimizada
 */
public class DescuentoGeneralCommand implements Command {
    private final Component productoOriginal;
    private final double porcentajeDescuento;

    public DescuentoGeneralCommand(Component productoOriginal, double porcentajeDescuento) {
        if (productoOriginal == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }

        this.productoOriginal = productoOriginal;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public Component ejecutar() {
        Component productoConDescuento = new DescuentoGeneralDecorator(productoOriginal, porcentajeDescuento);
        System.out.println(" Aplicado descuento general del " + porcentajeDescuento + "%");
        return productoConDescuento;
    }

    @Override
    public String getNombre() {
        return "Descuento General " + porcentajeDescuento + "%";
    }

    @Override
    public void deshacer() {
        // El deshacer simplemente notifica, ya que no modificamos el estado original
        System.out.println(" Deshaciendo descuento general del " + porcentajeDescuento + "%");
    }

    public Component getProductoOriginal() {
        return productoOriginal;
    }
}