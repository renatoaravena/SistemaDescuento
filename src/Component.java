/*
 * Interfaz Component define los métodos comunes para todos los componentes
 * en el patrón Decorator. Esta interfaz representa los productos de la tienda
 * y define las operaciones básicas que deben implementar todos los componentes(Productos).
 */

public interface Component {

//     Obtiene el precio del producto

    double getPrecio();


//     Obtiene la descripción del producto

    String getDescripcion();
}
