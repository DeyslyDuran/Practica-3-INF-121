package EJERCICIO1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CharangoManager {
    private List<Charango> charangos;
    private static final String ARCHIVO = "charangos.json";
    private Gson gson;

    public CharangoManager() {
        this.charangos = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Agregar un charango a la lista
    public void agregarCharango(Charango charango) {
        charangos.add(charango);
    }

    // Guardar charangos en archivo JSON
    public void guardarEnArchivo() {
        try (FileWriter writer = new FileWriter(ARCHIVO)) {
            gson.toJson(charangos, writer);
            System.out.println("Charangos guardados en " + ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    // Cargar charangos desde archivo JSON
    public void cargarDesdeArchivo() {
        try (FileReader reader = new FileReader(ARCHIVO)) {
            Type listType = new TypeToken<ArrayList<Charango>>(){}.getType();
            charangos = gson.fromJson(reader, listType);
            System.out.println("Charangos cargados desde " + ARCHIVO);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
    }

    // b) Eliminar charangos con más de 6 cuerdas falsas
    public void eliminarConMasDe6CuerdasFalsas() {
        int tamanioInicial = charangos.size();
        charangos.removeIf(c -> c.contarCuerdasFalsas() > 6);
        System.out.println("\nEliminados " + (tamanioInicial - charangos.size()) + " charangos con más de 6 cuerdas falsas");
    }

    // c) Listar charangos de material 'x'
    public void listarPorMaterial(String material) {
        System.out.println("\n--- Charangos de material: " + material + " ---");
        List<Charango> filtrados = charangos.stream()
                .filter(c -> c.getMaterial().equalsIgnoreCase(material))
                .collect(Collectors.toList());

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron charangos de ese material.");
        } else {
            filtrados.forEach(System.out::println);
        }
    }

    // d) Buscar charangos con 10 cuerdas
    public void buscarCon10Cuerdas() {
        System.out.println("\n--- Charangos con 10 cuerdas ---");
        List<Charango> con10Cuerdas = charangos.stream()
                .filter(c -> c.getNroCuerdas() == 10)
                .collect(Collectors.toList());

        if (con10Cuerdas.isEmpty()) {
            System.out.println("No se encontraron charangos con 10 cuerdas.");
        } else {
            con10Cuerdas.forEach(System.out::println);
        }
    }

    // e) Ordenar por material alfabéticamente
    public void ordenarPorMaterial() {
        charangos.sort(Comparator.comparing(Charango::getMaterial));
        System.out.println("\nCharangos ordenados por material alfabéticamente:");
        charangos.forEach(System.out::println);
    }

    // Listar todos los charangos
    public void listarTodos() {
        System.out.println("\n--- Todos los Charangos ---");
        if (charangos.isEmpty()) {
            System.out.println("No hay charangos registrados.");
        } else {
            charangos.forEach(System.out::println);
        }
    }
}
