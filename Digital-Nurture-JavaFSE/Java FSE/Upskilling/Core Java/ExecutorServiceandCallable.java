import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceandCallable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> "Task 1 result");
        tasks.add(() -> {
            Thread.sleep(500);
            return "Task 2 result";
        });
        tasks.add(() -> "Task 3 result");

        try {
            List<Future<String>> futures = new ArrayList<>();
            for (Callable<String> task : tasks) {
                futures.add(executor.submit(task));
            }

            for (Future<String> future : futures) {
                try {
                    String result = future.get();
                    System.out.println(result);
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println("Error getting task result: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        } finally {
            executor.shutdown();
        }
    }
}
