package EJERCICIO5;

import java.util.ArrayList;
import java.util.List;

public class Farmacia {
    private String nombreFarmacia;
    private int sucursal;
    private String direccion;
    private int nroMedicamentos;
    private List<Medicamento> medicamentos;

    public Farmacia(String nombreFarmacia, int sucursal, String direccion) {
        this.nombreFarmacia = nombreFarmacia;
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.medicamentos = new ArrayList<>();
        this.nroMedicamentos = 0;
    }

    public String getNombreFarmacia() {
        return nombreFarmacia;
    }
    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void insertarMedicamento(Medicamento medicamento) {
        medicamentos.add(medicamento);
        nroMedicamentos = medicamentos.size();
    }

    public List<Medicamento> getMedicamentosPorTipo(String tipo) {
        List<Medicamento> resultado = new ArrayList<>();
        for (Medicamento med : medicamentos) {
            if (med.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(med);
            }
        }
        return resultado;
    }

    public Medicamento buscarMedicamento(String nombre) {
        for (Medicamento med : medicamentos) {
            if (med.getNombre().equalsIgnoreCase(nombre)) {
                return med;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "nombreFarmacia='" + nombreFarmacia + '\'' +
                ", sucursal=" + sucursal +
                ", direccion='" + direccion + '\'' +
                ", nroMedicamentos=" + nroMedicamentos +
                '}';
    }
}
