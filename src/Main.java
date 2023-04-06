public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Error: Please provide a configuration file and mode number.");
            return;
        }

        String configFile = args[0];
        int mode = Integer.parseInt(args[1]);

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
