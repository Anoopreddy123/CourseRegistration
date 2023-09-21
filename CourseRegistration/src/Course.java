import java.util.Scanner;

public class Course {
/**
 * TODO Need to implement a seperate tree for the course so that we can get the roaster in complete tree at once.
 * 1. Where to implement the course overlap...? where to define the data for each course and logic to check that courses are
 * not overlapping each other.
 * 2. Also implement the logic for dropping the course for a student and the vice cersa we need to drop the student id from
 * the course AVL tree.
 *
 */
    public static Scanner scanner  = new Scanner(System.in);
  AVLTree avlTree = new AVLTree();

    private void displayCourses() {
        System.out.println("311 - Data Structure");
        System.out.println("411 - Design and Analysis of algorithm");
        System.out.println("583 - Software engineering Process");
        System.out.println("531 - Adv Computer Architecture");

    }
    public void enrollCourses() {

        Scanner scanner = new Scanner(System.in);

        int courseId = 0;
        TreeNode rootNode = null; // Initialize root node as null

        /* TODO
          make the loop run for a single student the whole time.
          and also make sure to add more courses to the list.
          and also list out the courses and their names in complete not just the course ID.

         */
        while (courseId != -1) {
            System.out.print("Enter Student ID to continue (or -1 to exit): ");
            int studentId = scanner.nextInt();

            if (studentId == -1) {
                break; // Exit the loop
            }

            System.out.println("Enter Course ID: ");
            displayCourses();
            courseId = scanner.nextInt();

            if (rootNode == null) {
                // If the root node is null, create a new one for this student ID
                rootNode = new TreeNode(studentId, courseId);
            } else if (studentId == rootNode.studentId) {
                // If the student ID matches the current root node, add the course ID to the set
                rootNode.addCourse(courseId);
            } else {
                // If a different student ID is entered, insert the previous root node into the AVL tree
                avlTree.insert(rootNode);
                // Create a new root node for the new student ID
                rootNode = new TreeNode(studentId, courseId);
            }
        }

        // Insert the final root node into the AVL tree
        if (rootNode != null) {
            avlTree.insert(rootNode);
        }

        // Display the contents of the AVL tree after course enrollments
        System.out.println("Course enrollments:");
        avlTree.inorderTraversal(AVLTree.root);

    }


    public void displayCourseDetails() {


    }

    public void displayCourseRoaster() {
    }

    public void displayStudentSchedule() {
        AVLTree.retrieveAndPrintCourses(scanner);
    }



}
