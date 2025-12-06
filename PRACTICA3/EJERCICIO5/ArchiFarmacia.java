package EJERCICIO5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArchiFarmacia {
    private String na;
    private List<Farmacia> farmacias;
    private Gson gson;

    public ArchiFarmacia(String na) {
        this.na = na;
        this.farmacias = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void crearArchivo() {
        try {
            File archivo = new File(na);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + na);
                try (FileWriter writer = new FileWriter(na)) {
                    gson.toJson(new ArrayList<Farmacia>(), writer);
                }
            } else {
                System.out.println("El archivo ya existe: " + na);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    public void cargarFarmacias() {
        try (FileReader reader = new FileReader(na)) {
            Type listType = new TypeToken<ArrayList<Farmacia>>(){}.getType();
            farmacias = gson.fromJson(reader, listType);
            if (farmacias == null) {
                farmacias = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            farmacias = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar farmacias: " + e.getMessage());
            farmacias = new ArrayList<>();
        }
    }

    private void guardarTodos() {
        try (FileWriter writer = new FileWriter(na)) {
            gson.toJson(farmacias, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public void archMedicamento(Farmacia farmacia) {
        cargarFarmacias();
        farmacias.add(farmacia);
        guardarTodos();
    }

    // a) Mostrar los medicamentos para la tos de la Sucursal número X
    public void mostrarMedicamentosParaTosSucursal(int sucursal) {
        cargarFarmacias();
        System.out.println("\n--- a) Medicamentos para la tos - Sucursal " + sucursal + " ---");

        boolean encontrado = false;
        for (Farmacia farmacia : farmacias) {
            if (farmacia.getSucursal() == sucursal) {
                List<Medicamento> medicamentosTos = farmacia.getMedicamentosPorTipo("tos");
                if (!medicamentosTos.isEmpty()) {
                    System.out.println("Farmacia: " + farmacia.getNombreFarmacia());
                    System.out.println("Dirección: " + farmacia.getDireccion());
                    for (Medicamento med : medicamentosTos) {
                        System.out.println("  - " + med);
                    }
                    encontrado = true;
                } else {
                    System.out.println("La sucursal no tiene medicamentos para la tos.");
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("Sucursal " + sucursal + " no encontrada.");
        }
    }

    // b) Mostrar el número de sucursal y su dirección que tienen el medicamento "Tapsin"
    public void mostrarSucursalConMedicamento(String nombreMedicamento) {
        cargarFarmacias();
        System.out.println("\n--- b) Sucursales con medicamento '" + nombreMedicamento + "' ---");

        boolean encontrado = false;
        for (Farmacia farmacia : farmacias) {
            Medicamento med = farmacia.buscarMedicamento(nombreMedicamento);
            if (med != null) {
                System.out.println("Sucursal: " + farmacia.getSucursal());
                System.out.println("Dirección: " + farmacia.getDireccion());
                System.out.println("Farmacia: " + farmacia.getNombreFarmacia());
                System.out.println("---");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró el medicamento '" + nombreMedicamento + "' en ninguna sucursal.");
        }
    }

    // c) Buscar medicamentos por tipo
    public void buscarMedicamentosPorTipo(String tipo) {
        cargarFarmacias();
        System.out.println("\n--- c) Medicamentos de tipo '" + tipo + "' ---");

        boolean encontrado = false;
        for (Farmacia farmacia : farmacias) {
            List<Medicamento> medicamentosTipo = farmacia.getMedicamentosPorTipo(tipo);
            if (!medicamentosTipo.isEmpty()) {
                System.out.println("\nFarmacia: " + farmacia.getNombreFarmacia() +
                        " (Sucursal " + farmacia.getSucursal() + ")");
                for (Medicamento med : medicamentosTipo) {
                    System.out.println("  - " + med);
                }
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron medicamentos del tipo '" + tipo + "'.");
        }
    }

    // d) Ordenar las farmacias según su dirección en orden alfabético
    public void ordenarFarmaciasPorDireccion() {
        cargarFarmacias();
        System.out.println("\n--- d) Farmacias ordenadas por dirección (alfabético) ---");

        if (farmacias.isEmpty()) {
            System.out.println("No hay farmacias registradas.");
            return;
        }

        farmacias.sort(Comparator.comparing(Farmacia::getDireccion));

        for (Farmacia farmacia : farmacias) {
            System.out.println(farmacia);
        }

        guardarTodos();
    }

    // e) Mover los medicamentos de tipo x de la farmacia y a la farmacia z
    public void moverMedicamentosTipo(String tipo, int sucursalOrigen, int sucursalDestino) {
        cargarFarmacias();
        System.out.println("\n--- e) Mover medicamentos tipo '" + tipo + "' de sucursal " +
                sucursalOrigen + " a sucursal " + sucursalDestino + " ---");

        Farmacia farmaciaOrigen = null;
        Farmacia farmaciaDestino = null;

        for (Farmacia farmacia : farmacias) {
            if (farmacia.getSucursal() == sucursalOrigen) {
                farmaciaOrigen = farmacia;
            }
            if (farmacia.getSucursal() == sucursalDestino) {
                farmaciaDestino = farmacia;
            }
        }

        if (farmaciaOrigen == null) {
            System.out.println("Sucursal origen " + sucursalOrigen + " no encontrada.");
            return;
        }

        if (farmaciaDestino == null) {
            System.out.println("Sucursal destino " + sucursalDestino + " no encontrada.");
            return;
        }

        List<Medicamento> medicamentosMover = farmaciaOrigen.getMedicamentosPorTipo(tipo);

        if (medicamentosMover.isEmpty()) {
            System.out.println("No hay medicamentos del tipo '" + tipo +
                    "' en la sucursal origen.");
            return;
        }

        // Mover medicamentos
        for (Medicamento med : medicamentosMover) {
            farmaciaDestino.insertarMedicamento(med);
            farmaciaOrigen.getMedicamentos().remove(med);
        }

        // Actualizar contador de medicamentos
        farmaciaOrigen.getMedicamentos().removeAll(medicamentosMover);

        System.out.println("Se movieron " + medicamentosMover.size() +
                " medicamentos del tipo '" + tipo + "'");
        System.out.println("De: " + farmaciaOrigen.getNombreFarmacia() +
                " (Sucursal " + sucursalOrigen + ")");
        System.out.println("A: " + farmaciaDestino.getNombreFarmacia() +
                " (Sucursal " + sucursalDestino + ")");

        guardarTodos();
    }

}