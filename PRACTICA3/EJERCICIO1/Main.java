package EJERCICIO1;

// Main.java
public class Main {
    public static void main(String[] args) {
        CharangoManager manager = new CharangoManager();

        // Crear algunos charangos de ejemplo
        manager.agregarCharango(new Charango("Madera", 10,
                new boolean[]{true, true, false, false, false, false, false, false, true, true}));

        manager.agregarCharango(new Charango("Cedro", 10,
                new boolean[]{true, true, true, true, true, true, true, true, true, true}));

        manager.agregarCharango(new Charango("Pino", 8,
                new boolean[]{false, false, false, false, false, false, false, false}));

        manager.agregarCharango(new Charango("Cedro", 12,
                new boolean[]{true, false, true, false, true, false, true, false, true, false, true, false}));

        manager.agregarCharango(new Charango("Algarrobo", 10,
                new boolean[]{true, true, true, false, false, true, true, true, false, true}));

        // Guardar en archivo JSON
        manager.guardarEnArchivo();

        System.out.println("\n=== PROBANDO FUNCIONALIDADES ===");

        // Listar todos
        manager.listarTodos();

        // b) Eliminar charangos con más de 6 cuerdas falsas
        manager.eliminarConMasDe6CuerdasFalsas();
        manager.listarTodos();

        // c) Listar charangos de material "Cedro"
        manager.listarPorMaterial("Cedro");

        // d) Buscar charangos con 10 cuerdas
        manager.buscarCon10Cuerdas();

        // e) Ordenar por material
        manager.ordenarPorMaterial();

        // Guardar cambios
        manager.guardarEnArchivo();

        System.out.println("\n=== CARGANDO DESDE ARCHIVO ===");

        // Probar carga desde archivo
        CharangoManager manager2 = new CharangoManager();
        manager2.cargarDesdeArchivo();
        manager2.listarTodos();
    }
}