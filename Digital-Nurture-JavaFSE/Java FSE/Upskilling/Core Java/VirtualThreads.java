public class VirtualThreads {
    private static final int TASK_COUNT = 100_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Launching " + TASK_COUNT + " virtual threads...");
        long startVirtual = System.nanoTime();
        Thread[] virtualThreads = new Thread[TASK_COUNT];
        for (int i = 0; i < TASK_COUNT; i++) {
            int index = i;
            virtualThreads[i] = Thread
                    .startVirtualThread(() -> System.out.println("Virtual thread " + index + " running"));
        }
        for (Thread thread : virtualThreads) {
            thread.join();
        }
        long virtualDurationMs = (System.nanoTime() - startVirtual) / 1_000_000;
        System.out.println("Virtual threads completed in " + virtualDurationMs + " ms\n");

        System.out.println("Launching " + TASK_COUNT + " platform threads...");
        long startPlatform = System.nanoTime();
        Thread[] platformThreads = new Thread[TASK_COUNT];
        for (int i = 0; i < TASK_COUNT; i++) {
            int index = i;
            platformThreads[i] = new Thread(() -> System.out.println("Platform thread " + index + " running"));
            platformThreads[i].start();
        }
        for (Thread thread : platformThreads) {
            thread.join();
        }
        long platformDurationMs = (System.nanoTime() - startPlatform) / 1_000_000;
        System.out.println("Platform threads completed in " + platformDurationMs + " ms");
    }
}
