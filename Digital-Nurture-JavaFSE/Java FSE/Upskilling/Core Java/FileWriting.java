import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriting {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
                FileWriter writer = new FileWriter("output.txt")) {
            System.out.print("Enter a string: ");
            String input = scanner.nextLine();
            writer.write(input);
            System.out.println("Data has been written to output.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
