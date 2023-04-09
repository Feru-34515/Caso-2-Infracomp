import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class ModeladoEntrada {


    public static String[] modeladoEntrada(String ruta) {
        File archivo = new File(ruta);
        String entrada = "";
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                entrada += linea + " ";
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arregloSubstrings = entrada.split(" ");

        return arregloSubstrings;
    }

    public static void modeladoSalida(String[] Entrada) {
            String NF = Entrada[0]; //Número filas
            String NC = Entrada[1]; //Número columnas
            String TE = Entrada[2]; //Tamaño entero
            String TP = Entrada[3]; //Tamaño de una página
            String MP = Entrada[4]; //Número de páginas o marcos de página
            int NR = Integer.parseInt(NF) * Integer.parseInt(NF) * 3;
            File salida = new File("src/Salida.txt");

           
            try (FileWriter fw = new FileWriter(salida)) {
                BufferedWriter bw = new BufferedWriter(fw);
                 //Descripción del problema
                bw.write("TP= " + TP); // NF x NC x TE
                bw.newLine();
                bw.write("NF= " + NF); 
                bw.newLine();
                bw.write("NC= " + NC);
                bw.newLine();
                bw.write("NR= " + NR); // NF x NC x Numero de matrices
                bw.newLine();
                //Referencias generadas
                int ciclos = Integer.parseInt(NF) * Integer.parseInt(NC) * Integer.parseInt(TE);
                int filasM = 0;
                for (int i = 0; i<=((ciclos - Integer.parseInt(TE)) / Integer.parseInt(TE)); i++){
                    for (int j = 0; j<3; j++){                        
                        if (j == 0){
                            bw.write("[A-" + filasM%Integer.parseInt(NF) + "-" + i%Integer.parseInt(NC) + "], " +  j + ", " + (i * Integer.parseInt(TE))%Integer.parseInt(TP)); 
                            bw.newLine();
                        }
                        else if (j == 1){
                            bw.write("[B-" + filasM%Integer.parseInt(NF) + "-" + i%Integer.parseInt(NC) + "], " +  j + ", " + (i * Integer.parseInt(TE))%Integer.parseInt(TP)); 
                            bw.newLine();
                        }
                        else if (j == 2){
                            bw.write("[C-" + filasM%Integer.parseInt(NF) + "-" + i%Integer.parseInt(NC) + "], " +  j + ", " + (i * Integer.parseInt(TE))%Integer.parseInt(TP)); 
                            bw.newLine();
                        }
                    }
                    if (i%Integer.parseInt(NF) == (i%Integer.parseInt(NF)-1)) {
                        filasM++;
                    }
                }
                
                bw.close();
                fw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



        
    }
}
