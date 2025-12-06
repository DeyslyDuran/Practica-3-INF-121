package EJERCICIO3;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE PRODUCTOS ===\n");

        // a) Crear instancia del archivo
        ArchivoProducto archivo = new ArchivoProducto("productos.json");

        // Crear archivo
        System.out.println("--- Crear archivo ---");
        archivo.crearArchivo();

        // b) Guardar productos
        System.out.println("\n--- b) Guardar productos ---");
        archivo.guardaProducto(new Producto(101, "Laptop HP", 4500.50f));
        archivo.guardaProducto(new Producto(102, "Mouse Logitech", 85.99f));
        archivo.guardaProducto(new Producto(103, "Teclado Mecánico", 320.00f));
        archivo.guardaProducto(new Producto(104, "Monitor Samsung 27\"", 1850.75f));
        archivo.guardaProducto(new Producto(105, "Auriculares Sony", 450.00f));
        archivo.guardaProducto(new Producto(106, "Webcam HD", 280.50f));
        archivo.guardaProducto(new Producto(107, "USB 64GB", 95.00f));

        // Listar todos los productos
        archivo.listarProductos();

        // c) Buscar producto por código
        System.out.println("\n--- c) Buscar producto por código ---");
        archivo.buscaProducto(103);
        archivo.buscaProducto(999); // Producto que no existe

        // d) Calcular promedio de precios
        System.out.println("\n--- d) Calcular promedio de precios ---");
        double promedio = archivo.calcularPromedioPrecios();

        // e) Mostrar producto más caro
        System.out.println("\n--- e) Mostrar producto más caro ---");
        archivo.mostrarProductoMasCaro();

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}
