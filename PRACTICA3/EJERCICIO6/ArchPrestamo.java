package EJERCICIO6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class ArchPrestamo {
    private String nombreArch;
    private List<Prestamo> prestamos;
    private Gson gson;

    public ArchPrestamo(String nombreArch) {
        this.nombreArch = nombreArch;
        this.prestamos = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void crearArchivo() {
        try {
            File archivo = new File(nombreArch);
            if (archivo.createNewFile()) {
                try (FileWriter writer = new FileWriter(nombreArch)) {
                    gson.toJson(new ArrayList<Prestamo>(), writer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    public void cargarPrestamos() {
        try (FileReader reader = new FileReader(nombreArch)) {
            Type listType = new TypeToken<ArrayList<Prestamo>>(){}.getType();
            prestamos = gson.fromJson(reader, listType);
            if (prestamos == null) {
                prestamos = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            prestamos = new ArrayList<>();
        } catch (IOException e) {
            prestamos = new ArrayList<>();
        }
    }

    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nombreArch)) {
            gson.toJson(prestamos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public void agregarPrestamo(Prestamo prestamo) {
        cargarPrestamos();
        prestamos.add(prestamo);
        guardarTodos();
    }

    // a) Listar los libros cuyo precio estén entre 2 valores (x e y)
    public void listarLibrosPorRangoPrecio(double precioMin, double precioMax, ArchiLibro archiLibro) {
        System.out.println("\n--- a) Libros con precio entre " + precioMin + " y " + precioMax + " ---");

        List<Libro> libros = archiLibro.getLibros();
        List<Libro> librosFiltrados = libros.stream()
                .filter(l -> l.getPrecio() >= precioMin && l.getPrecio() <= precioMax)
                .collect(Collectors.toList());

        if (librosFiltrados.isEmpty()) {
            System.out.println("No hay libros en ese rango de precios.");
        } else {
            librosFiltrados.forEach(System.out::println);
        }
    }

    // b) Calcular el ingreso total generado por un libro específico
    public void calcularIngresoTotalLibro(int codLibro, ArchiLibro archiLibro) {
        cargarPrestamos();
        System.out.println("\n--- b) Ingreso total generado por libro código " + codLibro + " ---");

        List<Libro> libros = archiLibro.getLibros();
        Libro libro = libros.stream()
                .filter(l -> l.getCodLibro() == codLibro)
                .findFirst()
                .orElse(null);

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        int totalPrestamos = prestamos.stream()
                .filter(p -> p.getCodLibro() == codLibro)
                .mapToInt(Prestamo::getCantidad)
                .sum();

        double ingresoTotal = totalPrestamos * libro.getPrecio();

        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Precio unitario: " + libro.getPrecio());
        System.out.println("Total préstamos: " + totalPrestamos);
        System.out.println("Ingreso total: " + ingresoTotal);
    }

    // c) Mostrar la lista de libros que nunca fueron vendidos
    public void mostrarLibrosNuncaVendidos(ArchiLibro archiLibro) {
        cargarPrestamos();
        System.out.println("\n--- c) Libros que nunca fueron prestados ---");

        List<Libro> libros = archiLibro.getLibros();
        Set<Integer> librosConPrestamos = prestamos.stream()
                .map(Prestamo::getCodLibro)
                .collect(Collectors.toSet());

        List<Libro> librosNuncaPrestados = libros.stream()
                .filter(l -> !librosConPrestamos.contains(l.getCodLibro()))
                .collect(Collectors.toList());

        if (librosNuncaPrestados.isEmpty()) {
            System.out.println("Todos los libros han sido prestados al menos una vez.");
        } else {
            librosNuncaPrestados.forEach(System.out::println);
        }
    }

    // d) Mostrar a todos los clientes que compraron un libro específico (dado su código)
    public void mostrarClientesPorLibro(int codLibro, ArchCliente archCliente) {
        cargarPrestamos();
        System.out.println("\n--- d) Clientes que prestaron el libro código " + codLibro + " ---");

        List<Integer> codigosClientes = prestamos.stream()
                .filter(p -> p.getCodLibro() == codLibro)
                .map(Prestamo::getCodCliente)
                .distinct()
                .collect(Collectors.toList());

        if (codigosClientes.isEmpty()) {
            System.out.println("Ningún cliente ha prestado este libro.");
            return;
        }

        List<Cliente> clientes = archCliente.getClientes();
        for (int codCliente : codigosClientes) {
            Cliente cliente = clientes.stream()
                    .filter(c -> c.getCodCliente() == codCliente)
                    .findFirst()
                    .orElse(null);

            if (cliente != null) {
                System.out.println(cliente);
            }
        }
    }

    // e) Definir el libro más prestado
    public void definirLibroMasPrestado(ArchiLibro archiLibro) {
        cargarPrestamos();
        System.out.println("\n--- e) Libro más prestado ---");

        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        Map<Integer, Integer> prestamosPorLibro = new HashMap<>();

        for (Prestamo p : prestamos) {
            prestamosPorLibro.put(p.getCodLibro(),
                    prestamosPorLibro.getOrDefault(p.getCodLibro(), 0) + p.getCantidad());
        }

        int codLibroMasPrestado = prestamosPorLibro.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        int maxPrestamos = prestamosPorLibro.get(codLibroMasPrestado);

        List<Libro> libros = archiLibro.getLibros();
        Libro libroMasPrestado = libros.stream()
                .filter(l -> l.getCodLibro() == codLibroMasPrestado)
                .findFirst()
                .orElse(null);

        if (libroMasPrestado != null) {
            System.out.println("Libro más prestado: " + libroMasPrestado.getTitulo());
            System.out.println("Código: " + libroMasPrestado.getCodLibro());
            System.out.println("Total préstamos: " + maxPrestamos);
        }
    }

    // f) Mostrar el cliente que tuvo más préstamos
    public void mostrarClienteMasPrestamos(ArchCliente archCliente) {
        cargarPrestamos();
        System.out.println("\n--- f) Cliente con más préstamos ---");

        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        Map<Integer, Integer> prestamosPorCliente = new HashMap<>();

        for (Prestamo p : prestamos) {
            prestamosPorCliente.put(p.getCodCliente(),
                    prestamosPorCliente.getOrDefault(p.getCodCliente(), 0) + p.getCantidad());
        }

        int codClienteMasPrestamos = prestamosPorCliente.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        int maxPrestamos = prestamosPorCliente.get(codClienteMasPrestamos);

        List<Cliente> clientes = archCliente.getClientes();
        Cliente clienteMasPrestamos = clientes.stream()
                .filter(c -> c.getCodCliente() == codClienteMasPrestamos)
                .findFirst()
                .orElse(null);

        if (clienteMasPrestamos != null) {
            System.out.println("Cliente: " + clienteMasPrestamos.getNombre() + " " +
                    clienteMasPrestamos.getApellido());
            System.out.println("CI: " + clienteMasPrestamos.getCi());
            System.out.println("Código: " + clienteMasPrestamos.getCodCliente());
            System.out.println("Total préstamos: " + maxPrestamos);
        }
    }
}