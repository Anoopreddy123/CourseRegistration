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

    courseName.put(311,"Data Structure , 9");
    courseName.put(411,"Design and Analysis of algorithm, 9");
    courseName.put(583,"Software engineering Process ,13");
    courseName.put(531,"Adv Computer Architecture ,15");
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
        System.out.println("Enter to option to continue");
        System.out.println("1. Add a course");
        System.out.println("2. Drop a course");
        int option = scanner.nextInt();

        if(option == 1){
        if (studentId == -1) {
            return; // Exit the loop
        }
        while (true) {

            System.out.println("Enter Course  for the Student ID: " + studentId);
            displayCourses();
            courseId = scanner.nextInt();
            if(courseId == -1) break;
            String studentID = roaster.get(courseId);
            roaster.put(courseId, studentID + " " + String.valueOf(studentId));
            int b = canEnroll(rootNode, courseId);
            if (b == 0) {
                if (rootNode == null && courseId != -1) {
                    // If the root node is null, create a new one for this student ID
                    rootNode = new TreeNode(studentId, courseId);
                } else if (studentId == rootNode.studentId && courseId != -1) {
                    // If the student ID matches the current root node, add the course ID to the set
                    rootNode.addCourse(courseId);
                } else if (courseId != -1) {
                    // If a different student ID is entered, insert the previous root node into the AVL tree
                    avlTree.insert(rootNode);
                    // Create a new root node for the new student ID
                    rootNode = new TreeNode(studentId, courseId);
                } else {
                    break;
                }
            }else{
                System.out.println("=====================================");
                System.out.println("The class opted is overlapping with the course :" + b);
                System.out.println("=====================================");
            }
        }

        // Insert the final root node into the AVL tree
        if (rootNode != null) {
            avlTree.insert(rootNode);
        }

        // Display the contents of the AVL tree after course enrollments
        System.out.println("Course enrollments for Student :" + studentId);
        avlTree.inorderTraversal(AVLTree.root);

    }else{
            System.out.println("Enter Course ID to drop for the Student ID: " + studentId);
            courseId = scanner.nextInt();
            AVLTree.delete(AVLTree.root,courseId);
        }
}



    private int canEnroll(TreeNode node, int courseId) {
        System.out.println("Inside the can enroll");
        String[] secondCourse = courseName.get(courseId).split(",");
        int  secondTiming= Integer.parseInt(secondCourse[1].trim());
        int i = 0;
        if (node != null) {
            Set sets = AVLTree.returnCourseId(node);
            System.out.println(sets.size());
            int[] arr = new int[sets.size()];
            System.out.println("Second course ID : " + courseId);
            for(Object courseIds : sets) {
                arr[i] = (int) courseIds;
                i++;
            }
            for (int j = 0; j < arr.length ; j++) {
                String[] firstCourse = courseName.get(arr[j]).split(",");
                System.out.println("Inside the for loop for the comparison" + arr[j]);
                int  firstTiming= Integer.parseInt(firstCourse[1].trim());

                if(firstTiming == secondTiming){
                    return arr[j];
                }
            }

            }
        return 0;
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
