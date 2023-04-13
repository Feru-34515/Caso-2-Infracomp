public class Hilo2 extends Thread{
	 
	 
    public Hilo2 (){

       }
    
   
   
   public void run() {
    int filasRAM = Main.RAM.length;

    for (int j = 0; j< filasRAM; j++){
        if (Main.RAM[j][1].equals("1000")){
            Main.RAM[j][1] = "0100";
        }
        else if (Main.RAM[j][1].equals("0100")){
            Main.RAM[j][1] = "0010";
        }
        else if (Main.RAM[j][1].equals("0010")){
            Main.RAM[j][1] = "0001";
        }
        else{
            Main.RAM[j][1] = "0000";
        }

        }
        try {
                
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}