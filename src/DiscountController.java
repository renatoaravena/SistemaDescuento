public class DiscountController {
    private DiscountView view;
    private DiscountManager discountManager;

    public DiscountController(DiscountView view) {
        this.view = view;
        this.discountManager = DiscountManager.getInstance();
    }

    public void showDiscountOptions() {
        view.displayDiscountOptions();
    }

    public Component applyGeneralDiscount(Component product, double percentage) {
        try {
            Component discountedProduct = discountManager.aplicarDescuentoGeneral(product, percentage);
            view.displayDiscountApplied("Descuento General", percentage);
            return discountedProduct;
        } catch (Exception e) {
            view.displayDiscountError(e.getMessage());
            return product;
        }
    }

    public Component applyCategoryDiscount(Component product, double percentage, String category) {
        try {
            Component discountedProduct = discountManager.aplicarDescuentoPorCategoria(product, percentage, category);
            view.displayDiscountApplied("Descuento por Categoría " + category, percentage);
            return discountedProduct;
        } catch (Exception e) {
            view.displayDiscountError(e.getMessage());
            return product;
        }
    }

    public Component applyComboDiscount(Component product, double generalDiscount, double categoryDiscount, String category) {
        try {
            Component discountedProduct = discountManager.aplicarDescuentoCombo(product, generalDiscount, categoryDiscount, category);
            view.displayDiscountApplied("Combo de Descuentos", generalDiscount + categoryDiscount);
            return discountedProduct;
        } catch (Exception e) {
            view.displayDiscountError(e.getMessage());
            return product;
        }
    }

    public void showPriceComparison(Component originalProduct, Component discountedProduct) {
        view.displayFinalPrice(originalProduct.getPrecio(), discountedProduct.getPrecio());
    }

}
