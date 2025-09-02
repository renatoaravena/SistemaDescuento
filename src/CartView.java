public class CartView {
    public void displayCart(Pedidos cart) {
        System.out.println("\n=== CARRITO DE COMPRAS ===");
        if (cart.getProducts().isEmpty()) {
            System.out.println("El carrito está vacío");
            return;
        }

        for (int i = 0; i < cart.getProducts().size(); i++) {
            Component product = cart.getProducts().get(i);
            System.out.printf("%d. %s | Precio: $%,.0f%n",
                    i + 1, product.getNombre(), product.getPrecio());
        }
        System.out.printf("TOTAL: $%,.0f%n", cart.getTotal());
    }

    public void displayCartEmpty() {
        System.out.println("\nEl carrito está vacío");
    }

    public void displayAddToCartSuccess(String productName) {
        System.out.println("\n✓ '" + productName + "' agregado al carrito");
    }

    public void displayRemoveFromCartSuccess(String productName) {
        System.out.println("\n✗ '" + productName + "' removido del carrito");
    }
}