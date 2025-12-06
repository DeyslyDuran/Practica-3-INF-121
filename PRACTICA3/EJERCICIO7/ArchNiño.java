package EJERCICIO7;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArchNiño {
    private String nomA;
    private List<Niño> na;
    private Gson gson;

    public ArchNiño(String nomA) {
        this.nomA = nomA;
        this.na = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Método para crear el archivo
    public void crearArchivo() {
        try {
            File archivo = new File(nomA);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + nomA);
                // Inicializar con lista vacía
                try (FileWriter writer = new FileWriter(nomA)) {
                    gson.toJson(new ArrayList<Niño>(), writer);
                }
            } else {
                System.out.println("El archivo ya existe: " + nomA);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // Método para cargar niños desde el archivo
    public void cargarNiños() {
        try (FileReader reader = new FileReader(nomA)) {
            Type listType = new TypeToken<ArrayList<Niño>>(){}.getType();
            na = gson.fromJson(reader, listType);
            if (na == null) {
                na = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando lista vacía.");
            na = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar niños: " + e.getMessage());
            na = new ArrayList<>();
        }
    }

    // Método para guardar un niño
    public void guardarNiño(Niño n) {
        cargarNiños();

        // Verificar si ya existe un niño con el mismo CI
        boolean existe = na.stream()
                .anyMatch(niño -> niño.getCi() == n.getCi());

        if (existe) {
            System.out.println("Ya existe un niño con el CI " + n.getCi());
            return;
        }

        // Agregar nuevo niño
        na.add(n);

        // Guardar todos en el archivo
        guardarTodos();
        System.out.println("Niño guardado: " + n.getNombre());
    }

    // Método auxiliar para guardar toda la lista
    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nomA)) {
            gson.toJson(na, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar niños: " + e.getMessage());
        }
    }

    // a) Listar todos los niños
    public void listarNiños() {
        cargarNiños();

        System.out.println("\n--- Lista de Niños ---");
        if (na.isEmpty()) {
            System.out.println("No hay niños registrados.");
        } else {
            System.out.println("Total de niños: " + na.size());
            na.forEach(System.out::println);
        }
    }

    // b) Cuántos niños tienen el peso adecuado de acuerdo a su talla y edad
    public int contarPesoAdecuado() {
        cargarNiños();

        int contador = (int) na.stream()
                .filter(n -> n.getPeso().equalsIgnoreCase("adecuado") ||
                        n.getPeso().equalsIgnoreCase("normal"))
                .count();

        System.out.println("\n--- Niños con peso adecuado ---");
        System.out.println("Cantidad: " + contador);

        return contador;
    }

    // c) Mostrar a los niños que de acuerdo a la edad no tienen el peso o la talla adecuada
    public void mostrarPesoTallaInadecuada() {
        cargarNiños();

        System.out.println("\n--- Niños con peso o talla inadecuada ---");

        List<Niño> inadecuados = na.stream()
                .filter(n -> (!n.getPeso().equalsIgnoreCase("adecuado") &&
                        !n.getPeso().equalsIgnoreCase("normal")) ||
                        (!n.getTalla().equalsIgnoreCase("adecuada") &&
                                !n.getTalla().equalsIgnoreCase("normal")))
                .toList();

        if (inadecuados.isEmpty()) {
            System.out.println("Todos los niños tienen peso y talla adecuados.");
        } else {
            inadecuados.forEach(System.out::println);
        }
    }

    // d) Determinar el promedio de edad en los niños
    public double calcularPromedioEdad() {
        cargarNiños();

        if (na.isEmpty()) {
            System.out.println("No hay niños para calcular el promedio.");
            return 0.0;
        }

        double suma = na.stream()
                .mapToInt(Niño::getEdad)
                .sum();

        double promedio = suma / na.size();

        System.out.println("\n--- Promedio de edad ---");
        System.out.println("Total de niños: " + na.size());
        System.out.println("Suma de edades: " + (int)suma);
        System.out.println("Promedio: " + String.format("%.2f", promedio) + " años");

        return promedio;
    }

    // e) Buscar al niño con el carnet x
    public Niño buscarPorCarnet(int c) {
        cargarNiños();

        Niño encontrado = na.stream()
                .filter(n -> n.getCi() == c)
                .findFirst()
                .orElse(null);

        if (encontrado != null) {
            System.out.println("\n--- Niño encontrado ---");
            System.out.println("Nombre: " + encontrado.getNombre());
            System.out.println("Apellido Paterno: " + encontrado.getApellidoPaterno());
            System.out.println("Apellido Materno: " + encontrado.getApellidoMaterno());
            System.out.println("CI: " + encontrado.getCi());
            System.out.println("Edad: " + encontrado.getEdad());
            System.out.println("Peso: " + encontrado.getPeso());
            System.out.println("Talla: " + encontrado.getTalla());
        } else {
            System.out.println("Niño con carnet " + c + " no encontrado.");
        }

        return encontrado;
    }

    // f) Mostrar a los niños con la talla más alta
    public void mostrarTallaMasAlta() {
        cargarNiños();

        if (na.isEmpty()) {
            System.out.println("No hay niños registrados.");
            return;
        }

        List<Niño> tallaAlta = na.stream()
                .filter(n -> n.getTalla().equalsIgnoreCase("alta"))
                .toList();

        System.out.println("\n--- Niños con la talla más alta ---");
        if (tallaAlta.isEmpty()) {
            System.out.println("No hay niños con talla alta.");
        } else {
            tallaAlta.forEach(System.out::println);
        }
    }
}