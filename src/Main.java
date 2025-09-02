import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Component> productosDisponibles = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioActual;
    private static Pedidos carritoActual;
    private static ProductController productController;
    private static CartController cartController;
    private static DiscountController discountController;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarDatos();
        mostrarMenuPrincipal();
    }

    private static void inicializarDatos() {
        // Crear usuarios
        usuarios.add(new Usuario("U001", "Juan Pérez", "juan@email.com"));
        usuarios.add(new Usuario("U002", "María García", "maria@email.com"));
        usuarioActual = usuarios.get(0);

        // Crear productos
        productosDisponibles.add(new ProductoBase("P001", "Camisa Elegante", 25000, "Ropa"));
        productosDisponibles.add(new ProductoBase("P002", "Laptop Gamer", 1500000, "Electrónica"));
        productosDisponibles.add(new ProductoBase("P003", "Zapatos de Cuero", 80000, "Calzado"));
        productosDisponibles.add(new ProductoBase("P004", "Libro de Programación", 35000, "Libros"));
        productosDisponibles.add(new ProductoBase("P005", "Pantalón Jeans", 45000, "Ropa"));
        productosDisponibles.add(new ProductoBase("P006", "Smartphone", 800000, "Electrónica"));

        // Inicializar carrito
        carritoActual = new Pedidos("CART001", usuarioActual.getId());

        // Inicializar controladores
        ProductView productView = new ProductView();
        CartView cartView = new CartView();
        DiscountView discountView = new DiscountView();

        productController = new ProductController(productosDisponibles, productView);
        cartController = new CartController(carritoActual, cartView);
        discountController = new DiscountController(discountView);
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ver productos disponibles");
            System.out.println("2. Ver carrito de compras");
            System.out.println("3. Aplicar descuentos");
            System.out.println("4. Gestionar usuario");
            System.out.println("5. Demo patrones de diseño");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    menuProductos();
                    break;
                case 2:
                    menuCarrito();
                    break;
                case 3:
                    menuDescuentos();
                    break;
                case 4:
                    menuUsuario();
                    break;
                case 5:
                    ejecutarDemoPatrones();
                    break;
                case 0:
                    System.out.println("¡Gracias por usar nuestro sistema!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuProductos() {
        int opcion;
        do {
            System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
            productController.displayAllProducts();

            System.out.println("\n1. Ver detalles de producto");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el número del producto: ");
                    int index = scanner.nextInt() - 1;
                    productController.displayProductDetails(index);
                    break;
                case 2:
                    System.out.print("Ingrese el número del producto a agregar: ");
                    int indexProducto = scanner.nextInt() - 1;
                    Component producto = productController.getProductByIndex(indexProducto);
                    if (producto != null) {
                        cartController.addToCart(producto);
                    } else {
                        System.out.println("Producto no válido.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuCarrito() {
        int opcion;
        do {
            System.out.println("\n=== CARRITO DE COMPRAS ===");
            cartController.displayCart();

            System.out.println("\n1. Remover producto del carrito");
            System.out.println("2. Vaciar carrito");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el número del producto a remover: ");
                    int index = scanner.nextInt() - 1;
                    cartController.removeFromCart(index);
                    break;
                case 2:
                    cartController.clearCart();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuDescuentos() {
        int opcion;
        do {
            discountController.showDiscountOptions();
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion >= 1 && opcion <= 3) {
                aplicarDescuento(opcion);
            } else if (opcion != 4) {
                System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void aplicarDescuento(int tipoDescuento) {
        cartController.displayCart();
        if (carritoActual.getProducts().isEmpty()) {
            System.out.println("El carrito está vacío. Agregue productos primero.");
            return;
        }

        System.out.print("Seleccione el número del producto para aplicar descuento: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= carritoActual.getProducts().size()) {
            System.out.println("Índice de producto no válido.");
            return;
        }

        Component productoOriginal = carritoActual.getProducts().get(index);
        Component productoCopia = new ProductoBase(
                productoOriginal.getId(),
                productoOriginal.getNombre(),
                productoOriginal.getPrecio(),
                productoOriginal.getCategoria()
        );

        Component productoConDescuento = null;

        switch (tipoDescuento) {
            case 1: // Descuento general
                System.out.print("Ingrese el porcentaje de descuento: ");
                double porcentajeGeneral = scanner.nextDouble();
                productoConDescuento = discountController.applyGeneralDiscount(productoCopia, porcentajeGeneral);
                break;

            case 2: // Descuento por categoría
                System.out.print("Ingrese el porcentaje de descuento: ");
                double porcentajeCategoria = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Ingrese la categoría: ");
                String categoria = scanner.nextLine();
                productoConDescuento = discountController.applyCategoryDiscount(productoCopia, porcentajeCategoria, categoria);
                break;

            case 3: // Combo de descuentos
                System.out.print("Ingrese el porcentaje de descuento general: ");
                double descGeneral = scanner.nextDouble();
                System.out.print("Ingrese el porcentaje de descuento por categoría: ");
                double descCategoria = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Ingrese la categoría especial: ");
                String catEspecial = scanner.nextLine();
                productoConDescuento = discountController.applyComboDiscount(productoCopia, descGeneral, descCategoria, catEspecial);
                break;
        }

        if (productoConDescuento != null) {
            // Reemplazar el producto en el carrito
            carritoActual.getProducts().set(index, productoConDescuento);
            // Recalcular total
            double nuevoTotal = 0;
            for (Component p : carritoActual.getProducts()) {
                nuevoTotal += p.getPrecio();
            }
            carritoActual.setTotal(nuevoTotal);

            discountController.showPriceComparison(productoOriginal, productoConDescuento);
        }
    }

    private static void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n=== INFORMACIÓN DE USUARIO ===");
            System.out.println("Usuario actual: " + usuarioActual.getName());
            System.out.println("Email: " + usuarioActual.getEmail());

            System.out.println("\n1. Cambiar información de usuario");
            System.out.println("2. Seleccionar otro usuario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    cambiarInformacionUsuario();
                    break;
                case 2:
                    seleccionarUsuario();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void cambiarInformacionUsuario() {
        System.out.print("Nuevo nombre (" + usuarioActual.getName() + "): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.trim().isEmpty()) {
            usuarioActual.setName(nuevoNombre);
        }

        System.out.print("Nuevo email (" + usuarioActual.getEmail() + "): ");
        String nuevoEmail = scanner.nextLine();
        if (!nuevoEmail.trim().isEmpty()) {
            usuarioActual.setEmail(nuevoEmail);
        }

        System.out.println("Información actualizada correctamente.");
    }

    private static void seleccionarUsuario() {
        System.out.println("\n=== SELECCIONAR USUARIO ===");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i+1) + ". " + usuarios.get(i).getName());
        }
        System.out.print("Seleccione un usuario: ");

        int seleccion = scanner.nextInt() - 1;
        scanner.nextLine();

        if (seleccion >= 0 && seleccion < usuarios.size()) {
            usuarioActual = usuarios.get(seleccion);
            // Crear nuevo carrito para el usuario
            carritoActual = new Pedidos("CART" + (System.currentTimeMillis() % 10000), usuarioActual.getId());
            cartController = new CartController(carritoActual, new CartView());
            System.out.println("Usuario cambiado a: " + usuarioActual.getName());
        } else {
            System.out.println("Selección no válida.");
        }
    }

    private static void ejecutarDemoPatrones() {
        System.out.println("\n=== DEMOSTRACIÓN DE PATRONES DE DISEÑO ===");

        // Obtener instancia del DiscountManager
        DiscountManager discountManager = DiscountManager.getInstance();

        // Crear productos de diferentes categorías
        Component camisa = new ProductoBase("P001", "Camisa Elegante", 25000, "Ropa");
        Component laptop = new ProductoBase("P002", "Laptop Gamer", 1500000, "Electrónica");
        Component zapatos = new ProductoBase("P003", "Zapatos de Cuero", 80000, "Calzado");
        Component libro = new ProductoBase("P004", "Libro de Programación", 35000, "Libros");

        System.out.println("=== DEMO 1: APLICACIÓN BÁSICA DE DESCUENTOS ===");
        demoAplicacionBasica(discountManager, camisa, laptop);

        System.out.println("\n\n=== DEMO 2: INVOKER (DESHACER/REHACER) ===");
        demoComandosInvoker(discountManager, zapatos);

        System.out.println("\n\n=== DEMO 3: VALIDACIONES Y MANEJO DE ERRORES ===");
        demoValidaciones(discountManager);

        System.out.println("\n\n=== DEMO 4: DESCUENTOS COMBINADOS ===");
        demoComboComplejo(discountManager, libro);

        System.out.println("\n\n=== DEMO 5: HISTORIAL DE OPERACIONES ===");
        discountManager.mostrarHistorialDescuentos();
    }

    private static void demoAplicacionBasica(DiscountManager discountManager, Component... productos) {
        System.out.println("\n1.1. Aplicando descuento general del 15% a todos los productos:");
        for (Component producto : productos) {
            Component conDescuento = discountManager.aplicarDescuentoGeneral(producto, 15);
            mostrarInfoProducto(conDescuento);
        }

        System.out.println("\n1.2. Aplicando descuento adicional del 25% solo para categoría 'Ropa':");
        for (Component producto : productos) {
            Component conDescuentoEspecial = discountManager.aplicarDescuentoPorCategoria(producto, 25, "Ropa");
            mostrarInfoProducto(conDescuentoEspecial);
        }
    }

    private static void demoComandosInvoker(DiscountManager manager, Component producto) {
        System.out.println("\n2.1. Producto original:");
        mostrarInfoProducto(producto);

        System.out.println("\n2.2. Aplicando descuento general del 10%:");
        Component conDescuento1 = manager.aplicarDescuentoGeneral(producto, 10);
        mostrarInfoProducto(conDescuento1);

        System.out.println("\n2.3. Aplicando descuento adicional del 15% para calzado:");
        Component conDescuento2 = manager.aplicarDescuentoPorCategoria(conDescuento1, 15, "Calzado");
        mostrarInfoProducto(conDescuento2);

        System.out.println("\n2.4. Deshaciendo última operación:");
        Component deshecho = manager.deshacerUltimaOperacion();
        mostrarInfoProducto(deshecho);

        System.out.println("\n2.5. Rehaciendo operación:");
        Component rehecho = manager.rehacerUltimaOperacion();
        mostrarInfoProducto(rehecho);
    }

    private static void demoValidaciones(DiscountManager manager) {
        System.out.println("\n3.1. Probando validaciones:");

        try {
            // Precio negativo
            Component productoInvalido = new ProductoBase("P999", "Producto Inválido", -100, "Test");
        } catch (IllegalArgumentException e) {
            System.out.println(" Capturado error: " + e.getMessage());
        }

        try {
            // Descuento inválido
            Component producto = new ProductoBase("P005", "Producto Test", 10000, "Test");
            manager.aplicarDescuentoGeneral(producto, 150);
        } catch (IllegalArgumentException e) {
            System.out.println(" Capturado error: " + e.getMessage());
        }

        try {
            // Código promocional inválido
            double resultado = manager.aplicarDescuento(10000, "PROMO_INVALIDO");
            System.out.println(" Manejo de código inválido: Precio mantiene en $" + resultado);
        } catch (Exception e) {
            System.out.println(" Capturado error: " + e.getMessage());
        }
    }

    private static void demoComboComplejo(DiscountManager manager, Component producto) {
        System.out.println("\n4.1. Producto original:");
        mostrarInfoProducto(producto);

        System.out.println("\n4.2. Aplicando combo de descuentos (10% general + 20% para libros):");
        Component conCombo = manager.aplicarDescuentoCombo(producto, 10, 20, "Libros");
        mostrarInfoProducto(conCombo);

        System.out.println("\n4.3. Intentando aplicar combo a categoría diferente:");
        Component conComboFallido = manager.aplicarDescuentoCombo(producto, 10, 20, "Electrónica");
        mostrarInfoProducto(conComboFallido);
    }

    private static void mostrarInfoProducto(Component producto) {
        System.out.printf("    %s (ID: %s) | Categoría: %s | Precio: $%,.0f%n",
                producto.getNombre(),
                producto.getId(),
                producto.getCategoria(),
                producto.getPrecio());
    }
}