import java.util.HashMap;
import java.util.Map;

class PageTable {
    Map<Integer, Page> pages;
    Config config;
    private int pageFaults;

    public PageTable(Config config) {
        this.config = config;
        pages = new HashMap<>();
    }

    public synchronized void updatePageTable(int pageIndex) {
        if (!pages.containsKey(pageIndex)) {
            if (pages.size() >= config.numPageFrames) {
                evictPage();
            }
            pages.put(pageIndex, new Page(pageIndex));
            pageFaults++;
        }
        pages.get(pageIndex).age |= 0b10000000;
    }

    public synchronized void agePages() {
        for (Page page : pages.values()) {
            page.age >>>= 1;
        }
    }

    private void evictPage() {
        int minAge = Integer.MAX_VALUE;
        int minPageIndex = -1;

        for (Map.Entry<Integer, Page> entry : pages.entrySet()) {
            if (entry.getValue().age < minAge) {
                minAge = entry.getValue().age;
                minPageIndex = entry.getKey();
            }
        }

        if (minPageIndex != -1) {
            pages.remove(minPageIndex);
        }
    }
}
