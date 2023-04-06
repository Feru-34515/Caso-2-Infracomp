public class PageReference {
    private int pageNumber;
    private int processId;
    public int offset;
    
    public PageReference(int pageNumber, int processId) {
        this.pageNumber = pageNumber;
        this.processId = processId;
    }
    
    public int getPageNumber() {
        return pageNumber;
    }

    public int getOffset() {
        return offset;
    }

    public int getProcessId() {
        return processId;
    }
}
