import java.util.ArrayList;
import java.util.List;

class MemoryManager {
    private List<PageFrame> pageFrames;
    private List<PageTableEntry> pageTable;

    public MemoryManager(int numFrames, int numPages) {
        pageFrames = new ArrayList<PageFrame>();
        for (int i = 0; i < numFrames; i++) {
            pageFrames.add(new PageFrame(-1));
        }

        pageTable = new ArrayList<PageTableEntry>();
        for (int i = 0; i < numPages; i++) {
            pageTable.add(new PageTableEntry(-1, false, false));
        }
    }

    public PageTableEntry getPageTableEntry(int pageId) {
        return pageTable.get(pageId);
    }

    public PageFrame getPageFrame(int index) {
        return pageFrames.get(index);
    }

    public int getNumFrames() {
        return pageFrames.size();
    }

    public int getNumPages() {
        return pageTable.size();
    }

    public boolean isPageLoaded(int pageId) {
        for (PageFrame frame : pageFrames) {
            if (frame.getPageId() == pageId) {
                return true;
            }
        }
        return false;
    }

    public void loadPage(int pageId) {
        for (PageFrame frame : pageFrames) {
            if (frame.getPageId() == -1) {
                frame.setPageId(pageId);
                return;
            }
        }
    }

    public void evictPage() {
        int minAge = Integer.MAX_VALUE;
        int evictIndex = -1;

        for (int i = 0; i < pageFrames.size(); i++) {
            PageFrame frame = pageFrames.get(i);

            if (frame.getAge() < minAge) {
                minAge = frame.getAge();
                evictIndex = i;
            }
        }

        if (evictIndex != -1) {
            pageFrames.get(evictIndex).setPageId(-1);
            pageFrames.get(evictIndex).setAge(0);
        }
    }

    public void agePages() {
        for (PageFrame frame : pageFrames) {
            if (frame.getPageId() != -1) {
                PageTableEntry entry = getPageTableEntry(frame.getPageId());
                entry.incrementAge();
            }
            frame.incrementAge();
        }
    }
}

