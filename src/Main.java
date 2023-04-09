
public class Main {
    public static void main(String[] args) {
        String ruta = ("src/Entrada.txt");
        String[] entrada = ModeladoEntrada.modeladoEntrada(ruta);
        ModeladoEntrada.modeladoSalida(entrada);
    }


}