class AgingAlgorithmThread implements Runnable {
    PageTable pageTable;
    volatile boolean isDone; // Add this flag

    public AgingAlgorithmThread(PageTable pageTable) {
        this.pageTable = pageTable;
        this.isDone = false; // Initialize it to false
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public void run() {
        while (!isDone) { // Change the condition to check the flag
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
            pageTable.agePages();
        }
    }
}
