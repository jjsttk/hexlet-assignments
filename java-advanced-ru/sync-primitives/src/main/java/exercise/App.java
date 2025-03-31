package exercise;

public class App {

    public static void main(String[] args) {
        // BEGIN
        var ourList = new SafetyList();

        var firstThread = new ListThread(ourList);
        var secondThread = new ListThread(ourList);

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // END
    }
}

