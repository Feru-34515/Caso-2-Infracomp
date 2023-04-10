import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class MemoriaVirtual {
	
	public static int[][] crearMemoriaVirtual() {
        String archivo = "src/Salida.txt"; // Ruta del archivo de texto a leer
        List<String> ultimaFila = null; // Variable para almacenar la última fila

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Leer el archivo y almacenar la última fila
            while ((linea = br.readLine()) != null) {
                ultimaFila = List.of(linea.split(", "));;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int i = Integer.parseInt(ultimaFila.get(1));
        int j = Integer.parseInt(ultimaFila.get(2));
        int[][] MemoriaVirtual = new int[i][j];

        return MemoriaVirtual;
        }
	
	public static int[][] crearRAM(int marcos) {
        String archivo = "src/Salida.txt"; // Ruta del archivo de texto a leer
        List<String> ultimaFila = null; // Variable para almacenar la última fila

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Leer el archivo y almacenar la última fila
            while ((linea = br.readLine()) != null) {
                ultimaFila = List.of( linea.split(", "));;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int i = marcos;
        int j = Integer.parseInt(ultimaFila.get(2));
        int[][] RAM = new int[i][j];
 
        return RAM;
	}
	
	public static int[][] crearMAP(int Preales) {
		String archivo = "src/Salida.txt"; // Ruta del archivo de texto a leer
        List<String> ultimaFila = null; // Variable para almacenar la última fila

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Leer el archivo y almacenar la última fila
            while ((linea = br.readLine()) != null) {
                ultimaFila = List.of(linea.split(", "));;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int Pvirtuales = Integer.parseInt(ultimaFila.get(1));
        int[][] MAP = new int[Pvirtuales][2];
        for(int i = 0; i< Pvirtuales; i++) {
        	MAP[i][0] = i;
        	MAP[i][1] = i%(Preales);
        	System.out.println(MAP[i][0]);
            System.out.println(MAP[i][1]);
        }
        
		return MAP;
	
	}
    
}
