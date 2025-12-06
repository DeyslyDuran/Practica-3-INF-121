package EJERCICIO9;

import java.util.ArrayList;
import java.util.List;

public class Zoologico {
    private int id;
    private String nombre;
    private int nroAnimales;
    private List<Animal> animales;

    public Zoologico(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.animales = new ArrayList<>();
        this.nroAnimales = 0;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroAnimales() {
        return nroAnimales;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void agregarAnimal(Animal animal) {
        animales.add(animal);
        nroAnimales = animales.size();
    }

    public void eliminarAnimal(String nombreAnimal) {
        animales.removeIf(a -> a.getNombre().equalsIgnoreCase(nombreAnimal));
        nroAnimales = animales.size();
    }

    @Override
    public String toString() {
        return "Zoologico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nroAnimales=" + nroAnimales +
                ", animales=" + animales +
                '}';
    }
}