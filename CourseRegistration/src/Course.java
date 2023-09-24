import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Course {
/**
 * TODO Need to implement a seperate tree for the course so that we can get the roaster in complete tree at once.
 * 1. Where to implement the course overlap...? where to define the data for each course and logic to check that courses are
 * not overlapping each other.
 * 2. Also implement the logic for dropping the course for a student and the vice versa we need to drop the student id from
 * the course AVL tree.
 *
 */
    public static Scanner scanner  = new Scanner(System.in);
  AVLTree avlTree = new AVLTree();
    HashMap<Integer, String> courseName = new HashMap<>();

Course(){

    courseName.put(311,"Data Structure , 9-12");
    courseName.put(411,"Design and Analysis of algorithm, 8-10");
    courseName.put(583,"Software engineering Process ,13-15");
    courseName.put(531,"Adv Computer Architecture ,15-20");
}

    private void displayCourses() {
        for (HashMap.Entry<Integer, String> entry : courseName.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("Enter -1 to exit.");
    }
    public void enrollCourses(HashMap<Integer, String> roaster) {
        System.out.println("----------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);

        int courseId = 0;
        TreeNode rootNode = null; // Initialize root node as null

        /* TODO
          make the loop run for a single student the whole time. -- Done
          and also make sure to add more courses to the list.
          and also list out the courses and their names in complete not just the course ID. -- Done

         */

        System.out.print("Enter Student ID to continue (or -1 to exit): ");
        int studentId = scanner.nextInt();

        if (studentId == -1) {
            return; // Exit the loop
        }
        while (courseId != -1) {

            System.out.println("Enter Course  for the Student ID: " + studentId);
            displayCourses();
            courseId = scanner.nextInt();
            String studentID = roaster.get(courseId);
            roaster.put(courseId,studentID + " "+ String.valueOf(studentId));
            canEnroll(rootNode);

            if (rootNode == null && courseId != -1) {
                // If the root node is null, create a new one for this student ID
                rootNode = new TreeNode(studentId, courseId);
            } else if (studentId == rootNode.studentId && courseId != -1) {
                // If the student ID matches the current root node, add the course ID to the set
                    rootNode.addCourse(courseId);
            } else if(courseId != -1){
                // If a different student ID is entered, insert the previous root node into the AVL tree
                avlTree.insert(rootNode);
                // Create a new root node for the new student ID
                rootNode = new TreeNode(studentId, courseId);
            }else{
                break;
            }
        }

        // Insert the final root node into the AVL tree
        if (rootNode != null) {
            avlTree.insert(rootNode);
        }

        // Display the contents of the AVL tree after course enrollments
        System.out.println("Course enrollments for Student :" + studentId);
        avlTree.inorderTraversal(AVLTree.root);

    }



    private void canEnroll(TreeNode node) {

        System.out.println("Inside the can enroll");
        if (node != null) {
            Set sets = AVLTree.returnCourseId(node);
            System.out.println(sets.size());
        }
    }


    public void displayCourseDetails() {

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Enter the course ID that needs to be added.");
        int courseId = scanner.nextInt();;
        System.out.println("Enter the course name.");
        String courseNames = scanner.next();
        courseName.put(courseId,courseNames);
        System.out.println(courseName.size());

    }

    public void displayCourseRoaster(HashMap<Integer, String> roaster) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Enter the course ID");
        int courseId = scanner.nextInt();
        System.out.println("Course Name :" + " : "+ courseName.get(courseId));
        if (roaster.get(courseId).isEmpty()) {
            System.out.println("No students enrolled");
        } else {
            System.out.println("Students Enrolled" + " : " + roaster.get(courseId));
        }

    }

    public void displayStudentSchedule() {
        System.out.println("----------------------------------------------------------------------------");
        AVLTree.retrieveAndPrintCourses(scanner);
    }



}
