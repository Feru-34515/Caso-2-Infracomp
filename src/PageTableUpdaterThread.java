class PageTableUpdaterThread implements Runnable {
    PageTable pageTable;
    int[][] pageRefs;
    Config config;

    public PageTableUpdaterThread(PageTable pageTable, int[][] pageRefs, Config config) {
        this.pageTable = pageTable;
        this.pageRefs = pageRefs;
        this.config = config;
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
        }
    }
}
