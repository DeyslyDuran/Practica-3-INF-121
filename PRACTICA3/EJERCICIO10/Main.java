package EJERCICIO10;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE EMPRESAS E INSTITUCIONES ===\n");

        // Crear instancia del archivo
        ArchEmpresas archEmpresas = new ArchEmpresas("empresas.txt");

        // Crear archivo
        System.out.println("--- Crear archivo ---");
        archEmpresas.crearArchivo();

        // Guardar empresas
        System.out.println("\n--- Guardar empresas ---");
        archEmpresas.guardarEmpresa(new Empresa("TechSolutions", "Tecnología", 150));
        archEmpresas.guardarEmpresa(new Empresa("BuildCorp", "Construcción", 320));
        archEmpresas.guardarEmpresa(new Empresa("MediCare", "Salud", 280));
        archEmpresas.guardarEmpresa(new Empresa("EduFuture", "Educación", 95));
        archEmpresas.guardarEmpresa(new Empresa("FoodDelight", "Alimentación", 180));
        archEmpresas.guardarEmpresa(new Empresa("InnovateTech", "Tecnología", 220));
        archEmpresas.guardarEmpresa(new Empresa("GreenEnergy", "Energía", 140));
        archEmpresas.guardarEmpresa(new Empresa("FinanceGroup", "Finanzas", 450));

        // Mostrar todas las empresas
        System.out.println("\n=== MOSTRAR TODAS LAS EMPRESAS ===");
        archEmpresas.mostrarTodasLasEmpresas();

        // Buscar empresa por nombre
        System.out.println("\n=== BUSCAR EMPRESA POR NOMBRE ===");
        archEmpresas.buscarEmpresaPorNombre("MediCare");
        archEmpresas.buscarEmpresaPorNombre("NoExiste");

        // Mostrar empresas por rubro
        System.out.println("\n=== EMPRESAS POR RUBRO ===");
        archEmpresas.mostrarEmpresasPorRubro("Tecnología");

        // Mostrar empresa con más empleados
        System.out.println("\n=== EMPRESA CON MÁS EMPLEADOS ===");
        archEmpresas.mostrarEmpresaMasEmpleados();

        // Calcular total de empleados
        System.out.println("\n=== TOTAL DE EMPLEADOS ===");
        archEmpresas.calcularTotalEmpleados();

        // Modificar empresa
        System.out.println("\n=== MODIFICAR EMPRESA ===");
        archEmpresas.modificarEmpresa("EduFuture", "EduFuture Academy", "Educación Superior", 120);

        // Mostrar después de modificación
        archEmpresas.mostrarTodasLasEmpresas();

        // Eliminar empresa
        System.out.println("\n=== ELIMINAR EMPRESA ===");
        archEmpresas.eliminarEmpresa("GreenEnergy");

        // Mostrar después de eliminación
        archEmpresas.mostrarTodasLasEmpresas();

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}