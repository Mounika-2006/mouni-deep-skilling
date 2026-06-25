import java.util.*;

class simpleCalculator {
    public static void main(String args[]) {
        System.out.println("Enter a,b values");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Enter the operator");
        switch (sc.next()) {
            case "+":
                System.out.println(a + b);
                break;
            case "-":
                System.out.println(a - b);
                break;
            case "*":
                System.out.println(a * b);
                break;
            case "/":
                System.out.println(a / b);
                break;
            default:
                System.out.println("Invalid operator");

        }
    }

}
