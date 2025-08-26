/*
 Comando concreto que aplica un descuento fijo a un producto.
 Implementa el patrón Command conteniendo la lógica de aplicar
 un descuento de cantidad fija como un ejecutable.
 */
public class AplicarDescuentoFijoCommand implements Command {
    private final Component producto;
    private final double descuentoFijo;

    public AplicarDescuentoFijoCommand(Component producto, double descuentoFijo) {
        this.producto = producto;
        this.descuentoFijo = descuentoFijo;
    }

    @Override
    public Component ejecutar() {
        return new DescuentoFijoDecorator(producto, descuentoFijo);
    }

}