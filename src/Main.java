
public class Main {
    public static void main(String[] args) {
        String ruta = ("src/Entrada2.txt");
        String[] entrada = ModeladoEntrada.modeladoEntrada(ruta);
        ModeladoEntrada.modeladoSalida(entrada);
        int marcos = ModeladoEntrada.sacarNPaginas(entrada);
        int[][] memoriaVirtual = MemoriaVirtual.crearMemoriaVirtual();
        int[][] RAM = MemoriaVirtual.crearRAM(marcos);
        int[][] MAP = MemoriaVirtual.crearMAP(marcos);
    }


}