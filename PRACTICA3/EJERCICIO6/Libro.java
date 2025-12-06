package EJERCICIO6;

public class Libro {
    private int codLibro;
    private String titulo;
    private double precio;

    public Libro(int codLibro, String titulo, double precio) {
        this.codLibro = codLibro;
        this.titulo = titulo;
        this.precio = precio;
    }

    public int getCodLibro() {
        return codLibro;
    }
    public String getTitulo() {
        return titulo;
    }

    public double getPrecio() {
        return precio;
    }
    @Override
    public String toString() {
        return "Libro{" +
                "codLibro=" + codLibro +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                '}';
    }
}