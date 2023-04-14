public class Hilo2 extends Thread{
	 
    private MemoriaRam memoriaRam;

    public Hilo2(MemoriaRam memoriaRam) {
        this.memoriaRam = memoriaRam;
    }
    
   
   
   public void run() {
    System.out.println("Hola");
    int filasRAM = memoriaRam.getRAMSize();

    for (int j = 0; j< filasRAM; j++){
        String currentValue = memoriaRam.getRAMValue(j);
        switch (currentValue) {
            case "1000":
                memoriaRam.actualizarRAM(j, "0100");
                break;
            case "0100":
                memoriaRam.actualizarRAM(j, "0010");
                break;
            case "0010":
                memoriaRam.actualizarRAM(j, "0001");
                break;
            default:
                memoriaRam.actualizarRAM(j, "0000");
                break;
        }

        }
        try {
                
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}