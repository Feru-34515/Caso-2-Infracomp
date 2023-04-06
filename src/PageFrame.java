
class PageFrame{
        private int pageId;
        private int age;
    
        public PageFrame(int pageId) {
            this.pageId = pageId;
            this.age = 0;
        }
    
        public int getPageId() {
            return pageId;
        }
    
        public void setPageId(int pageId) {
            this.pageId = pageId;
        }
    
        public int getAge() {
            return age;
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        public void incrementAge() {
            this.age++;
        }
}
