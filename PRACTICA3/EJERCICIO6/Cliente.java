package EJERCICIO6;

public class Cliente {
    private int codCliente;
    private String ci;
    private String nombre;
    private String apellido;

    public Cliente(int codCliente, String ci, String nombre, String apellido) {
        this.codCliente = codCliente;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCodCliente() {
        return codCliente;
    }
    public String getCi() {
        return ci;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "codCliente=" + codCliente +
                ", ci='" + ci + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}