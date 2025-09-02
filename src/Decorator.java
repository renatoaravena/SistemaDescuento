/*
  Clase abstracta Decorator que implementa la interfaz Component.
  Es la base de todos los decoradores concretos
 */

public abstract class Decorator implements Component {
    protected Component componente;

    public Decorator(Component componente) {

        if (componente == null){
            throw new IllegalArgumentException("El componente no puede ser nulo.");
        }

        this.componente = componente;
    }

    @Override
    public double getPrecio() {
        return componente.getPrecio();
    }

    @Override
    public String getNombre() {
        return componente.getNombre();
    }

    @Override
    public String getCategoria() {
        return componente.getCategoria();
    }

    @Override
    public String getId() {
        return componente.getId();
    }

}