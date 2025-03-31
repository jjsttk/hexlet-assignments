package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    private final int[] array;
    private int value;

    public MinThread(int[] array) {
        this.array = array;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        value = getMinValue();

    }

    public int getMinValue() {
        int minValue = array[0];
        for (var num : array) {
            minValue = Math.min(minValue, num);
        }
        return minValue;
    }
}
// END
