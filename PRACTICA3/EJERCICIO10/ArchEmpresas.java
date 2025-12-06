package EJERCICIO10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArchEmpresas {
    private String nombre;
    private List<Empresa> empresas;
    private Gson gson;

    public ArchEmpresas(String nombre) {
        this.nombre = nombre;
        this.empresas = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Método para crear el archivo
    public void crearArchivo() {
        try {
            File archivo = new File(nombre);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + nombre);
                try (FileWriter writer = new FileWriter(nombre)) {
                    gson.toJson(new ArrayList<Empresa>(), writer);
                }
            } else {
                System.out.println("El archivo ya existe: " + nombre);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // Método para cargar empresas desde el archivo
    public void cargarEmpresas() {
        try (FileReader reader = new FileReader(nombre)) {
            Type listType = new TypeToken<ArrayList<Empresa>>(){}.getType();
            empresas = gson.fromJson(reader, listType);
            if (empresas == null) {
                empresas = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando lista vacía.");
            empresas = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar empresas: " + e.getMessage());
            empresas = new ArrayList<>();
        }
    }

    // Método para guardar una empresa
    public void guardarEmpresa(Empresa e) {
        cargarEmpresas();

        boolean existe = empresas.stream()
                .anyMatch(empresa -> empresa.getNombre().equalsIgnoreCase(e.getNombre()));

        if (existe) {
            System.out.println("Ya existe una empresa con el nombre " + e.getNombre());
            return;
        }

        empresas.add(e);
        guardarTodas();
        System.out.println("Empresa guardada: " + e.getNombre());
    }

    // Método auxiliar para guardar toda la lista
    private void guardarTodas() {
        try (FileWriter writer = new FileWriter(nombre)) {
            gson.toJson(empresas, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar empresas: " + e.getMessage());
        }
    }

    // Mostrar todas las empresas
    public void mostrarTodasLasEmpresas() {
        cargarEmpresas();

        System.out.println("\n--- Lista de Empresas ---");
        if (empresas.isEmpty()) {
            System.out.println("No hay empresas registradas.");
        } else {
            System.out.println("Total de empresas: " + empresas.size());
            empresas.forEach(System.out::println);
        }
    }

    // Buscar una empresa por nombre
    public Empresa buscarEmpresaPorNombre(String nombreEmpresa) {
        cargarEmpresas();

        Empresa encontrada = empresas.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombreEmpresa))
                .findFirst()
                .orElse(null);

        if (encontrada != null) {
            System.out.println("\n--- Empresa encontrada ---");
            System.out.println("Nombre: " + encontrada.getNombre());
            System.out.println("Rubro: " + encontrada.getRubro());
            System.out.println("Número de empleados: " + encontrada.getNumeroEmpleados());
        } else {
            System.out.println("Empresa con nombre " + nombreEmpresa + " no encontrada.");
        }

        return encontrada;
    }

    // Método adicional: Mostrar empresas por rubro
    public void mostrarEmpresasPorRubro(String rubro) {
        cargarEmpresas();

        System.out.println("\n--- Empresas del rubro: " + rubro + " ---");

        List<Empresa> empresasRubro = empresas.stream()
                .filter(e -> e.getRubro().equalsIgnoreCase(rubro))
                .toList();

        if (empresasRubro.isEmpty()) {
            System.out.println("No hay empresas del rubro " + rubro);
        } else {
            empresasRubro.forEach(System.out::println);
        }
    }

    // Método adicional: Mostrar empresa con más empleados
    public void mostrarEmpresaMasEmpleados() {
        cargarEmpresas();

        if (empresas.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }

        Empresa masEmpleados = empresas.stream()
                .max(Comparator.comparing(Empresa::getNumeroEmpleados))
                .orElse(null);

        System.out.println("\n--- Empresa con más empleados ---");
        if (masEmpleados != null) {
            System.out.println(masEmpleados);
        }
    }

    // Método adicional: Calcular total de empleados
    public void calcularTotalEmpleados() {
        cargarEmpresas();

        int total = empresas.stream()
                .mapToInt(Empresa::getNumeroEmpleados)
                .sum();

        System.out.println("\n--- Total de empleados ---");
        System.out.println("Empresas registradas: " + empresas.size());
        System.out.println("Total de empleados: " + total);
    }

    // Método adicional: Modificar empresa
    public void modificarEmpresa(String nombreViejo, String nombreNuevo, String nuevoRubro, int nuevoNumEmpleados) {
        cargarEmpresas();

        Empresa encontrada = empresas.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombreViejo))
                .findFirst()
                .orElse(null);

        if (encontrada != null) {
            encontrada.setNombre(nombreNuevo);
            encontrada.setRubro(nuevoRubro);
            encontrada.setNumeroEmpleados(nuevoNumEmpleados);
            guardarTodas();
            System.out.println("\n--- Empresa modificada ---");
            System.out.println("Nombre anterior: " + nombreViejo);
            System.out.println("Datos nuevos: " + encontrada);
        } else {
            System.out.println("Empresa con nombre " + nombreViejo + " no encontrada.");
        }
    }

    // Método adicional: Eliminar empresa
    public void eliminarEmpresa(String nombreEmpresa) {
        cargarEmpresas();

        boolean eliminado = empresas.removeIf(e -> e.getNombre().equalsIgnoreCase(nombreEmpresa));

        if (eliminado) {
            guardarTodas();
            System.out.println("\n--- Empresa eliminada ---");
            System.out.println("Empresa eliminada: " + nombreEmpresa);
        } else {
            System.out.println("Empresa con nombre " + nombreEmpresa + " no encontrada.");
        }
    }
}