import java.util.*;

class ArraySumandAverage {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the size of the array");
        int n = sc.nextInt();
        int arr[] = new int[n];
        int sum = 0;
        System.out.println("enter the elements of the array");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        double average = (double) sum / n;
        System.out.println("the sum of the array is:" + sum);
        System.out.println("the average of the array is: " + average);
        sc.close();

    }

}
