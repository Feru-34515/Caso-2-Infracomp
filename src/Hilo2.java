public class Hilo2 extends Thread{
    private int Seleccionado;
	 
	 
	 public Hilo2 (int Seleccionado ){
	        this.Seleccionado = Seleccionado;

	    }
	 
	 
	public int getSeleccionado() {
		return Seleccionado;
	}
	public void setSeleccionado(int seleccionado) {
		Seleccionado = seleccionado;
	}

    public void run() {
        int filasRAM = Main.RAM.length;
        for (int j = 0; j< filasRAM; j++){
            if (j == Seleccionado) {
                Main.RAM[j][1] = SegundoModo.correrDerecha1(Main.RAM[j][1]);
            }
            else {
                Main.RAM[j][1] = SegundoModo.correrDerecha0(Main.RAM[j][1]);
            }
        }

    }
}
