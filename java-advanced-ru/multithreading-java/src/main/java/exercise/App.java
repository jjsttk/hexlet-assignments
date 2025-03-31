package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array) {
        var resultMap = new HashMap<String, Integer>();
        var maxThread = new MaxThread(array);
        var minThread = new MinThread(array);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        resultMap.put("max", maxThread.getValue());
        resultMap.put("min", minThread.getValue());

        return resultMap;
    }
    // END
}
