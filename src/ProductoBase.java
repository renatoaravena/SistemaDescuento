/*

  Clase concreta que implementa la interfaz Component.
  Representa los productos base de la tienda sin descuentos aplicados.
  Contiene la lógica base para obtener precio y descripción.

 */


public class ProductoBase implements Component {
    private final String id;
    private final String nombre;
    private final double precioBase;
    private final String categoria;

    public ProductoBase(String id, String nombre, double precioBase, String categoria) {
//        Validaciones basicas

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío.");
        }

        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }



        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.categoria = categoria;

    }

    @Override
    public double getPrecio() {
        return precioBase;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getCategoria() {
        return categoria;
    }

    @Override
    public String getId() {
        return id;
    }
}