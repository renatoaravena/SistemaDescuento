/*
  Decorador concreto que aplica un descuento de cantidad fija al producto.
  Extiende la funcionalidad base del Component restando una cantidad fija
  al precio original.
 */

public class DescuentoFijoDecorator extends Decorator {
    private final double descuentoFijo;

    public DescuentoFijoDecorator(Component componente, double descuentoFijo) {
        super(componente);
        this.descuentoFijo = descuentoFijo;
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() - descuentoFijo;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " con descuento fijo de $" + descuentoFijo;
    }
}