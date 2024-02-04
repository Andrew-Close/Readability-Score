import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double root1 = (b * -1 - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        double root2 = (b * -1 + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        if (root1 < root2) {
            System.out.println(root1);
            System.out.println(root2);
        } else {
            System.out.println(root2);
            System.out.println(root1);
        }
    }
}