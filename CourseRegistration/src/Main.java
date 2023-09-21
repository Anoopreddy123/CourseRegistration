import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static Scanner scanner  = new Scanner(System.in);

// To display the options for the user
    private static void options() {
        System.out.println("1. Enter the student Information to sign him up");
        System.out.println("2. Select To add or drop the courses");
        System.out.println("3. Enter the course details.");
        System.out.println("4. Enter the courseId to get the roaster.");
        System.out.println("5. Enter the studentId to get the class schedule.");
        System.out.println("6. Exit");
    }


    public static void main(String[] args) {

      String input = null;
        while (input != "exit") {
            System.out.println("Please select a option from the given list to continue further.");
            options();
            System.out.print("Enter the option here :");
             input = scanner.nextLine();
            Student student = new Student();
            Course course = new Course();

                if(Objects.equals(input, "1"))
                    student.studentInfo();

                else if(Objects.equals(input, "2"))
                    course.enrollCourses();

                else if(Objects.equals(input, "3"))
                    course.displayCourseDetails();

                else if(Objects.equals(input, "4"))
                    course.displayCourseRoaster();

                else if(Objects.equals(input, "5"))
                    course.displayStudentSchedule();
                else
                    return;

        }
    }
}


