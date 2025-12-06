package EJERCICIO8;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ArchRefri {
    private String nombre;
    private List<Alimento> alimentos;
    private Gson gson;

    public ArchRefri(String nombre) {
        this.nombre = nombre;
        this.alimentos = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Método para crear el archivo
    public void crearArchivo() {
        try {
            File archivo = new File(nombre);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + nombre);
                try (FileWriter writer = new FileWriter(nombre)) {
                    gson.toJson(new ArrayList<Alimento>(), writer);
                }
            } else {
                System.out.println("El archivo ya existe: " + nombre);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // Método para cargar alimentos desde el archivo
    public void cargarAlimentos() {
        try (FileReader reader = new FileReader(nombre)) {
            Type listType = new TypeToken<ArrayList<Alimento>>(){}.getType();
            alimentos = gson.fromJson(reader, listType);
            if (alimentos == null) {
                alimentos = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando lista vacía.");
            alimentos = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar alimentos: " + e.getMessage());
            alimentos = new ArrayList<>();
        }
    }

    // a) Método para guardar un alimento (Crear)
    public void guardarAlimento(Alimento a) {
        cargarAlimentos();

        // Verificar si ya existe un alimento con el mismo nombre
        boolean existe = alimentos.stream()
                .anyMatch(alimento -> alimento.getNombre().equalsIgnoreCase(a.getNombre()));

        if (existe) {
            System.out.println("Ya existe un alimento con el nombre " + a.getNombre());
            return;
        }

        alimentos.add(a);
        guardarTodos();
        System.out.println("Alimento guardado: " + a.getNombre());
    }

    // a) Método para modificar un alimento por nombre
    public void modificarAlimento(String nombreViejo, String nombreNuevo) {
        cargarAlimentos();

        Alimento encontrado = alimentos.stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombreViejo))
                .findFirst()
                .orElse(null);

        if (encontrado != null) {
            encontrado.setNombre(nombreNuevo);
            guardarTodos();
            System.out.println("\n--- Alimento modificado ---");
            System.out.println("Nombre anterior: " + nombreViejo);
            System.out.println("Nombre nuevo: " + nombreNuevo);
        } else {
            System.out.println("Alimento con nombre " + nombreViejo + " no encontrado.");
        }
    }

    // a) Método para eliminar un alimento por nombre
    public void eliminarAlimento(String nombreAlimento) {
        cargarAlimentos();

        boolean eliminado = alimentos.removeIf(a -> a.getNombre().equalsIgnoreCase(nombreAlimento));

        if (eliminado) {
            guardarTodos();
            System.out.println("\n--- Alimento eliminado ---");
            System.out.println("Alimento eliminado: " + nombreAlimento);
        } else {
            System.out.println("Alimento con nombre " + nombreAlimento + " no encontrado.");
        }
    }

    // Método auxiliar para guardar toda la lista
    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nombre)) {
            gson.toJson(alimentos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar alimentos: " + e.getMessage());
        }
    }

    // Método para listar todos los alimentos
    public void listarAlimentos() {
        cargarAlimentos();

        System.out.println("\n--- Lista de Alimentos ---");
        if (alimentos.isEmpty()) {
            System.out.println("No hay alimentos registrados.");
        } else {
            System.out.println("Total de alimentos: " + alimentos.size());
            alimentos.forEach(System.out::println);
        }
    }

    // b) Mostrar los alimentos que caducaron antes de una fecha dada X
    public void mostrarCaducadosAntesDe(String fechaLimite) {
        cargarAlimentos();

        System.out.println("\n--- Alimentos caducados antes de " + fechaLimite + " ---");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date fechaLim = sdf.parse(fechaLimite);

            List<Alimento> caducados = alimentos.stream()
                    .filter(a -> {
                        try {
                            Date fechaVenc = sdf.parse(a.getFechaVencimiento());
                            return fechaVenc.before(fechaLim);
                        } catch (ParseException e) {
                            return false;
                        }
                    })
                    .toList();

            if (caducados.isEmpty()) {
                System.out.println("No hay alimentos caducados antes de esta fecha.");
            } else {
                caducados.forEach(System.out::println);
            }
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
        }
    }

    // c) Eliminar los alimentos que tengan cantidad 0
    public void eliminarCantidadCero() {
        cargarAlimentos();

        int cantidadAntes = alimentos.size();
        alimentos.removeIf(a -> a.getCantidad() == 0);
        int cantidadDespues = alimentos.size();

        guardarTodos();

        System.out.println("\n--- Eliminar alimentos con cantidad 0 ---");
        System.out.println("Alimentos eliminados: " + (cantidadAntes - cantidadDespues));
    }

    // d) Buscar los alimentos ya vencidos
    public void buscarVencidos() {
        cargarAlimentos();

        System.out.println("\n--- Alimentos vencidos ---");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = new Date();

        List<Alimento> vencidos = alimentos.stream()
                .filter(a -> {
                    try {
                        Date fechaVenc = sdf.parse(a.getFechaVencimiento());
                        return fechaVenc.before(fechaActual);
                    } catch (ParseException e) {
                        return false;
                    }
                })
                .toList();

        if (vencidos.isEmpty()) {
            System.out.println("No hay alimentos vencidos.");
        } else {
            vencidos.forEach(System.out::println);
        }
    }

    // e) Mostrar el alimento que tenga más cantidad en el refri
    public void mostrarMayorCantidad() {
        cargarAlimentos();

        if (alimentos.isEmpty()) {
            System.out.println("No hay alimentos registrados.");
            return;
        }

        Alimento mayorCantidad = alimentos.stream()
                .max(Comparator.comparing(Alimento::getCantidad))
                .orElse(null);

        System.out.println("\n--- Alimento con mayor cantidad ---");
        if (mayorCantidad != null) {
            System.out.println(mayorCantidad);
        }
    }
}