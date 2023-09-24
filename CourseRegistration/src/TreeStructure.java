import java.util.*;

class TreeNode {
    int studentId;
    Set<Integer> courseIds; // Use a set to store course IDs
    TreeNode left;
    TreeNode right;
    int height;

    public TreeNode(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseIds = new HashSet<>();
        this.courseIds.add(courseId);
        this.height = 1;
    }

    // Helper method to add a course ID to the set
    public void addCourse(int courseId) {
        courseIds.add(courseId);
    }
}

class AVLTree {
    static TreeNode root;

    int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    void updateHeight(TreeNode node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    TreeNode balanceNode(TreeNode node) {
        updateHeight(node);

        int balance = getHeight(node.left) - getHeight(node.right);

        if (balance > 1) {
            if (getHeight(node.left.left) >= getHeight(node.left.right)) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balance < -1) {
            if (getHeight(node.right.right) >= getHeight(node.right.left)) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    void insert(TreeNode newNode) {
        root = insert(root, newNode);
    }

    private TreeNode insert(TreeNode node, TreeNode newNode) {
        if (node == null) {
            return newNode;
        }

        if (newNode.studentId < node.studentId) {
            node.left = insert(node.left, newNode);
        } else if (newNode.studentId > node.studentId) {
            node.right = insert(node.right, newNode);
        } else {
            // Student ID already exists, add the course to the set
            node.addCourse(newNode.courseIds.iterator().next()); // Assuming one course per student ID
            return node;
        }

        return balanceNode(node);
    }
    //

    // Function to perform an inorder traversal of the AVL Tree
    void inorderTraversal(TreeNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print("Student ID: " + node.studentId + ", Courses: " + node.courseIds);
            System.out.println();
            inorderTraversal(node.right);
        }
    }


    void inorderTraversal(TreeNode node, int targetStudentId) {
        System.out.println("Inside the inorderTraversal");
        if (node != null) {
            inorderTraversal(node.left, targetStudentId);
            if (node.studentId == targetStudentId) {
                System.out.println("Student ID: " + node.studentId + ", Courses: " + node.courseIds);
            }
            inorderTraversal(node.right, targetStudentId);
        }
    }

    public static void retrieveAndPrintCourses(Scanner scanner) {
        System.out.print("Enter Student ID to retrieve courses (or -1 to exit): ");
        int targetStudentId = scanner.nextInt();

        if (targetStudentId == -1) {
            return; // Exit the method if -1 is entered
        }

        System.out.println("Course enrollments for Student ID " + targetStudentId + ":");

        // Call a helper method to perform the retrieval and printing
        retrieveAndPrintCoursesRecursive(root, targetStudentId);
    }

    // Helper method to perform retrieval and printing recursively
    public static void retrieveAndPrintCoursesRecursive(TreeNode node, int targetStudentId) {
        if (node != null) {
            retrieveAndPrintCoursesRecursive(node.left, targetStudentId);

            if (node.studentId == targetStudentId) {
                System.out.println("Courses: " + node.courseIds);
            }

            retrieveAndPrintCoursesRecursive(node.right, targetStudentId);
        }
    }

    public static void inOrderTraversal(TreeNode node, LinkedList<Integer> linkedList) {
        if (node == null) {
            return;
        }

        // Recursively traverse the left subtree
        inOrderTraversal(node.left, linkedList);

        // Insert the current node's data into the linked list
        linkedList.add(node.studentId);

        // Recursively traverse the right subtree
        inOrderTraversal(node.right, linkedList);
    }

    public static Set returnCourseId(TreeNode node){

        return node.courseIds;

    }

}

