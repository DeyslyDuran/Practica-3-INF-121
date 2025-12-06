package EJERCICIO6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchiLibro {
    private String nombreArch;
    private List<Libro> libros;
    private Gson gson;

    public ArchiLibro(String nombreArch) {
        this.nombreArch = nombreArch;
        this.libros = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void crearArchivo() {
        try {
            File archivo = new File(nombreArch);
            if (archivo.createNewFile()) {
                try (FileWriter writer = new FileWriter(nombreArch)) {
                    gson.toJson(new ArrayList<Libro>(), writer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    public void cargarLibros() {
        try (FileReader reader = new FileReader(nombreArch)) {
            Type listType = new TypeToken<ArrayList<Libro>>(){}.getType();
            libros = gson.fromJson(reader, listType);
            if (libros == null) {
                libros = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            libros = new ArrayList<>();
        } catch (IOException e) {
            libros = new ArrayList<>();
        }
    }

    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nombreArch)) {
            gson.toJson(libros, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public void agregarLibro(Libro libro) {
        cargarLibros();
        libros.add(libro);
        guardarTodos();
    }

    public List<Libro> getLibros() {
        cargarLibros();
        return libros;
    }
}