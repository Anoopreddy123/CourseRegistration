import java.util.HashMap;
import java.util.Scanner;

public class Student {
 private static Scanner scanner = new Scanner(System.in);
 HashMap<Integer, String> studentData = new HashMap<>();
    public void studentInfo() {
        int option = 0;
        while(option != -1) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("1. Add a student to the list.");
            System.out.println("2. Get the details of the student.");
            System.out.print("Enter an option from the above :");
            option = scanner.nextInt();

            if (option == 1) {
                System.out.print("Please enter the student ID :");
                int studentId = scanner.nextInt();
                System.out.print("Please enter the Student Name :");
                String studentName = scanner.next();
                studentData.put(studentId, studentName);
            } else if (option == 2) {
                System.out.println("Enter the Student ID to retrieve the data of the student");
                int studentId = scanner.nextInt();
                System.out.println(studentData.get(studentId));
            }

        }
    }

    public void displayStudentSchedule(){


    }
}
