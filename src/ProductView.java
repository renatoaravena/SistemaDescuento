import java.util.List;

public class ProductView {
    public void displayProducts(List<Component> products) {
        System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
        for (int i = 0; i < products.size(); i++) {
            Component product = products.get(i);
            System.out.printf("%d. %s (ID: %s) | Categoría: %s | Precio: $%,.0f%n",
                    i + 1, product.getNombre(), product.getId(),
                    product.getCategoria(), product.getPrecio());
        }
    }

    public void displayProductDetails(Component product) {
        System.out.println("\n=== DETALLES DEL PRODUCTO ===");
        System.out.printf("Nombre: %s%n", product.getNombre());
        System.out.printf("ID: %s%n", product.getId());
        System.out.printf("Categoría: %s%n", product.getCategoria());
        System.out.printf("Precio: $%,.0f%n", product.getPrecio());
    }
}
