/*
  Decorador concreto que aplica un descuento porcentual al producto.
  Extiende la funcionalidad base del Component añadiendo un descuento
  calculado como porcentaje del precio original.
 */

public class DescuentoPorcentajeDecorator extends Decorator {
    private final double porcentajeDescuento;

    public DescuentoPorcentajeDecorator(Component componente, double porcentajeDescuento) {
        super(componente);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double getPrecio() {
        double precioBase = super.getPrecio();
        return precioBase - (precioBase * porcentajeDescuento / 100);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " con " + porcentajeDescuento + "% de descuento";
    }
}