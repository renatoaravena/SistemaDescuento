import java.util.List;

public class ProductController {
    private List<Component> products;
    private ProductView view;

    public ProductController(List<Component> products, ProductView view) {
        this.products = products;
        this.view = view;
    }

    public void displayAllProducts() {
        view.displayProducts(products);
    }

    public void displayProductDetails(int index) {
        if (index >= 0 && index < products.size()) {
            view.displayProductDetails(products.get(index));
        } else {
            System.out.println("Índice de producto inválido");
        }
    }

    public Component getProductById(String id) {
        for (Component product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Component getProductByIndex(int index) {
        if (index >= 0 && index < products.size()) {
            return products.get(index);
        }
        return null;
    }

    public List<Component> getAllProducts() {
        return products;
    }
}
