import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> students = new HashMap<>();

        System.out.print("How many student entries do you want to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            students.put(id, name);
        }

        System.out.print("Enter an ID to retrieve a student name: ");
        int lookupId = scanner.nextInt();
        String result = students.get(lookupId);

        if (result != null) {
            System.out.println("Student name for ID " + lookupId + ": " + result);
        } else {
            System.out.println("No student found with ID " + lookupId + ".");
        }

        scanner.close();
    }
}
