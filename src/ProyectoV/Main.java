package ProyectoV;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Crear un objeto del generador de archivos
        GenerateInfoFiles generador = new GenerateInfoFiles();

        try {
            System.out.println("===========================================");
            System.out.println("  SISTEMA AUTOMÁTICO DE GESTIÓN DE DATOS");
            System.out.println("===========================================\n");

            // Paso 1: Generar los archivos de información
            System.out.println("Generando archivos...");
            generador.generarArchivoUsuarios();
            generador.generarArchivoProductos();
            generador.generarArchivoVentas();

            // Paso 2: Leer los archivos generados
            System.out.println("\nLeyendo archivos generados...");
            generador.leerArchivo("Usuarios.txt");
            generador.leerArchivo("Productos.txt");
            generador.leerArchivo("Ventas.txt");

            // Paso 3: Organizar los datos y generar reporte
            System.out.println("\nOrganizando y generando reporte...");
            generador.organizarDatos();

            // Paso 4: Mensaje de finalización
            System.out.println("\n✅ Ejecución finalizada exitosamente.");

        } catch (Exception e) {
            // Captura cualquier error durante la ejecución
            System.out.println("❌ Ocurrió un error durante la ejecución: " + e.getMessage());
        }
    }
}

// ============================================================
// CLASE SECUNDARIA: GenerateInfoFiles
// ------------------------------------------------------------
// Contiene los métodos que crean, leen y organizan los archivos.
// ============================================================
class GenerateInfoFiles {

    /**
     * Genera el archivo de usuarios con datos simulados.
     */
    public void generarArchivoUsuarios() {
        try {
            Random random = new Random();
            List<String> usuarios = new ArrayList<>();

            // Se crean 5 usuarios ficticios
            for (int i = 1; i <= 5; i++) {
                int codigo = 100 + random.nextInt(900);
                usuarios.add("Usuario" + i + ";" + codigo);
            }

            escribirArchivo("Usuarios.txt", usuarios);
            System.out.println("Archivo Usuarios.txt creado correctamente con " + usuarios.size() + " registros.");

        } catch (Exception e) {
            System.out.println("Error al generar Usuarios.txt: " + e.getMessage());
        }
    }

    /**
     * Genera el archivo de productos con datos simulados.
     */
    public void generarArchivoProductos() {
        try {
            List<String> productos = Arrays.asList(
                "Laptop;3500",
                "Mouse;50",
                "Teclado;120",
                "Monitor;800",
                "Impresora;600"
            );

            escribirArchivo("Productos.txt", productos);
            System.out.println("Archivo Productos.txt creado correctamente con " + productos.size() + " registros.");

        } catch (Exception e) {
            System.out.println("Error al generar Productos.txt: " + e.getMessage());
        }
    }

    /**
     * Genera el archivo de ventas con combinaciones aleatorias de usuario y producto.
     */
    public void generarArchivoVentas() {
        try {
            Random random = new Random();
            List<String> ventas = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                String usuario = "Usuario" + (1 + random.nextInt(5));
                String producto = switch (random.nextInt(5)) {
                    case 0 -> "Laptop";
                    case 1 -> "Mouse";
                    case 2 -> "Teclado";
                    case 3 -> "Monitor";
                    default -> "Impresora";
                };
                int cantidad = 1 + random.nextInt(3);
                ventas.add(usuario + ";" + producto + ";" + cantidad);
            }

            escribirArchivo("Ventas.txt", ventas);
            System.out.println("Archivo Ventas.txt creado correctamente con " + ventas.size() + " registros.");

        } catch (Exception e) {
            System.out.println("Error al generar Ventas.txt: " + e.getMessage());
        }
    }

    /**
     * Método privado reutilizable para escribir listas de datos en archivos.
     */
    private void escribirArchivo(String nombreArchivo, List<String> datos) throws IOException {
        Files.write(Paths.get(nombreArchivo), datos);
    }

    /**
     * Lee un archivo e imprime cuántos registros contiene.
     */
    public void leerArchivo(String nombreArchivo) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));
            System.out.println(nombreArchivo + ": " + lineas.size() + " registros leídos correctamente.");
        } catch (IOException e) {
            System.out.println("No se pudo leer " + nombreArchivo + ": " + e.getMessage());
        }
    }

    /**
     * Organiza los datos y genera un archivo de reporte final.
     */
    public void organizarDatos() {
        try {
            List<String> usuarios = Files.readAllLines(Paths.get("Usuarios.txt"));
            List<String> productos = Files.readAllLines(Paths.get("Productos.txt"));
            List<String> ventas = Files.readAllLines(Paths.get("Ventas.txt"));

            List<String> reporte = new ArrayList<>();
            reporte.add("=========== REPORTE GENERAL DEL SISTEMA ===========");
            reporte.add("Usuarios registrados: " + usuarios.size());
            reporte.add("Productos registrados: " + productos.size());
            reporte.add("Ventas registradas: " + ventas.size());
            reporte.add("Fecha de generación: " + new Date());
            reporte.add("====================================================");
            reporte.add("Datos procesados exitosamente. Sin errores detectados.");

            Files.write(Paths.get("Reporte.txt"), reporte);
            System.out.println("Archivo Reporte.txt generado exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}
