
public class Main {
	public static String[] entrada = PrimerModo.modeladoEntrada("src/Entrada2.txt");
	public static int marcos = PrimerModo.sacarNPaginas(entrada);
    //Creación estructuras de datos para simulación del segundo modo
    public static String[][] RAM = SegundoModo.crearRAM();
    public static String[][] MAP = SegundoModo.crearMAP(marcos);

    

    public static void main(String[] args) {
        // Creación texto de primer Modo
        PrimerModo.modeladoSalida(entrada);
        /**
        Un thread se encargará de ir actualizando el estado de la tabla de páginas y los marcos de página en memoria 
        real, de acuerdo con las referencias generadas por el proceso y el número de marcos de página asignados. Este 
        thread debe correr cada dos milisegundos (en vez de pulsos de reloj usaremos milisegundos).
        **/
        Hilo1 actualizador = new Hilo1(-1, -1);
        

        /**
        El otro thread se encargará de ejecutar el algoritmo de envejecimiento (con base en el esquema presentado por 
        Tanenbaum). Este thread debe correr cada milisegundo (en vez de pulsos de reloj usaremos milisegundos). 
        **/
        Hilo2 envejecimiento = new Hilo2(-1);

        //Segundo Modo
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
                    actualizador.setFilaDelMenorRAM(filaDelMenor);
                    actualizador.setFilaMAP(i);
                    actualizador.start();

                    System.out.println("Se corrigió el fallo de página vitual poniendo la referencia " + filaDelMenor + " como página de la RAM en el MAP") ;
                    envejecimiento.setSeleccionado(filaDelMenor);
                }
                else{
                    envejecimiento.setSeleccionado(i);

                }
            envejecimiento.start();
            }
  
    }


    
    


}