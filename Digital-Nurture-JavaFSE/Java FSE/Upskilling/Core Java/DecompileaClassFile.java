public class DecompileaClassFile {
    public static void main(String[] args) {
        int a = 7;
        int b = 5;
        int sum = add(a, b);
        System.out.println("Sum: " + sum);
    }

    private static int add(int x, int y) {
        return x + y;
    }
}
