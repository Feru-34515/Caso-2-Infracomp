import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class MemoryManager {
    PageTable pageTable;
    Config config;
    int[][] pageRefs;

    public MemoryManager(Config config, int[][] pageRefs) {
        this.config = config;
        this.pageRefs = pageRefs;
        pageTable = new PageTable(config);
    }

    public void simulateProcessBehavior(MatrixAdditionProcess process) {
        process.tour1();
        int[][] pageReferences = process.generatePageReferences();

        for (int i = 0; i < config.numRows; i++) {
            for (int j = 0; j < config.numCols; j++) {
                int pageIndex = pageReferences[i][j];
                pageTable.updatePageTable(pageIndex);
            }
        }
    }

    public void simulatePagingSystem() {
        AgingAlgorithmThread agingAlgorithm = new AgingAlgorithmThread(pageTable);
        PageTableUpdaterThread pageTableUpdater = new PageTableUpdaterThread(pageTable, pageRefs, config, agingAlgorithm);
        
        System.out.println("Starting PageTableUpdaterThread...");
        Thread ptuThread = new Thread(pageTableUpdater);
        System.out.println("Starting AgingAlgorithmThread...");
        Thread aaThread = new Thread(agingAlgorithm);

        ptuThread.start();
        aaThread.start();

        try {
            System.out.println("Joining PageTableUpdaterThread...");
            ptuThread.join();
            System.out.println("PageTableUpdaterThread joined.");
            System.out.println("Joining AgingAlgorithmThread...");
            aaThread.join();
            System.out.println("AgingAlgorithmThread joined.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
