public class DiscountView {
    public void displayDiscountOptions() {
        System.out.println("\n=== OPCIONES DE DESCUENTO ===");
        System.out.println("1. Descuento general (%)");
        System.out.println("2. Descuento por categoría");
        System.out.println("3. Combo de descuentos");
        System.out.println("4. Volver al menú principal");
    }

    public void displayDiscountApplied(String discountType, double discountAmount) {
        System.out.printf("\n✓ Descuento aplicado: %s (%.1f%%)%n", discountType, discountAmount);
    }

    public void displayDiscountError(String message) {
        System.out.println("\n✗ Error al aplicar descuento: " + message);
    }

    public void displayFinalPrice(double originalPrice, double finalPrice) {
        System.out.printf("Precio original: $%,.0f%n", originalPrice);
        System.out.printf("Precio final: $%,.0f%n", finalPrice);
        System.out.printf("Ahorro: $%,.0f%n", (originalPrice - finalPrice));
    }
}
