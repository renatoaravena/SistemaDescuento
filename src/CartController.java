public class CartController {
    private Pedidos cart;
    private CartView view;
    private DiscountManager discountManager;

    public CartController(Pedidos cart, CartView view) {
        this.cart = cart;
        this.view = view;
        this.discountManager = DiscountManager.getInstance();
    }

    public void addToCart(Component product) {
        cart.addProduct(product);
        view.displayAddToCartSuccess(product.getNombre());
        view.displayCart(cart);
    }

    public void removeFromCart(int index) {
        if (index >= 0 && index < cart.getProducts().size()) {
            Component product = cart.getProducts().get(index);
            cart.removeProduct(product);
            view.displayRemoveFromCartSuccess(product.getNombre());
            view.displayCart(cart);
        } else {
            System.out.println("Índice de producto inválido");
        }
    }

    public void displayCart() {
        if (cart.getProducts().isEmpty()) {
            view.displayCartEmpty();
        } else {
            view.displayCart(cart);
        }
    }

    public void clearCart() {
        cart.getProducts().clear();
        cart.setTotal(0.0);
        System.out.println("Carrito vaciado");
    }

    public Pedidos getCart() {
        return cart;
    }
}
