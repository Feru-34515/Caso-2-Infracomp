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

    public static void algoritmoEnvejecimiento(int[][] MAP, int[][] RAM){
        int filasMAP = MAP.length;
        int filasRAM = RAM.length;
        int numFallos = 0;
        int menor = 10000;
        int llave = 0;
        boolean centinela = false;

        for(int i=0; i<filasMAP; i++){
            menor = 10000;
            if(MAP[i][1] == -1){
                centinela = false;
                numFallos++;
                //Iterar sobre la RAM
                for(int j=0; j<filasRAM; j++){
                    //Caso en el que hay espacios en la Memoria Virtual que no han sido modificados aun
                    if(RAM[j][1] == 0){
                        centinela = true;
                        MAP[i][1] = i;
                        RAM[j][1] = 1000;
                        //Si se esta cambiando el valor en un espacio de la RAM que no sea el 0, se actualizan los anteriores
                        if (j>0){
                            for(int k=0; k<j; k++){
                                if(RAM[k][1] == 1000){
                                    RAM[k][1] = 100;
                                }
                                else if(RAM[k][1] == 100){
                                    RAM[k][1] = 10;
                                }
                                else if(RAM[k][1] == 10){
                                    RAM[k][1] = 1;
                                }
                            }
                        }
                        break;
                    }
                    //En caso de que no haya ningun espacio en la RAM con valor 0000, se encuentra el menor:
                    else{
                    //Convertir el valor del binario de la Memoria Virtual a decimal
                    String binario = Integer.toString(RAM[j][1]);
                    int valor = convertirBinarioADecimal(binario);
                    if (valor < menor){
                        menor = valor;
                        llave = j;
                    }
                    }
 
                }
                if (centinela == false){
                    MAP[llave][1] = -1;
                    MAP[i][1] = llave;

                    if(RAM[llave][1] == 10){
                        RAM[llave][1] = 1001;
                    }
                    else if(RAM[llave][1] == 100){
                        RAM[llave][1] = 1010;
                    }
                    else if(RAM[llave][1] == 1000){
                        RAM[llave][1] = 1100;
                    }
                    
                    if (llave>0){
                        for(int h=0; h<llave; h++){
                            if (RAM[h][1] == 10){
                                RAM[h][1] = 1;
                            }
                            else if (RAM[h][1] == 100){
                                RAM[h][1] = 10;
                            }
                            else if (RAM[h][1] == 1000){
                                RAM[h][1] = 100;
                            }
                            else if (RAM[h][1] == 1010){
                                RAM[h][1] = 101;
                            }
                            else if (RAM[h][1] == 1001){
                                RAM[h][1] = 100;
                            }
                        }
                        for(int h=llave+1; h<filasRAM; h++){
                            if (RAM[h][1] == 10){
                                RAM[h][1] = 1;
                            }
                            else if (RAM[h][1] == 100){
                                RAM[h][1] = 10;
                            }
                            else if (RAM[h][1] == 1000){
                                RAM[h][1] = 100;
                            }
                            else if (RAM[h][1] == 1010){
                                RAM[h][1] = 101;
                            }
                            else if (RAM[h][1] == 1001){
                                RAM[h][1] = 100;
                            }
                        }
                    } 
                    else{
                        for(int h=llave+1; h<filasRAM; h++){
                            if (RAM[h][1] == 10){
                                RAM[h][1] = 1;
                            }
                            else if (RAM[h][1] == 100){
                                RAM[h][1] = 10;
                            }
                            else if (RAM[h][1] == 1000){
                                RAM[h][1] = 100;
                            }
                            else if (RAM[h][1] == 1010){
                                RAM[h][1] = 101;
                            }
                            else if (RAM[h][1] == 1001){
                                RAM[h][1] = 100;
                            }
                        }
                    }    
                }
            }
        }
    }
    
    public static int convertirBinarioADecimal(String binario){
        int decimal = 0;
        int longitud = binario.length();

        for (int i = 0; i<longitud; i++){
            char digitoBinario = binario.charAt(i);
            if(digitoBinario == '1'){
                decimal += Math.pow (2, longitud-i-1);
            }
            
            else if(digitoBinario != '0'){
                throw new IllegalArgumentException("El numero binario debe contener solo 0s y 1s");
            }
        }
        return decimal;
    }
    
}
