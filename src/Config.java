import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

class Config {
    int numRows, numCols, integerSize, pageSize, numPageFrames, NF, NC, TE, TP, PM;
    private HashMap<String, Integer> configMap;

    public Config() {
        this.configMap = new HashMap<>();
    }

    public Config(String filePath) {
        this();
        loadConfiguration(filePath);
    }

    public void loadConfiguration(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());
                    configMap.put(key, value);
                }
            }

            // Print the parsed values for debugging
            System.out.println("Parsed configuration values:");
            for (Map.Entry<String, Integer> entry : configMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("Error: Unable to read the configuration file.");
            e.printStackTrace();
        }
    }
}
