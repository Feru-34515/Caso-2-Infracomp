
public class Main {
	public static String[] entrada = ModeladoEntrada.modeladoEntrada("src/Entrada2.txt");
	public static int marcos = ModeladoEntrada.sacarNPaginas(entrada);
    //Creación estructuras de datos del segundo proceso
    public static String[][] RAM = SegundoModo.crearRAM();
    public static String[][] MAP = SegundoModo.crearMAP(marcos);
    
    
    
    
    public static void main(String[] args) {
        //Primer Proceso
        
        ModeladoEntrada.modeladoSalida(entrada);
        //Estructura segundo proceso
        
        
        //Creacionés Threads 

        // Un thread se encargará de ir actualizando el estado de la tabla de páginas y los marcos de página en memoria 
        // real, de acuerdo con las referencias generadas por el proceso y el número de marcos de página asignados. Este 
        // thread debe correr cada dos milisegundos (en vez de pulsos de reloj usaremos milisegundos).
        Hilo1 actualizador = new Hilo1();

        // El otro thread se encargará de ejecutar el algoritmo de envejecimiento (con base en el esquema presentado por 
        // Tanenbaum). Este thread debe correr cada milisegundo (en vez de pulsos de reloj usaremos milisegundos). 
        Hilo2 envejeciemiento = new Hilo2();
        
    }
    
    


}