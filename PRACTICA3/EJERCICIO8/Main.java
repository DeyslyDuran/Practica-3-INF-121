package EJERCICIO8;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE REFRIGERADOR ===\n");

        // Crear instancia del archivo
        ArchRefri refri = new ArchRefri("alimentos.json");

        // Crear archivo
        System.out.println("--- Crear archivo ---");
        refri.crearArchivo();

        // a) Crear - Guardar alimentos
        System.out.println("\n--- a) Guardar alimentos ---");
        refri.guardarAlimento(new Alimento("Leche", "15/12/2024", 2));
        refri.guardarAlimento(new Alimento("Pan", "01/12/2024", 5));
        refri.guardarAlimento(new Alimento("Queso", "20/12/2024", 0));
        refri.guardarAlimento(new Alimento("Yogurt", "10/12/2024", 8));
        refri.guardarAlimento(new Alimento("Jamón", "25/12/2024", 3));
        refri.guardarAlimento(new Alimento("Mantequilla", "30/11/2024", 1));
        refri.guardarAlimento(new Alimento("Huevos", "18/12/2024", 12));

        // Listar todos los alimentos
        refri.listarAlimentos();

        // a) Modificar alimento por nombre
        System.out.println("\n--- a) Modificar alimento por nombre ---");
        refri.modificarAlimento("Pan", "Pan Integral");

        // a) Eliminar alimento por nombre
        System.out.println("\n--- a) Eliminar alimento por nombre ---");
        refri.eliminarAlimento("Mantequilla");

        // Listar después de modificaciones
        refri.listarAlimentos();

        // b) Mostrar alimentos caducados antes de una fecha
        System.out.println("\n--- b) Alimentos caducados antes de 05/12/2024 ---");
        refri.mostrarCaducadosAntesDe("05/12/2024");

        // c) Eliminar alimentos con cantidad 0
        System.out.println("\n--- c) Eliminar alimentos con cantidad 0 ---");
        refri.eliminarCantidadCero();

        // Listar después de eliminar cantidad 0
        refri.listarAlimentos();

        // d) Buscar alimentos ya vencidos (fecha actual)
        System.out.println("\n--- d) Buscar alimentos vencidos ---");
        refri.buscarVencidos();

        // e) Mostrar alimento con mayor cantidad
        System.out.println("\n--- e) Alimento con mayor cantidad ---");
        refri.mostrarMayorCantidad();

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}