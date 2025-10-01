package ProyectoV;

import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GenerateInfoFiles {

    // Método que crea un archivo con los datos proporcionados
    public void generateFile(String fileName, List<String> data) {
        try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
            for (String line : data) {
                fileWriter.write(line + System.lineSeparator());
            }
            System.out.println("Archivo generado con éxito: " + fileName);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo: " + e.getMessage());
        }
    }

    // Método para leer y mostrar el contenido del archivo generado
    public void printFileContent(String fileName) {
        try {
            List<String> content = Files.readAllLines(Paths.get(fileName));
            System.out.println("Contenido del archivo " + fileName + ":");
            for (String line : content) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    // Método principal para probar la creación de archivos
    public static void main(String[] args) {
        GenerateInfoFiles generator = new GenerateInfoFiles();

        // Definimos varios archivos con sus datos
        Map<String, List<String>> archivos = new HashMap<>();
        
        archivos.put("usuarios.txt", Arrays.asList(
            "ID,Nombre,Edad,Correo",
            "1,Juan Pérez,30,juan@example.com",
            "2,Ana Gómez,25,ana@example.com"
        ));
        
        archivos.put("productos.txt", Arrays.asList(
            "ID,Nombre,Precio",
            "101,Laptop,3500.00",
            "102,Mouse,25.00"
        ));
        
        archivos.put("ventas.txt", Arrays.asList(
            "IDVenta,IDUsuario,IDProducto,Cantidad",
            "1001,1,101,1",
            "1002,2,102,2"
        ));

        // Generamos y mostramos todos los archivos
        for (Map.Entry<String, List<String>> entry : archivos.entrySet()) {
            generator.generateFile(entry.getKey(), entry.getValue());
            generator.printFileContent(entry.getKey());
        }
    }
}
