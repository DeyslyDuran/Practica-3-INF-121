package EJERCICIO4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArchiNota {
    private String nombreArchi;
    private List<Nota> notas;
    private Gson gson;

    public ArchiNota(String nombreArchi) {
        this.nombreArchi = nombreArchi;
        this.notas = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Método para crear el archivo
    public void crearArchivo() {
        try {
            File archivo = new File(nombreArchi);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + nombreArchi);
                try (FileWriter writer = new FileWriter(nombreArchi)) {
                    gson.toJson(new ArrayList<Nota>(), writer);
                }
            } else {
                System.out.println("El archivo ya existe: " + nombreArchi);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // Método para cargar notas desde el archivo
    public void cargarNotas() {
        try (FileReader reader = new FileReader(nombreArchi)) {
            Type listType = new TypeToken<ArrayList<Nota>>(){}.getType();
            notas = gson.fromJson(reader, listType);
            if (notas == null) {
                notas = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando lista vacía.");
            notas = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar notas: " + e.getMessage());
            notas = new ArrayList<>();
        }
    }

    // Método auxiliar para guardar toda la lista
    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nombreArchi)) {
            gson.toJson(notas, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar notas: " + e.getMessage());
        }
    }

    // b) Método para agregar a varios estudiantes
    public void agregarEstudiantes(List<Nota> nuevasNotas) {
        cargarNotas();

        System.out.println("\n--- Agregando estudiantes ---");
        for (Nota nota : nuevasNotas) {
            // Verificar si ya existe el estudiante con la misma materia
            boolean existe = notas.stream()
                    .anyMatch(n -> n.getEstudiante().getRu() == nota.getEstudiante().getRu()
                            && n.getMaterno().equalsIgnoreCase(nota.getMaterno()));

            if (existe) {
                System.out.println("Ya existe registro para " +
                        nota.getEstudiante().getNombreCompleto() +
                        " en " + nota.getMaterno());
            } else {
                notas.add(nota);
                System.out.println("Agregado: " + nota.getEstudiante().getNombreCompleto() +
                        " - " + nota.getMaterno() + " (Nota: " + nota.getNotaFinal() + ")");
            }
        }

        guardarTodos();
        System.out.println("Total de registros: " + notas.size());
    }

    // Sobrecarga para agregar un solo estudiante
    public void agregarEstudiante(Nota nota) {
        List<Nota> lista = new ArrayList<>();
        lista.add(nota);
        agregarEstudiantes(lista);
    }

    // c) Obtener el promedio de notas de todos los estudiantes
    public double obtenerPromedioGeneral() {
        cargarNotas();

        if (notas.isEmpty()) {
            System.out.println("No hay notas registradas.");
            return 0.0;
        }

        double suma = notas.stream()
                .mapToDouble(Nota::getNotaFinal)
                .sum();

        double promedio = suma / notas.size();

        System.out.println("\n--- Promedio General de Notas ---");
        System.out.println("Total de notas: " + notas.size());
        System.out.println("Suma total: " + String.format("%.2f", suma));
        System.out.println("Promedio: " + String.format("%.2f", promedio));

        return promedio;
    }

    // d) Buscar al o los estudiantes con la mejor nota
    public List<Nota> buscarEstudiantesConMejorNota() {
        cargarNotas();

        if (notas.isEmpty()) {
            System.out.println("No hay notas registradas.");
            return new ArrayList<>();
        }

        double mejorNota = notas.stream()
                .mapToDouble(Nota::getNotaFinal)
                .max()
                .orElse(0.0);

        List<Nota> mejoresNotas = notas.stream()
                .filter(n -> n.getNotaFinal() == mejorNota)
                .collect(Collectors.toList());

        System.out.println("\n--- Estudiante(s) con la mejor nota ---");
        System.out.println("Mejor nota: " + mejorNota);
        mejoresNotas.forEach(n -> {
            System.out.println("  - " + n.getEstudiante().getNombreCompleto() +
                    " | Materia: " + n.getMaterno() +
                    " | Nota: " + n.getNotaFinal());
        });

        return mejoresNotas;
    }

    // e) Eliminar a todos los estudiantes de una determinada materia
    public void eliminarEstudiantesPorMateria(String materia) {
        cargarNotas();

        int cantidadInicial = notas.size();

        notas.removeIf(n -> n.getMaterno().equalsIgnoreCase(materia));

        int eliminados = cantidadInicial - notas.size();

        System.out.println("\n--- Eliminación por materia ---");
        System.out.println("Materia: " + materia);
        System.out.println("Registros eliminados: " + eliminados);
        System.out.println("Registros restantes: " + notas.size());

        if (eliminados > 0) {
            guardarTodos();
        }
    }

    // Método para listar todas las notas
    public void listarNotas() {
        cargarNotas();

        System.out.println("\n--- Lista de Notas ---");
        if (notas.isEmpty()) {
            System.out.println("No hay notas registradas.");
        } else {
            System.out.println("Total de registros: " + notas.size());
            notas.forEach(System.out::println);
        }
    }

}