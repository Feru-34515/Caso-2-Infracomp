
public class Hilo1 extends Thread{

    private int filaMAP;
	private int filaDelMenorRAM;

	public Hilo1 (int filaMAP, int  filaDelMenorRAM){
	        this.filaMAP = filaMAP;
			this.filaDelMenorRAM = filaDelMenorRAM;
	}

	// Getter para filaMAP
	public int getFilaMAP() {
		return filaMAP;
	}

	// Setter para filaMAP
	public void setFilaMAP(int filaMAP) {
		this.filaMAP = filaMAP;
	}

	// Getter para filaDelMenorRAM
	public int getFilaDelMenorRAM() {
		return filaDelMenorRAM;
	}

	// Setter para filaDelMenorRAM
	public void setFilaDelMenorRAM(int filaDelMenorRAM) {
		this.filaDelMenorRAM = filaDelMenorRAM;
	}

	 
	public void run() {
		int filasMAP = Main.MAP.length;

	for (int j = 0; j< filasMAP; j++){
		if (Main.MAP[j][1].equals(Integer.toString(filaDelMenorRAM))){
			Main.MAP[j][1] = "X";
		}
	}
	Main.MAP[filaMAP][1] = Integer.toString(filaDelMenorRAM);
	try {
		Thread.sleep(2); // Duermo el thread por 2 milisegundos
	} catch (InterruptedException e) {
		e.printStackTrace();
	}


        
    }

	

		


}
