public class AplicarDescuentoPorcentajeCommand implements Command {
    private final Component producto;
    private final double porcentaje;

    public AplicarDescuentoPorcentajeCommand(Component producto, double porcentaje) {
        this.producto = producto;
        this.porcentaje = porcentaje;
    }

    @Override
    public Component ejecutar() {
        return new DescuentoPorcentajeDecorator(producto, porcentaje);
    }


}