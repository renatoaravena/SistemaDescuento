/**
 * Decorador que aplica un descuento general a TODOS los productos
 * con validación de porcentaje de descuento.
 */
public class DescuentoGeneralDecorator extends Decorator {
    private final double porcentajeDescuento;

    public DescuentoGeneralDecorator(Component componente, double porcentajeDescuento) {
        super(componente);

        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }

        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double getPrecio() {
        double precioBase = super.getPrecio();
        double precioConDescuento = precioBase * (1 - porcentajeDescuento/100);

        // Validar que el precio no sea negativo después del descuento
        if (precioConDescuento < 0) {
            throw new IllegalStateException("El precio no puede ser negativo después del descuento");
        }

        return precioConDescuento;
    }

    @Override
    public String getNombre() {
        return super.getNombre() + " [DESCUENTO GENERAL: " + porcentajeDescuento + "%]";
    }
}