public class MemoriaRam {
    private String[][] MAP;
    private String[][] RAM;

    public MemoriaRam(String[][] MAP, String[][] RAM) {
        this.MAP = MAP;
        this.RAM = RAM;
    }

    public synchronized int getMapSize() {
        return MAP.length;
    }

    public synchronized int getRAMSize() {
        return RAM.length;
    }

    public synchronized String getMapValue(int index) {
        return MAP[index][1];
    }

    public synchronized void actualizarMAP(int index, String value) {
        MAP[index][1] = value;
    }

    public synchronized String getRAMValue(int index) {
        return RAM[index][1];
    }

    public synchronized void actualizarRAM(int index, String value) {
        RAM[index][1] = value;
    }
}
