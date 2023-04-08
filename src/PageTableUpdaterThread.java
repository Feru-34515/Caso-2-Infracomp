class PageTableUpdaterThread implements Runnable {
    PageTable pageTable;
    int[][] pageRefs;
    Config config;
    AgingAlgorithmThread agingAlgorithm;

    public PageTableUpdaterThread(PageTable pageTable, int[][] pageRefs, Config config, AgingAlgorithmThread agingAlgorithm) {
        this.pageTable = pageTable;
        this.pageRefs = pageRefs;
        this.config = config;
        this.agingAlgorithm = agingAlgorithm;
    }

    @Override
    public void run() {
        for (int i = 0; i < config.numRows; i++) {
            for (int j = 0; j < config.numCols; j++) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int pageIndex = pageRefs[i][j];
                pageTable.updatePageTable(pageIndex);
            }
            agingAlgorithm.setDone();
        }
    }
}
