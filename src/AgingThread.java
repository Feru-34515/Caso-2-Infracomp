import java.util.List;

public class AgingThread implements Runnable {

    private List<PageTableEntry> pageTable;
    private int agingFactor;

    public AgingThread(List<PageTableEntry> pageTable, int agingFactor) {
        this.pageTable = pageTable;
        this.agingFactor = agingFactor;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(agingFactor * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (PageTableEntry entry : pageTable) {
                entry.aging(agingFactor);
            }
        }
    }
}
