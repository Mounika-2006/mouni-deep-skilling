public class ThreadCreation {
    public static void main(String[] args) {
        Thread printer1 = new MessagePrinter("Thread 1 message", 5);
        Thread printer2 = new MessagePrinter("Thread 2 message", 5);

        printer1.start();
        printer2.start();
    }
}

class MessagePrinter extends Thread {
    private final String message;
    private final int count;

    MessagePrinter(String message, int count) {
        this.message = message;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(message + " - iteration " + (i + 1));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
