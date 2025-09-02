import java.util.ArrayList;
import java.util.List;

public class Pedidos {
    private String id;
    private String userId;
    private List<Component> products;
    private double total;

    public Pedidos(String id, String userId) {
        this.id = id;
        this.userId = userId;
        this.products = new ArrayList<>();
        this.total = 0.0;
    }

    public void addProduct(Component product) {
        products.add(product);
        total += product.getPrecio();
    }

    public void removeProduct(Component product) {
        if (products.remove(product)) {
            total -= product.getPrecio();
        }
    }

    // Getters y setters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public List<Component> getProducts() { return products; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Order{id='" + id + "', userId='" + userId + "', total=" + total + ", products=" + products.size() + "}";
    }
}
