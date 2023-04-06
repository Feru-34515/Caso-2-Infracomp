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
        PageTableUpdaterThread pageTableUpdater = new PageTableUpdaterThread(pageTable, pageRefs, config);
        AgingAlgorithmThread agingAlgorithm = new AgingAlgorithmThread(pageTable);

        Thread ptuThread = new Thread(pageTableUpdater);
        Thread aaThread = new Thread(agingAlgorithm);

        ptuThread.start();
        aaThread.start();

        try {
            ptuThread.join();
            aaThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
