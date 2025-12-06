package EJERCICIO9;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArchZoo {
    private String nombre;
    private List<Zoologico> zoologicos;
    private Gson gson;

    public ArchZoo(String nombre) {
        this.nombre = nombre;
        this.zoologicos = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Método para crear el archivo
    public void crearArchivo() {
        try {
            File archivo = new File(nombre);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + nombre);
                try (FileWriter writer = new FileWriter(nombre)) {
                    gson.toJson(new ArrayList<Zoologico>(), writer);
                }
            } else {
                System.out.println("El archivo ya existe: " + nombre);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // Método para cargar zoológicos desde el archivo
    public void cargarZoologicos() {
        try (FileReader reader = new FileReader(nombre)) {
            Type listType = new TypeToken<ArrayList<Zoologico>>(){}.getType();
            zoologicos = gson.fromJson(reader, listType);
            if (zoologicos == null) {
                zoologicos = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando lista vacía.");
            zoologicos = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar zoológicos: " + e.getMessage());
            zoologicos = new ArrayList<>();
        }
    }

    // a) Método para crear/guardar un zoológico
    public void guardarZoologico(Zoologico z) {
        cargarZoologicos();

        boolean existe = zoologicos.stream()
                .anyMatch(zoo -> zoo.getId() == z.getId());

        if (existe) {
            System.out.println("Ya existe un zoológico con el ID " + z.getId());
            return;
        }

        zoologicos.add(z);
        guardarTodos();
        System.out.println("Zoológico guardado: " + z.getNombre());
    }

    // a) Método para modificar un zoológico por ID
    public void modificarZoologico(int id, String nuevoNombre) {
        cargarZoologicos();

        Zoologico encontrado = zoologicos.stream()
                .filter(z -> z.getId() == id)
                .findFirst()
                .orElse(null);

        if (encontrado != null) {
            encontrado.setNombre(nuevoNombre);
            guardarTodos();
            System.out.println("\n--- Zoológico modificado ---");
            System.out.println("ID: " + id);
            System.out.println("Nuevo nombre: " + nuevoNombre);
        } else {
            System.out.println("Zoológico con ID " + id + " no encontrado.");
        }
    }

    // a) Método para eliminar un zoológico por ID
    public void eliminarZoologico(int id) {
        cargarZoologicos();

        boolean eliminado = zoologicos.removeIf(z -> z.getId() == id);

        if (eliminado) {
            guardarTodos();
            System.out.println("\n--- Zoológico eliminado ---");
            System.out.println("Zoológico con ID " + id + " eliminado.");
        } else {
            System.out.println("Zoológico con ID " + id + " no encontrado.");
        }
    }

    // Método auxiliar para guardar toda la lista
    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nombre)) {
            gson.toJson(zoologicos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar zoológicos: " + e.getMessage());
        }
    }

    // Método para listar todos los zoológicos
    public void listarZoologicos() {
        cargarZoologicos();

        System.out.println("\n--- Lista de Zoológicos ---");
        if (zoologicos.isEmpty()) {
            System.out.println("No hay zoológicos registrados.");
        } else {
            System.out.println("Total de zoológicos: " + zoologicos.size());
            zoologicos.forEach(System.out::println);
        }
    }

    // b) Listar los zoológicos que contengan mayor cantidad variedad de animales
    public void listarMayorVariedad() {
        cargarZoologicos();

        if (zoologicos.isEmpty()) {
            System.out.println("No hay zoológicos registrados.");
            return;
        }

        int maxVariedad = zoologicos.stream()
                .mapToInt(Zoologico::getNroAnimales)
                .max()
                .orElse(0);

        List<Zoologico> conMayorVariedad = zoologicos.stream()
                .filter(z -> z.getNroAnimales() == maxVariedad)
                .toList();

        System.out.println("\n--- Zoológicos con mayor variedad de animales ---");
        System.out.println("Cantidad máxima de animales: " + maxVariedad);
        conMayorVariedad.forEach(System.out::println);
    }

    // c) Listar los zoológicos vacíos y eliminarlos
    public void listarYEliminarVacios() {
        cargarZoologicos();

        List<Zoologico> vacios = zoologicos.stream()
                .filter(z -> z.getNroAnimales() == 0)
                .toList();

        System.out.println("\n--- Zoológicos vacíos ---");
        if (vacios.isEmpty()) {
            System.out.println("No hay zoológicos vacíos.");
        } else {
            vacios.forEach(System.out::println);

            zoologicos.removeIf(z -> z.getNroAnimales() == 0);
            guardarTodos();
            System.out.println("Zoológicos vacíos eliminados: " + vacios.size());
        }
    }

    // d) Mostrar a los animales de la especie x
    public void mostrarAnimalesPorEspecie(String especie) {
        cargarZoologicos();

        System.out.println("\n--- Animales de la especie: " + especie + " ---");

        boolean encontrado = false;
        for (Zoologico zoo : zoologicos) {
            for (Animal animal : zoo.getAnimales()) {
                if (animal.getEspecie().equalsIgnoreCase(especie)) {
                    System.out.println("Zoológico: " + zoo.getNombre() + " - " + animal);
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron animales de la especie " + especie);
        }
    }

    // e) Mover los animales de un zoológico x a un zoológico y
    public void moverAnimales(int idOrigen, int idDestino) {
        cargarZoologicos();

        Zoologico zooOrigen = zoologicos.stream()
                .filter(z -> z.getId() == idOrigen)
                .findFirst()
                .orElse(null);

        Zoologico zooDestino = zoologicos.stream()
                .filter(z -> z.getId() == idDestino)
                .findFirst()
                .orElse(null);

        if (zooOrigen == null) {
            System.out.println("Zoológico origen con ID " + idOrigen + " no encontrado.");
            return;
        }

        if (zooDestino == null) {
            System.out.println("Zoológico destino con ID " + idDestino + " no encontrado.");
            return;
        }

        System.out.println("\n--- Mover animales ---");
        System.out.println("Desde: " + zooOrigen.getNombre() + " (ID: " + idOrigen + ")");
        System.out.println("Hacia: " + zooDestino.getNombre() + " (ID: " + idDestino + ")");
        System.out.println("Animales a mover: " + zooOrigen.getNroAnimales());

        // Mover todos los animales
        for (Animal animal : zooOrigen.getAnimales()) {
            zooDestino.agregarAnimal(animal);
        }

        // Limpiar el zoológico origen
        zooOrigen.getAnimales().clear();
        zooOrigen.getNroAnimales();

        guardarTodos();
        System.out.println("Animales movidos exitosamente.");
    }
}