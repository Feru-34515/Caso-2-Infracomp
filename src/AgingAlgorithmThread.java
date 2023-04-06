class AgingAlgorithmThread implements Runnable {
    PageTable pageTable;

    public AgingAlgorithmThread(PageTable pageTable) {
        this.pageTable = pageTable;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
            pageTable.agePages();
        }
    }
}
