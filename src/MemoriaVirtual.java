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
        
        for (int a = 0; i < i; a++) {
            for (int b = 0; j < j; b++) {
            	MemoriaVirtual[a][b] = 1;
            }
        }

        return MemoriaVirtual;
        }
	
	public static int[][] crearRAM() {
       
        int filas = Main.marcos;
        int[][] RAM = new int[filas][2];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 2; j++) {
            	if (j== 0) {
            	RAM[i][j] = i;
            	}
            	if (j== 1) {
                	RAM[i][j] = 0000;
                	}
            }
        }
 
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
        	for (int j = 0; j < 2; j++) {
        		if (j== 0) {
        			MAP[i][j] = i;
                	}
        		if (j== 1) {
        			MAP[i][j] = -1;
                	}
        	}
        }
		return MAP;
	
	}
    
}
