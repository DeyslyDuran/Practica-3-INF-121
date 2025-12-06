package EJERCICIO2;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE TRABAJADORES ===\n");

        // Crear instancia del archivo
        ArchivoTrabajador archivo = new ArchivoTrabajador();

        // a) Crear archivo
        System.out.println("--- a) Crear archivo ---");
        archivo.crearArchivo();

        // b) Guardar trabajadores
        System.out.println("\n--- b) Guardar trabajadores ---");
        archivo.guardarTrabajador(new Trabajador("Juan Pérez", 1001, 5000.0));
        archivo.guardarTrabajador(new Trabajador("María García", 1002, 6500.0));
        archivo.guardarTrabajador(new Trabajador("Carlos López", 1003, 4500.0));
        archivo.guardarTrabajador(new Trabajador("Ana Martínez", 1004, 7200.0));
        archivo.guardarTrabajador(new Trabajador("Pedro Rodríguez", 1005, 5800.0));

        // Listar todos los trabajadores
        archivo.listarTrabajadores();

        // c) Aumentar salario
        System.out.println("\n--- c) Aumentar salario ---");

        // Aumentar con nuevo salario específico
        Trabajador nuevoSalario = new Trabajador("", 0, 7000.0);
        archivo.aumentaSalario(1001, nuevoSalario);

        // Aumentar con porcentaje
        archivo.aumentaSalario(1003, 15.0); // 15% de aumento

        archivo.listarTrabajadores();

        // d) Buscar trabajador con mayor salario
        System.out.println("\n--- d) Buscar trabajador con mayor salario ---");
        archivo.buscarMayorSalario();

        // e) Ordenar por salario
        System.out.println("\n--- e) Ordenar trabajadores por salario ---");
        archivo.ordenarPorSalario();

        // Búsqueda por carnet
        System.out.println("\n--- Búsqueda por carnet ---");
        Trabajador buscado = archivo.buscarPorCarnet(1002);
        if (buscado != null) {
            System.out.println("Trabajador encontrado: " + buscado);
        } else {
            System.out.println("Trabajador no encontrado.");
        }

        System.out.println("\n===============");
    }
}