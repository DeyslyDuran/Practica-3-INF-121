package EJERCICIO1;

public class Charango {
    private String material;
    private int nroCuerdas;
    private boolean[] cuerdas;

    public Charango(String material, int nroCuerdas, boolean[] cuerdas) {
        this.material = material;
        this.nroCuerdas = nroCuerdas;
        this.cuerdas = cuerdas;
    }

    public String getMaterial() {
        return material;
    }

    public int getNroCuerdas() {
        return nroCuerdas;
    }

    public int contarCuerdasFalsas() {
        int contador = 0;
        for (boolean cuerda : cuerdas) {
            if (!cuerda) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Charango{");
        sb.append("material='").append(material).append('\'');
        sb.append(", nroCuerdas=").append(nroCuerdas);
        sb.append(", cuerdas=[");
        for (int i = 0; i < cuerdas.length; i++) {
            sb.append(cuerdas[i]);
            if (i < cuerdas.length - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
