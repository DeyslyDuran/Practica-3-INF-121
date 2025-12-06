package EJERCICIO5;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE FARMACIAS Y MEDICAMENTOS ===\n");

        ArchiFarmacia archivo = new ArchiFarmacia("farmacias.json");
        archivo.crearArchivo();

        // Crear farmacias
        Farmacia farmacia1 = new Farmacia("Farmacia Central", 1, "Av. 6 de Agosto");
        Farmacia farmacia2 = new Farmacia("Farmacia San Miguel", 2, "Calle Comercio");
        Farmacia farmacia3 = new Farmacia("Farmacia La Paz", 3, "Av. Arce");

        // Agregar medicamentos a Farmacia 1
        farmacia1.insertarMedicamento(new Medicamento("Tapsin", 101, "tos", 25.50));
        farmacia1.insertarMedicamento(new Medicamento("Paracetamol", 102, "fiebre", 8.00));
        farmacia1.insertarMedicamento(new Medicamento("Jarabe Tos", 103, "tos", 35.00));
        farmacia1.insertarMedicamento(new Medicamento("Ibuprofeno", 104, "dolor", 12.50));

        // Agregar medicamentos a Farmacia 2
        farmacia2.insertarMedicamento(new Medicamento("Tapsin", 201, "tos", 26.00));
        farmacia2.insertarMedicamento(new Medicamento("Amoxicilina", 202, "antibiótico", 45.00));
        farmacia2.insertarMedicamento(new Medicamento("Aspirina", 203, "dolor", 10.00));
        farmacia2.insertarMedicamento(new Medicamento("Jarabe Pectoral", 204, "tos", 38.50));

        // Agregar medicamentos a Farmacia 3
        farmacia3.insertarMedicamento(new Medicamento("Vitamina C", 301, "vitamina", 15.00));
        farmacia3.insertarMedicamento(new Medicamento("Diclofenaco", 302, "dolor", 18.00));
        farmacia3.insertarMedicamento(new Medicamento("Loratadina", 303, "alergia", 22.00));

        // Guardar farmacias en archivo
        archivo.archMedicamento(farmacia1);
        archivo.archMedicamento(farmacia2);
        archivo.archMedicamento(farmacia3);

        // a) Mostrar los medicamentos para la tos de la Sucursal número 1
        archivo.mostrarMedicamentosParaTosSucursal(1);

        // b) Mostrar el número de sucursal y su dirección que tienen el medicamento "Tapsin"
        archivo.mostrarSucursalConMedicamento("Tapsin");

        // c) Buscar medicamentos por tipo
        archivo.buscarMedicamentosPorTipo("dolor");

        // d) Ordenar las farmacias según su dirección en orden alfabético
        archivo.ordenarFarmaciasPorDireccion();

        // e) Mover los medicamentos de tipo "tos" de la farmacia 2 a la farmacia 3
        archivo.moverMedicamentosTipo("tos", 2, 3);

        // Verificar el movimiento
        System.out.println("\n--- Verificación después del movimiento ---");
        archivo.buscarMedicamentosPorTipo("tos");

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}