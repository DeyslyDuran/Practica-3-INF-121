package EJERCICIO9;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE ZOOLÓGICOS ===\n");

        // Crear instancia del archivo
        ArchZoo archZoo = new ArchZoo("zoologicos.json");

        // Crear archivo
        System.out.println("--- Crear archivo ---");
        archZoo.crearArchivo();

        // Crear zoológicos con animales
        System.out.println("\n--- a) Crear zoológicos ---");

        Zoologico zoo1 = new Zoologico(1, "Zoológico Central");
        zoo1.agregarAnimal(new Animal("Mamífero", "León", 3));
        zoo1.agregarAnimal(new Animal("Ave", "Águila", 2));
        zoo1.agregarAnimal(new Animal("Reptil", "Cocodrilo", 4));
        zoo1.agregarAnimal(new Animal("Mamífero", "Elefante", 2));

        Zoologico zoo2 = new Zoologico(2, "Bioparque Municipal");
        zoo2.agregarAnimal(new Animal("Ave", "Loro", 5));
        zoo2.agregarAnimal(new Animal("Mamífero", "Tigre", 2));

        Zoologico zoo3 = new Zoologico(3, "Reserva Natural");
        zoo3.agregarAnimal(new Animal("Mamífero", "Oso", 1));
        zoo3.agregarAnimal(new Animal("Ave", "Cóndor", 3));
        zoo3.agregarAnimal(new Animal("Reptil", "Serpiente", 6));
        zoo3.agregarAnimal(new Animal("Mamífero", "León", 2));
        zoo3.agregarAnimal(new Animal("Ave", "Tucán", 4));

        Zoologico zoo4 = new Zoologico(4, "Centro de Rescate");
        // Zoo vacío

        archZoo.guardarZoologico(zoo1);
        archZoo.guardarZoologico(zoo2);
        archZoo.guardarZoologico(zoo3);
        archZoo.guardarZoologico(zoo4);

        // Listar todos los zoológicos
        archZoo.listarZoologicos();

        // a) Modificar zoológico
        System.out.println("\n--- a) Modificar zoológico ---");
        archZoo.modificarZoologico(2, "Bioparque Metropolitano");

        // b) Listar zoológicos con mayor variedad
        System.out.println("\n--- b) Zoológicos con mayor variedad ---");
        archZoo.listarMayorVariedad();

        // c) Listar y eliminar zoológicos vacíos
        System.out.println("\n--- c) Listar y eliminar zoológicos vacíos ---");
        archZoo.listarYEliminarVacios();

        // Listar después de eliminar vacíos
        archZoo.listarZoologicos();

        // d) Mostrar animales de una especie
        System.out.println("\n--- d) Mostrar animales de especie Mamífero ---");
        archZoo.mostrarAnimalesPorEspecie("Mamífero");

        System.out.println("\n--- d) Mostrar animales de especie Ave ---");
        archZoo.mostrarAnimalesPorEspecie("Ave");

        // e) Mover animales de un zoológico a otro
        System.out.println("\n--- e) Mover animales de zoológico 2 a zoológico 1 ---");
        archZoo.moverAnimales(2, 1);

        // Listar después de mover animales
        archZoo.listarZoologicos();

        // a) Eliminar zoológico
        System.out.println("\n--- a) Eliminar zoológico ---");
        archZoo.eliminarZoologico(2);

        // Listar final
        archZoo.listarZoologicos();

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}