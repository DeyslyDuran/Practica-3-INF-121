package EJERCICIO4;

public class Nota {
    private String materno;
    private double notaFinal;
    private Estudiante estudiante;

    public Nota(String materno, double notaFinal, Estudiante estudiante) {
        this.materno = materno;
        this.notaFinal = notaFinal;
        this.estudiante = estudiante;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "materno='" + materno + '\'' +
                ", notaFinal=" + notaFinal +
                ", estudiante=" + estudiante.getNombreCompleto() +
                " (RU: " + estudiante.getRu() + ")" +
                '}';
    }
}