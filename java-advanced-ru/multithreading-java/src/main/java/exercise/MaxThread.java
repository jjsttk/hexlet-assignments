package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private int[] array;
    private int value;

    public MaxThread(int[] array) {
        this.array = array;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        value = getMaxValue();
    }


    public int getMaxValue() {
        int maxValue = array[0];
        for (var num : array) {
            maxValue = Math.max(num, maxValue);
        }
        return maxValue;
    }
}
// END
