
public class Hilo1 extends Thread{

    private MemoriaRam memoriaRam;
	 
	 
	 public Hilo1 (MemoriaRam memoriaRam){
        this.memoriaRam = memoriaRam;
	    }
	 
	
	
	public void run() {

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
                    if (memoriaRam.getRAMValue(filasRAMRevisadas).equals("0000")) {
                        filaDelMenor = filasRAMRevisadas; 
                        menorEncontrado = true;
                    }
                    else if (memoriaRam.getRAMValue(filasRAMRevisadas).equals("0010") && (memoriaRam.getRAMValue(filaDelMenor).equals("0100") || memoriaRam.getRAMValue(filaDelMenor).equals("1000"))){
                        filaDelMenor = filasRAMRevisadas;
                    }
                    else if (memoriaRam.getRAMValue(filasRAMRevisadas).equals("0100") && memoriaRam.getRAMValue(filaDelMenor).equals("1000")){
                        filaDelMenor = filasRAMRevisadas;
                    }
                    filasRAMRevisadas ++;
                    }
                    for (int j = 0; j< filasMAP; j++){
                        if (memoriaRam.getMapValue(j).equals(Integer.toString(filaDelMenor))){
                            memoriaRam.actualizarMAP(j, "X");
                        }
                    }
                    memoriaRam.actualizarRAM(filaDelMenor, Poner1(memoriaRam.getRAMValue(filaDelMenor)));
                    System.out.println("Se corrigió el fallo de página vitual poniendo la referencia " + filaDelMenor + " como página de la RAM en el MAP") ;
                    Main.RAM[filaDelMenor][1] = Poner1(Main.RAM[filaDelMenor][1]);
                }
                else{
                    memoriaRam.actualizarRAM(i, Poner1(memoriaRam.getRAMValue(i)));


                }
            }
			try {
                
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	
	}
	
	public static String Poner1(String num){
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
