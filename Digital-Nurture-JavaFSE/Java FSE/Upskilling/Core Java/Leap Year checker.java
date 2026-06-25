import java.util.*;

class LeapYearChecker {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the year");
        int year = sc.nextInt();
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println("leap year");
        } else {
            System.out.println("not a leap year");
        }
    }

}
