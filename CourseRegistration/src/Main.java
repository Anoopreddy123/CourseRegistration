import java.util.Scanner;

public class Main {
    public static Scanner scanner  = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Please select a option from the given list to continue further.");
        options();
        System.out.print("Enter the option here :");
        int input = scanner.nextInt();
        switch (input){
            case 1:
                System.out.println("Monday");
                return;
            case 2:
                System.out.println("Monday2");
                return;
            case 3:
                System.out.println("Monday");
                return;
            case 4:
                System.out.println("Monday");
                return;
            case 5:
                System.out.println("Monday");
                return;
            default:
                System.out.println("didnt find any input");
        }
    }

    private static void options() {
        System.out.println("1. Enter the student Information to sign him up");
        System.out.println("2. Select To add or drop the courses");
        System.out.println("3. Enter the course details.");
        System.out.println("4. Enter the courseId to get the roaster.");
        System.out.println("5. Enter the studentId to get the class schedule.");
        System.out.println("6. Exit");
    }
}