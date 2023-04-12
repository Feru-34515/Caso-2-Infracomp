import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class SegundoModo {
	

	public static String[][] crearRAM() {
       
        int filas = Main.marcos;
        String[][] RAM = new String[filas][2];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 2; j++) {
            	if (j== 0) {
            	RAM[i][j] = Integer.toString(i);
            	}
            	if (j== 1) {
                	RAM[i][j] = "0000";
                	}
            }
        }
 
        return RAM;
	}
	
	public static String[][] crearMAP(int Preales) {
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
        String[][] MAP = new String[Pvirtuales+1][2];
        for(int i = 0; i <= Pvirtuales; i++) {
        	for (int j = 0; j < 2; j++) {
        		if (j== 0) {
        			MAP[i][j] = Integer.toString(i);
                	}
        		if (j== 1) {
        			MAP[i][j] = "X";
                	}
        	}
        }
		return MAP;
	
	}
    //Primer Thread
    public static void actualizador(){
        int filasMAP = Main.MAP.length;
        int filasRAM = Main.RAM.length;
        int numFallos = 0;

        for(int i=0; i<filasMAP; i++){
            if (Main.MAP[i][1].equals("X")){
                numFallos ++;
                System.out.println("Se detectó el fallo #" + numFallos + " al poner la página " + i + " de la memoria virtual a la RAM");
                boolean menorEncontrado = false;
                int filasRAMRevisadas = 0;
                int filaDelMenor = 0;
                while (!menorEncontrado && filasRAMRevisadas < filasRAM){
                    if (Main.RAM[filasRAMRevisadas][1].equals("0000")) {
                        filaDelMenor = filasRAMRevisadas; 
                        menorEncontrado = true;
                    }
                    else if (Main.RAM[filasRAMRevisadas][1].equals("0010") && (Main.RAM[filaDelMenor][1].equals("0100") || Main.RAM[filaDelMenor][1].equals("1000"))){
                        filaDelMenor = filasRAMRevisadas;
                    }
                    else if (Main.RAM[filasRAMRevisadas][1].equals("0100") && Main.RAM[filaDelMenor][1].equals("1000")){
                        filaDelMenor = filasRAMRevisadas;
                    }
                    filasRAMRevisadas ++;
                    }
                    for (int j = 0; j< filasMAP; j++){
                        if (Main.MAP[j][1].equals(Integer.toString(filaDelMenor))){
                            Main.MAP[j][1] = "X";
                        }
                    }
                    Main.MAP[i][1] = Integer.toString(filaDelMenor);
                    System.out.println("Se corrigió el fallo de página vitual poniendo la referencia " + filaDelMenor + " como página de la RAM en el MAP") ;
                    algoritmoEnvejecimiento(filaDelMenor);
                }
                else{
                    algoritmoEnvejecimiento(i);

                }
            }
        
    }

    //Segundo Thread
    public synchronized static void algoritmoEnvejecimiento(int seleccionado){
        int filasRAM = Main.RAM.length;
        for (int j = 0; j< filasRAM; j++){
            if (j == seleccionado) {
                Main.RAM[j][1] = correrDerecha1(Main.RAM[j][1]);
            }
            else {
                Main.RAM[j][1] = correrDerecha0(Main.RAM[j][1]);
            }
        }
    }


    
    public synchronized static String correrDerecha0(String num){
        String rta;
        if (num.equals("1000")){
            rta = "0100";
        }
        else if (num.equals("0100")){
            rta = "0010";
        }
        else if (num.equals("0010")){
            rta = "0001";
        }
        else{
            rta = "0000";
        }
        return rta;

    }

    public static String correrDerecha1(String num){
        String rta;
        if (num.equals("1000")){
            rta = "1100";
        }
        else if (num.equals("0100")){
            rta = "1010";
        }
        else if (num.equals("0010")){
            rta = "1001";
        }
        else{
            rta = "1000";
        }
        return rta;

    }
    
}


