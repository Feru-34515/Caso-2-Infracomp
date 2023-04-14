import java.util.concurrent.atomic.AtomicBoolean;
public class Hilo2 extends Thread{
    private MemoriaRam memoriaRam;
    

    public Hilo2(MemoriaRam memoriaRam, AtomicBoolean hilo1Terminado) {
        this.memoriaRam = memoriaRam;
    }
    
   
   
    public void run() {
    	System.out.println("------------------------------bits a la derecha----------------------------------------------------");
        int filasRAM = memoriaRam.getRAMSize();
        for (int i = 0; i< filasRAM; i++){
	        for (int j = 0; j< filasRAM; j++){
	        	
	            String currentValue = memoriaRam.getRAMValue(j);
	            String subcurrentValue = currentValue.substring(0, currentValue.length() - 1);
	            String Corrido = "0" + subcurrentValue;
	            memoriaRam.actualizarRAM(j, Corrido);
	            //System.out.println(Corrido);
	            }
        }
            try {
                    
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    
}
