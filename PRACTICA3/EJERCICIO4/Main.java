package EJERCICIO4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE ESTUDIANTES Y NOTAS ===\n");

        // a) Crear instancia del archivo
        ArchiNota archivo = new ArchiNota("notas.json");
        archivo.crearArchivo();

        // Crear estudiantes
        Estudiante est1 = new Estudiante(2021001, "Juan", "Pérez", "García", 20);
        Estudiante est2 = new Estudiante(2021002, "María", "López", "Martínez", 19);
        Estudiante est3 = new Estudiante(2021003, "Carlos", "Rodríguez", "Silva", 21);
        Estudiante est4 = new Estudiante(2021004, "Ana", "Fernández", "Torres", 20);
        Estudiante est5 = new Estudiante(2021005, "Pedro", "González", "Ramírez", 22);

        // b) Agregar varios estudiantes con sus notas
        System.out.println("\n--- b) Agregar varios estudiantes ---");
        List<Nota> notasIniciales = new ArrayList<>();

        // Notas de Matemáticas
        notasIniciales.add(new Nota("Matemáticas", 85.5, est1));
        notasIniciales.add(new Nota("Matemáticas", 92.0, est2));
        notasIniciales.add(new Nota("Matemáticas", 78.5, est3));
        notasIniciales.add(new Nota("Matemáticas", 95.0, est4));
        notasIniciales.add(new Nota("Matemáticas", 45.0, est5));

        // Notas de Física
        notasIniciales.add(new Nota("Física", 88.0, est1));
        notasIniciales.add(new Nota("Física", 76.5, est2));
        notasIniciales.add(new Nota("Física", 91.0, est3));
        notasIniciales.add(new Nota("Física", 82.5, est4));
        notasIniciales.add(new Nota("Física", 48.0, est5));

        // Notas de Programación
        notasIniciales.add(new Nota("Programación", 96.5, est1));
        notasIniciales.add(new Nota("Programación", 89.0, est2));
        notasIniciales.add(new Nota("Programación", 93.5, est3));
        notasIniciales.add(new Nota("Programación", 87.0, est4));
        notasIniciales.add(new Nota("Programación", 72.5, est5));

        archivo.agregarEstudiantes(notasIniciales);

        // Listar todas las notas
        archivo.listarNotas();

        // c) Obtener el promedio de notas de todos los estudiantes
        System.out.println("\n--- c) Promedio de notas ---");
        archivo.obtenerPromedioGeneral();

        // d) Buscar al o los estudiantes con la mejor nota
        System.out.println("\n--- d) Estudiantes con mejor nota ---");
        archivo.buscarEstudiantesConMejorNota();


        // e) Eliminar a todos los estudiantes de una determinada materia
        System.out.println("\n--- e) Eliminar estudiantes por materia ---");
        archivo.eliminarEstudiantesPorMateria("Física");

        // Listar notas después de la eliminación
        archivo.listarNotas();

        // Calcular nuevo promedio
        archivo.obtenerPromedioGeneral();

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}