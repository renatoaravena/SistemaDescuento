/**
 * Comando que aplica un descuento específico por categoría
 */
public class DescuentoPorCategoriaCommand implements Command {
    private final Component productoOriginal;
    private final double porcentajeDescuento;
    private final String categoriaAplicable;

    public DescuentoPorCategoriaCommand(Component producto, double porcentajeDescuento, String categoriaAplicable) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        this.productoOriginal = producto;
        this.porcentajeDescuento = porcentajeDescuento;
        this.categoriaAplicable = categoriaAplicable;
    }

    @Override
    public Component ejecutar() {
        Component producto = new DescuentoPorCategoriaDecorator(productoOriginal, porcentajeDescuento, categoriaAplicable);
        System.out.println(" Aplicado descuento de " + porcentajeDescuento + "% para categoría " + categoriaAplicable);
        return producto;
    }

    @Override
    public String getNombre() {
        return "Descuento " + porcentajeDescuento + "% para " + categoriaAplicable;
    }

    @Override
    public void deshacer() {
        System.out.println(" Deshaciendo descuento de " + porcentajeDescuento + "% para categoría " + categoriaAplicable);
    }

    public Component getProductoOriginal() {
        return  productoOriginal;
    }
}