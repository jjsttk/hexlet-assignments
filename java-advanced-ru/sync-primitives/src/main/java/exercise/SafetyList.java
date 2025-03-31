package exercise;

public class SafetyList {
    // BEGIN
    private final int[] array;
    private Integer indexOfLastAddedNum;


    public SafetyList() {
        array = new int[2000];

    }

    public int[] getArray() {
        return array;
    }

    public synchronized void add(int num) {
        if (indexOfLastAddedNum == null) {
            array[0] = num;
            indexOfLastAddedNum = 0;
            return;
        }
        array[indexOfLastAddedNum] = num;
        indexOfLastAddedNum++;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return array.length;
    }

    // END
}
