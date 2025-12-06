package EJERCICIO6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchCliente {
    private String nombreArch;
    private List<Cliente> clientes;
    private Gson gson;

    public ArchCliente(String nombreArch) {
        this.nombreArch = nombreArch;
        this.clientes = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void crearArchivo() {
        try {
            File archivo = new File(nombreArch);
            if (archivo.createNewFile()) {
                try (FileWriter writer = new FileWriter(nombreArch)) {
                    gson.toJson(new ArrayList<Cliente>(), writer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    public void cargarClientes() {
        try (FileReader reader = new FileReader(nombreArch)) {
            Type listType = new TypeToken<ArrayList<Cliente>>(){}.getType();
            clientes = gson.fromJson(reader, listType);
            if (clientes == null) {
                clientes = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            clientes = new ArrayList<>();
        } catch (IOException e) {
            clientes = new ArrayList<>();
        }
    }

    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(nombreArch)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public void agregarCliente(Cliente cliente) {
        cargarClientes();
        clientes.add(cliente);
        guardarTodos();
    }

    public List<Cliente> getClientes() {
        cargarClientes();
        return clientes;
    }
}
