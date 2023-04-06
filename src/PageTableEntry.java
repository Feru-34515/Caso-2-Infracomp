public class PageTableEntry {
    private int frameNumber;
    private boolean inMemory;
    private boolean referenced;
    private long lastAccessTime;
    private int age;

    public PageTableEntry(int frameNumber, boolean inMemory, boolean referenced) {
        this.frameNumber = frameNumber;
        this.inMemory = inMemory;
        this.referenced = referenced;
        this.lastAccessTime = System.nanoTime();
        this.age = 0;
    }

    public PageTableEntry(int frameNumber, boolean inMemory, boolean referenced, long lastAccessTime, int age) {
        this.frameNumber = frameNumber;
        this.inMemory = inMemory;
        this.referenced = referenced;
        this.lastAccessTime = lastAccessTime;
        this.age = age;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getFrameNumber() {
        return this.frameNumber;
    }

    public void setInMemory(boolean inMemory) {
        this.inMemory = inMemory;
    }

    public boolean isInMemory() {
        return this.inMemory;
    }

    public void setReferenced(boolean referenced) {
        this.referenced = referenced;
    }

    public boolean isReferenced() {
        return this.referenced;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public long getLastAccessTime() {
        return this.lastAccessTime;
    }

    public void incrementAge() {
        this.age++;
    }

    public int getAge() {
        return this.age;
    }

    public void aging(int agingFactor) {
        this.age = this.age >> agingFactor;
        if (this.referenced) {
            this.age |= 1 << (31 - agingFactor);
        }
        this.referenced = false;
    }
}
