import java.util.Scanner;

public class ArrayList {
    public static void main(String[] args) {
        java.util.ArrayList<String> names = new java.util.ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student names. Type 'done' to finish:");
        while (true) {
            System.out.print("Name: ");
            String input = scanner.nextLine();
            if (input == null || input.trim().equalsIgnoreCase("done")) {
                break;
            }
            if (!input.trim().isEmpty()) {
                names.add(input.trim());
            }
        }

        scanner.close();

        System.out.println("\nStudent names entered:");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
