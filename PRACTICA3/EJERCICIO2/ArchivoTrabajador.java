package EJERCICIO2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArchivoTrabajador {
    private String nombreArch;
    private List<Trabajador> trabajadores;
    private Gson gson;

    public ArchivoTrabajador() {
        this.nombreArch = "trabajadores";
        this.trabajadores = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public ArchivoTrabajador(String nombreArch) {
        this.nombreArch = nombreArch;
        this.trabajadores = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // a) Método para crear y guardar el archivo
    public void crearArchivo() {
        try {
            File archivo = new File(nombreArch);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + nombreArch);
                // Inicializar con lista vacía
                try (FileWriter writer = new FileWriter(nombreArch)) {
                    gson.toJson(new ArrayList<Trabajador>(), writer);
                }
            } else {
                System.out.println("El archivo ya existe: " + nombreArch);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // Método para cargar trabajadores desde el archivo
    public void cargarTrabajadores() {
        try (FileReader reader = new FileReader(nombreArch)) {
            Type listType = new TypeToken<ArrayList<Trabajador>>(){}.getType();
            trabajadores = gson.fromJson(reader, listType);
            if (trabajadores == null) {
                trabajadores = new ArrayList<>();
            }
            System.out.println("Trabajadores cargados desde " + nombreArch);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
            trabajadores = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
    }

    // b) Método para guardar trabajadores en el archivo
    public void guardarTrabajador(Trabajador t) {
        // Cargar trabajadores existentes
        cargarTrabajadores();

        // Agregar nuevo trabajador
        trabajadores.add(t);

        // Guardar todos en el archivo
        try (FileWriter writer = new FileWriter(nombreArch)) {
            gson.toJson(trabajadores, writer);
            System.out.println("Trabajador guardado: " + t.getNombre());
        } catch (IOException e) {
            System.err.println("Error al guardar trabajador: " + e.getMessage());
        }
    }

    // Método auxiliar para guardar toda la lista
    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nombreArch)) {
            gson.toJson(trabajadores, writer);
            System.out.println("Archivo actualizado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    // c) Método para aumentar el salario de un trabajador
    public void aumentaSalario(int carnet, Trabajador t) {
        cargarTrabajadores();

        boolean encontrado = false;
        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getCarnet() == carnet) {
                trabajador.setSalario(t.getSalario());
                encontrado = true;
                System.out.println("Salario actualizado para " + trabajador.getNombre() +
                        " a: " + t.getSalario());
                break;
            }
        }

        if (encontrado) {
            guardarTodos();
        } else {
            System.out.println("Trabajador con carnet " + carnet + " no encontrado.");
        }
    }

    // Sobrecarga del método aumentaSalario con porcentaje
    public void aumentaSalario(int carnet, double porcentaje) {
        cargarTrabajadores();

        boolean encontrado = false;
        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getCarnet() == carnet) {
                double nuevoSalario = trabajador.getSalario() * (1 + porcentaje / 100);
                trabajador.setSalario(nuevoSalario);
                encontrado = true;
                System.out.println("Salario aumentado en " + porcentaje + "% para " +
                        trabajador.getNombre() + ". Nuevo salario: " + nuevoSalario);
                break;
            }
        }

        if (encontrado) {
            guardarTodos();
        } else {
            System.out.println("Trabajador con carnet " + carnet + " no encontrado.");
        }
    }

    // d) Buscar el trabajador con el mayor salario
    public Trabajador buscarMayorSalario() {
        cargarTrabajadores();

        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return null;
        }

        Trabajador mayorSalario = trabajadores.stream()
                .max(Comparator.comparingDouble(Trabajador::getSalario))
                .orElse(null);

        if (mayorSalario != null) {
            System.out.println("\nTrabajador con mayor salario:");
            System.out.println(mayorSalario);
        }

        return mayorSalario;
    }

    // e) Ordenar a los trabajadores por su salario
    public void ordenarPorSalario() {
        cargarTrabajadores();

        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores para ordenar.");
            return;
        }

        trabajadores.sort(Comparator.comparingDouble(Trabajador::getSalario).reversed());

        System.out.println("\nTrabajadores ordenados por salario (mayor a menor):");
        trabajadores.forEach(System.out::println);

        guardarTodos();
    }

    // Método para listar todos los trabajadores
    public void listarTrabajadores() {
        cargarTrabajadores();

        System.out.println("\n--- Lista de Trabajadores ---");
        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
        } else {
            trabajadores.forEach(System.out::println);
        }
    }

    // Método para buscar trabajador por carnet
    public Trabajador buscarPorCarnet(int carnet) {
        cargarTrabajadores();

        return trabajadores.stream()
                .filter(t -> t.getCarnet() == carnet)
                .findFirst()
                .orElse(null);
    }
}
