

import java.util.*;

/* ---------- Custom Exceptions ---------- */
class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException(String msg) {
        super(msg);
    }
}

class InvalidPercentageException extends Exception {
    public InvalidPercentageException(String msg) {
        super(msg);
    }
}

class EmptyFieldException extends Exception {
    public EmptyFieldException(String msg) {
        super(msg);
    }
}

/* ---------- Student Class ---------- */
class Student {
    int eno;
    String name;
    String branch;
    String sem;
    double percentage;

    Student(int eno, String name, String branch, String sem, double percentage) {
        this.eno = eno;
        this.name = name;
        this.branch = branch;
        this.sem = sem;
        this.percentage = percentage;
    }

    public String toString() {
        return eno + "\t" + name + "\t" + branch + "\t" + sem + "\t" + percentage;
    }
}

/* ---------- Main Class ---------- */
public class Minipr {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    /* ---------- Login System ---------- */
    static void login() {
        String username = "admin";
        String password = "1234";

        System.out.print("Enter Username: ");
        String u = sc.next();

        System.out.print("Enter Password: ");
        String p = sc.next();

        if (u.equals(username) && p.equals(password)) {
            System.out.println("Login Successful!\n");
        } else {
            System.out.println("Invalid Credentials! Program Exit.");
            System.exit(0);
        }
    }

    /* ---------- Add Student ---------- */
    static void addStudent() throws Exception {

        System.out.print("Enter Enrollment No: ");
        int eno = sc.nextInt();

        for (Student s : students) {
            if (s.eno == eno) {
                throw new DuplicateEnrollmentException("Enrollment Number already exists!");
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Branch: ");
        String branch = sc.next();
        if (branch.isEmpty()) {
            throw new EmptyFieldException("Branch cannot be empty!");
        }

        System.out.print("Enter Semester: ");
        String sem = sc.next();
        if (sem.isEmpty()) {
            throw new EmptyFieldException("Semester cannot be empty!");
        }

        System.out.print("Enter Percentage: ");
        double per = sc.nextDouble();
        if (per <= 0) {
            throw new InvalidPercentageException("Percentage must be positive!");
        }

        students.add(new Student(eno, name, branch, sem, per));
        System.out.println("Student Added Successfully!");
    }

    /* ---------- Display Students ---------- */
    static void displayStudents() {
        System.out.println("Eno\tName\tBranch\tSem\tPercentage");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /* ---------- Search Student ---------- */
    static void searchStudent() {
        System.out.print("Enter Enrollment No: ");
        int eno = sc.nextInt();

        for (Student s : students) {
            if (s.eno == eno) {
                System.out.println("Student Found:");
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    /* ---------- Update Branch ---------- */
    static void updateBranch() {
        System.out.print("Enter Enrollment No: ");
        int eno = sc.nextInt();

        for (Student s : students) {
            if (s.eno == eno) {
                System.out.print("Enter New Branch: ");
                s.branch = sc.next();
                System.out.println("Branch Updated Successfully!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    /* ---------- Delete Student ---------- */
    static void deleteStudent() {
        System.out.print("Enter Enrollment No: ");
        int eno = sc.nextInt();

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().eno == eno) {
                it.remove();
                System.out.println("Student Deleted Successfully!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    /* ---------- Sort Students ---------- */
    static void sortStudents() {
        students.sort(Comparator.comparingInt(s -> s.eno));
        System.out.println("Students Sorted by Enrollment No!");
    }

    /* ---------- Main Method ---------- */
    public static void main(String[] args) {

        login();

        int choice;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by Eno");
            System.out.println("4. Update Student Branch");
            System.out.println("5. Delete Student by Eno");
            System.out.println("6. Display Sorted Students");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: displayStudents(); break;
                    case 3: searchStudent(); break;
                    case 4: updateBranch(); break;
                    case 5: deleteStudent(); break;
                    case 6: sortStudents(); displayStudents(); break;
                    case 7: System.out.println("Thank You!"); break;
                    default: System.out.println("Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 7);
    }
}
