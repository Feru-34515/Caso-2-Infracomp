class MatrixAdditionProcess {
    int[][] mat1, mat2, mat3;
    Config config;

    public MatrixAdditionProcess(Config config) {
        this.config = config;
        mat1 = new int[config.numRows][config.numCols];
        mat2 = new int[config.numRows][config.numCols];
        mat3 = new int[config.numRows][config.numCols];
    }

    public void tour1() {
        for (int i = 0; i < config.numRows; i++) {
            for (int j = 0; j < config.numCols; j++) {
                mat3[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
    }

    public int[][] generatePageReferences() {
        int[][] pageRefs = new int[config.numRows][config.numCols];

        int pageSizeInIntegers = config.pageSize / config.integerSize;

        for (int i = 0; i < config.numRows; i++) {
            for (int j = 0; j < config.numCols; j++) {
                int pageIndex = (i * config.numCols + j) / pageSizeInIntegers;
                pageRefs[i][j] = pageIndex;
            }
        }
        return pageRefs;
    }
}
