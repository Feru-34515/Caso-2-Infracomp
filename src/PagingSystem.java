import java.util.ArrayList;
import java.util.List;

public class PagingSystem {
    private MemoryManager memoryManager;
    private AgingThread agingThread;

    public PagingSystem(int numFrames, int numPages, int agingFactor) {
        memoryManager = new MemoryManager(numFrames, numPages);
        agingThread = new AgingThread(memoryManager.getPageTable(), agingFactor);
        new Thread(agingThread).start();
    }

    public void accessPage(PageReference ref) {
        PageTableEntry entry = memoryManager.getPageTableEntry(ref.getPageNumber());

        if (!entry.isInMemory()) {
            if (memoryManager.isPageLoaded(ref.getPageNumber())) {
                int frameNumber = memoryManager.getPageFrameNumber(ref.getPageNumber());
                entry.setFrameNumber(frameNumber);
                entry.setInMemory(true);
                memoryManager.getPageFrame(frameNumber).setAge(0);
            } else {
                memoryManager.loadPage(ref.getPageNumber());
                entry.setFrameNumber(memoryManager.getLoadedFrameNumber());
                entry.setInMemory(true);
            }
        }

        entry.setReferenced(true);
        entry.setLastAccessTime(System.nanoTime());
        memoryManager.agePages();
    }

    public void printPageTable() {
        System.out.println("Page Table:");
        System.out.printf("%-10s %-10s %-10s %-15s %-10s\n", "Page ID", "Frame ID", "In Memory", "Referenced", "Age");

        List<PageTableEntry> pageTable = memoryManager.getPageTable();
        for (int i = 0; i < pageTable.size(); i++) {
            PageTableEntry entry = pageTable.get(i);
            String inMemoryStr = entry.isInMemory() ? "Yes" : "No";
            String referencedStr = entry.isReferenced() ? "Yes" : "No";
            System.out.printf("%-10s %-10s %-10s %-15s %-10s\n", i, entry.getFrameNumber(), inMemoryStr, referencedStr, entry.getAge());
        }
    }

    public void printPageFrames() {
        System.out.println("Page Frames:");
        System.out.printf("%-10s %-10s %-10s\n", "Frame ID", "Page ID", "Age");

        List<PageFrame> pageFrames = memoryManager.getPageFrames();
        for (int i = 0; i < pageFrames.size(); i++) {
            PageFrame frame = pageFrames.get(i);
            System.out.printf("%-10s %-10s %-10s\n", i, frame.getPageId(), frame.getAge());
        }
    }
}
