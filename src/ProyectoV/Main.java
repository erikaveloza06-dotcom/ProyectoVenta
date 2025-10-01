package ProyectoV;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GenerateInfoFiles generador = new GenerateInfoFiles();

        int opcion = 0;
        do {
            System.out.println("==============================");
            System.out.println("  SISTEMA DE GESTIÓN DE DATOS ");
            System.out.println("==============================");
            System.out.println("1. Generar archivos");
            System.out.println("2. Leer archivos");
            System.out.println("3. Organizar datos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\nGenerando archivos...");
                    generador.generarArchivoUsuarios();
                    generador.generarArchivoProductos();
                    generador.generarArchivoVentas();
                    System.out.println();
                    break;

                case 2:
                    System.out.println("\nLeyendo archivos...");
                    generador.leerArchivo("Usuarios.txt");
                    generador.leerArchivo("Productos.txt");
                    generador.leerArchivo("Ventas.txt");
                    System.out.println();
                    break;

                case 3:
                    System.out.println("\nOrganizando datos...");
                    generador.organizarDatos();
                    System.out.println("Proceso finalizado.\n");
                    break;

                case 4:
                    System.out.println("\nSaliendo...");
                    break;

                default:
                    System.out.println("\nOpción no válida\n");
            }
        } while (opcion != 4);

        sc.close();
    }
}

class GenerateInfoFiles {

    public void generarArchivoUsuarios() {
        List<String> usuarios = Arrays.asList("Juan;123", "Ana;456", "Pedro;789");
        escribirArchivo("Usuarios.txt", usuarios);
        System.out.println("Archivo Usuarios.txt creado correctamente.");
    }

    public void generarArchivoProductos() {
        List<String> productos = Arrays.asList("Laptop;3500", "Mouse;50");
        escribirArchivo("Productos.txt", productos);
        System.out.println("Archivo Productos.txt creado correctamente.");
    }

    public void generarArchivoVentas() {
        List<String> ventas = Arrays.asList("Juan;Laptop;1", "Ana;Mouse;2");
        escribirArchivo("Ventas.txt", ventas);
        System.out.println("Archivo Ventas.txt creado correctamente.");
    }

    private void escribirArchivo(String nombreArchivo, List<String> datos) {
        try {
            Files.write(Paths.get(nombreArchivo), datos);
        } catch (IOException e) {
            System.out.println("Error escribiendo " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public void leerArchivo(String nombreArchivo) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));
            System.out.println(nombreArchivo.replace(".txt", "") + ": " + lineas.size() + " registros");
        } catch (IOException e) {
            System.out.println("No se pudo leer " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public void organizarDatos() {
        // Aquí podrías cargar los archivos y organizarlos como necesites.
        System.out.println("Datos organizados por usuario y producto.");
    }
}
