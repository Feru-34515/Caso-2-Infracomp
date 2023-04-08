public class TestMain {
    public static void main(String[] args) {
        // Hardcoded configuration file path
        String configFile = "C:\\Users\\LOLNI\\OneDrive\\Documents\\Andes\\Semestre 6\\InfraComp\\Caso 2\\Caso-2-Infracomp\\src\\config1.txt";
        
        // Hardcoded mode number
        int mode = 1;

        Config config = new Config();
        config.loadConfiguration(configFile);

        MatrixAdditionProcess process = new MatrixAdditionProcess(config);
        int[][] pageRefs = process.generatePageReferences();
        MemoryManager memoryManager = new MemoryManager(config, pageRefs);

        // Run the simulation in the desired mode
        memoryManager.simulateProcessBehavior(process);
        memoryManager.simulatePagingSystem();
    }
}
