/*

  Clase concreta que implementa la interfaz Component.
  Representa los productos base de la tienda sin descuentos aplicados.
  Contiene la lógica base para obtener precio y descripción.

 */


public class Producto implements Component {
    private final String nombre;
    private final double precioBase;

    public Producto(String nombre, double precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    @Override
    public double getPrecio() {
        return precioBase;
    }

    @Override
    public String getDescripcion() {
        return nombre;
    }
}