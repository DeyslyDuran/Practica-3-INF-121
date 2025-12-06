package EJERCICIO7;

public class Niño extends Persona {
    private int edad;
    private String peso;
    private String talla;

    public Niño(String nombre, String apellidoPaterno, String apellidoMaterno, int ci, int edad, String peso, String talla) {
        super(nombre, apellidoPaterno, apellidoMaterno, ci);
        this.edad = edad;
        this.peso = peso;
        this.talla = talla;
    }

    public int getEdad() {
        return edad;
    }

    public String getPeso() {
        return peso;
    }

    public String getTalla() {
        return talla;
    }

    @Override
    public String toString() {
        return "Niño{" +
                "nombre='" + getNombre() + '\'' +
                ", apellidoPaterno='" + getApellidoPaterno() + '\'' +
                ", apellidoMaterno='" + getApellidoMaterno() + '\'' +
                ", ci=" + getCi() +
                ", edad=" + edad +
                ", peso='" + peso + '\'' +
                ", talla='" + talla + '\'' +
                '}';
    }
}