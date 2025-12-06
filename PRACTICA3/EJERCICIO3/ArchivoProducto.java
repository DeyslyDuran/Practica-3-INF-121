package EJERCICIO3;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArchivoProducto {
    private String nomA;
    private List<Producto> productos;
    private Gson gson;

    public ArchivoProducto(String nomA) {
        this.nomA = nomA;
        this.productos = new ArrayList<>();
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
                    gson.toJson(new ArrayList<Producto>(), writer);
                }
            } else {
                System.out.println("El archivo ya existe: " + nomA);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // Método para cargar productos desde el archivo
    public void cargarProductos() {
        try (FileReader reader = new FileReader(nomA)) {
            Type listType = new TypeToken<ArrayList<Producto>>(){}.getType();
            productos = gson.fromJson(reader, listType);
            if (productos == null) {
                productos = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando lista vacía.");
            productos = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
            productos = new ArrayList<>();
        }
    }

    // b) Guardar producto en el archivo
    public void guardaProducto(Producto p) {
        cargarProductos();

        // Verificar si ya existe un producto con el mismo código
        boolean existe = productos.stream()
                .anyMatch(prod -> prod.getCodigo() == p.getCodigo());

        if (existe) {
            System.out.println("Ya existe un producto con el código " + p.getCodigo());
            return;
        }

        // Agregar nuevo producto
        productos.add(p);

        // Guardar todos en el archivo
        guardarTodos();
        System.out.println("Producto guardado: " + p.getNombre());
    }

    // Método auxiliar para guardar toda la lista
    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nomA)) {
            gson.toJson(productos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
        }
    }

    // c) Buscar producto por código y mostrar sus datos
    public Producto buscaProducto(int c) {
        cargarProductos();

        Producto encontrado = productos.stream()
                .filter(p -> p.getCodigo() == c)
                .findFirst()
                .orElse(null);

        if (encontrado != null) {
            System.out.println("\n--- Producto encontrado ---");
            System.out.println("Código: " + encontrado.getCodigo());
            System.out.println("Nombre: " + encontrado.getNombre());
            System.out.println("Precio: " + encontrado.getPrecio());
        } else {
            System.out.println("Producto con código " + c + " no encontrado.");
        }

        return encontrado;
    }

    // d) Calcular el promedio de precios de los productos
    public double calcularPromedioPrecios() {
        cargarProductos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos para calcular el promedio.");
            return 0.0;
        }

        double suma = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        double promedio = suma / productos.size();

        System.out.println("\n--- Promedio de precios ---");
        System.out.println("Total de productos: " + productos.size());
        System.out.println("Suma total: " + suma);
        System.out.println("Promedio: " + String.format("%.2f", promedio));

        return promedio;
    }

    // e) Mostrar el producto más caro
    public Producto mostrarProductoMasCaro() {
        cargarProductos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return null;
        }

        Producto masCaro = productos.stream()
                .max(Comparator.comparing(Producto::getPrecio))
                .orElse(null);

        if (masCaro != null) {
            System.out.println("\n--- Producto más caro ---");
            System.out.println(masCaro);
        }

        return masCaro;
    }

    // Método para listar todos los productos
    public void listarProductos() {
        cargarProductos();

        System.out.println("\n--- Lista de Productos ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("Total de productos: " + productos.size());
            productos.forEach(System.out::println);
        }
    }

}
