package EJERCICIO6;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===\n");

        // Crear archivos
        ArchiLibro archiLibro = new ArchiLibro("libros.json");
        ArchCliente archCliente = new ArchCliente("clientes.json");
        ArchPrestamo archPrestamo = new ArchPrestamo("prestamos.json");

        archiLibro.crearArchivo();
        archCliente.crearArchivo();
        archPrestamo.crearArchivo();

        // Agregar libros
        archiLibro.agregarLibro(new Libro(101, "Cien Años de Soledad", 150.0));
        archiLibro.agregarLibro(new Libro(102, "Don Quijote", 120.0));
        archiLibro.agregarLibro(new Libro(103, "El Principito", 80.0));
        archiLibro.agregarLibro(new Libro(104, "1984", 95.0));
        archiLibro.agregarLibro(new Libro(105, "Orgullo y Prejuicio", 110.0));
        archiLibro.agregarLibro(new Libro(106, "Crimen y Castigo", 140.0));

        // Agregar clientes
        archCliente.agregarCliente(new Cliente(1, "12345678", "Juan", "Pérez"));
        archCliente.agregarCliente(new Cliente(2, "23456789", "María", "López"));
        archCliente.agregarCliente(new Cliente(3, "34567890", "Carlos", "García"));
        archCliente.agregarCliente(new Cliente(4, "45678901", "Ana", "Martínez"));

        // Agregar préstamos
        archPrestamo.agregarPrestamo(new Prestamo(1, 101, "2024-01-15", 2));
        archPrestamo.agregarPrestamo(new Prestamo(2, 102, "2024-01-20", 1));
        archPrestamo.agregarPrestamo(new Prestamo(1, 103, "2024-02-10", 3));
        archPrestamo.agregarPrestamo(new Prestamo(3, 101, "2024-02-15", 1));
        archPrestamo.agregarPrestamo(new Prestamo(2, 104, "2024-03-05", 2));
        archPrestamo.agregarPrestamo(new Prestamo(4, 102, "2024-03-10", 1));
        archPrestamo.agregarPrestamo(new Prestamo(1, 101, "2024-03-20", 1));
        archPrestamo.agregarPrestamo(new Prestamo(3, 103, "2024-04-01", 2));

        // a) Listar los libros cuyo precio estén entre 2 valores
        archPrestamo.listarLibrosPorRangoPrecio(80.0, 120.0, archiLibro);

        // b) Calcular el ingreso total generado por un libro específico
        archPrestamo.calcularIngresoTotalLibro(101, archiLibro);

        // c) Mostrar la lista de libros que nunca fueron vendidos
        archPrestamo.mostrarLibrosNuncaVendidos(archiLibro);

        // d) Mostrar a todos los clientes que compraron un libro específico
        archPrestamo.mostrarClientesPorLibro(101, archCliente);

        // e) Definir el libro más prestado
        archPrestamo.definirLibroMasPrestado(archiLibro);

        // f) Mostrar el cliente que tuvo más préstamos
        archPrestamo.mostrarClienteMasPrestamos(archCliente);

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}