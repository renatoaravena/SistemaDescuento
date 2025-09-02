/*
  Decorador que aplica un descuento adicional específico para una categoría
  con validación de parámetros.
 */
public class DescuentoPorCategoriaDecorator extends Decorator {
    private final double porcentajeDescuento;
    private final String categoriaAplicable;

    public DescuentoPorCategoriaDecorator(Component componente, double porcentajeDescuento, String categoriaAplicable) {
        super(componente);

        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }
        if (categoriaAplicable == null || categoriaAplicable.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría aplicable no puede estar vacía");
        }

        this.porcentajeDescuento = porcentajeDescuento;
        this.categoriaAplicable = categoriaAplicable;
    }

    @Override
    public double getPrecio() {
        double precioBase = super.getPrecio();

        if (categoriaAplicable.equalsIgnoreCase(super.getCategoria())) {
            double precioConDescuento = precioBase * (1 - porcentajeDescuento/100);

            if (precioConDescuento < 0) {
                throw new IllegalStateException("El precio no puede ser negativo después del descuento");
            }

            return precioConDescuento;
        } else {
            System.out.println(" La categoría del producto (" + super.getCategoria() + ") no coincide con la categoría aplicable (" + categoriaAplicable + "). No se aplica descuento.");
        }
        return precioBase;
    }

    @Override
    public String getNombre() {
        if (categoriaAplicable.equalsIgnoreCase(super.getCategoria())) {
            return super.getNombre() + " [DESCUENTO CATEGORÍA " + categoriaAplicable.toUpperCase() + ": " + porcentajeDescuento + "%]";
        }
        return super.getNombre();
    }
}
