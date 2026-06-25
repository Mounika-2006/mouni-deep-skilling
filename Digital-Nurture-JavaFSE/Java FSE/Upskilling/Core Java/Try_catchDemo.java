import java.util.*;

class Try_catchDemo {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the first number");
        int a = sc.nextInt();
        System.out.println("enter the b value");
        int b = sc.nextInt();
        try {
            int c = a / b;
            System.out.println("Result: " + c);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero. Please provide a non-zero divisor.");
        } finally {
            System.out.println();
            System.out.println("Operation completed.");
            sc.close();
        }
    }

}
