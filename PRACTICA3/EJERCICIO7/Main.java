package EJERCICIO7;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE NIÑOS ===\n");

        // Crear instancia del archivo
        ArchNiño archivo = new ArchNiño("ninos.json");

        // Crear archivo
        System.out.println("--- Crear archivo ---");
        archivo.crearArchivo();

        // Guardar niños
        System.out.println("\n--- Guardar niños ---");
        archivo.guardarNiño(new Niño("Juan", "Pérez", "García", 12345, 8, "adecuado", "normal"));
        archivo.guardarNiño(new Niño("María", "López", "Martínez", 12346, 10, "bajo", "baja"));
        archivo.guardarNiño(new Niño("Pedro", "González", "Rodríguez", 12347, 7, "adecuado", "alta"));
        archivo.guardarNiño(new Niño("Ana", "Fernández", "Sánchez", 12348, 9, "alto", "normal"));
        archivo.guardarNiño(new Niño("Luis", "Ramírez", "Torres", 12349, 11, "adecuado", "alta"));
        archivo.guardarNiño(new Niño("Sofía", "Vargas", "Morales", 12350, 6, "bajo", "normal"));
        archivo.guardarNiño(new Niño("Carlos", "Mendoza", "Silva", 12351, 9, "normal", "alta"));

        // a) Listar todos los niños
        archivo.listarNiños();

        // b) Contar niños con peso adecuado
        System.out.println("\n--- b) Contar niños con peso adecuado ---");
        archivo.contarPesoAdecuado();

        // c) Mostrar niños con peso o talla inadecuada
        System.out.println("\n--- c) Niños con peso o talla inadecuada ---");
        archivo.mostrarPesoTallaInadecuada();

        // d) Calcular promedio de edad
        System.out.println("\n--- d) Calcular promedio de edad ---");
        archivo.calcularPromedioEdad();

        // e) Buscar niño por carnet
        System.out.println("\n--- e) Buscar niño por carnet ---");
        archivo.buscarPorCarnet(12345);
        archivo.buscarPorCarnet(99999); // Niño que no existe

        // f) Mostrar niños con talla más alta
        System.out.println("\n--- f) Mostrar niños con talla más alta ---");
        archivo.mostrarTallaMasAlta();

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}