import java.util.Scanner;

public class StudentGradeManagementSystem {

    static final int MAX_STUDENTS = 50;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] studentNames = new String[MAX_STUDENTS];
        int[] studentGrades = new int[MAX_STUDENTS];
        int studentCount = 0;

        int choice;

        System.out.println("Welcome to Student Grade Management System");

        do {
            displayMenu();

            System.out.print("Enter your choice: ");

            while (!input.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number from 1 to 6.");
                input.next();
                System.out.print("Enter your choice: ");
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    studentCount = addStudent(input, studentNames, studentGrades, studentCount);
                    break;

                case 2:
                    displayStudents(studentNames, studentGrades, studentCount);
                    break;

                case 3:
                    findStudentGrade(input, studentNames, studentGrades, studentCount);
                    break;

                case 4:
                    calculateAverage(studentGrades, studentCount);
                    break;

                case 5:
                    findHighestAndLowest(studentNames, studentGrades, studentCount);
                    break;

                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please choose a number from 1 to 6.");
            }

        } while (choice != 6);

        input.close();
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("1. Add Student and Grade");
        System.out.println("2. Display All Students and Grades");
        System.out.println("3. Find a Student's Grade");
        System.out.println("4. Calculate Class Average");
        System.out.println("5. Find Highest and Lowest Grades");
        System.out.println("6. Exit");
    }

    public static int addStudent(Scanner input, String[] names, int[] grades, int count) {
        if (count >= MAX_STUDENTS) {
            System.out.println("Student list is full. Cannot add more students.");
            return count;
        }

        System.out.print("Enter student name: ");
        String name = input.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Invalid name! Student name cannot be empty.");
            return count;
        }

        System.out.print("Enter student grade: ");

        while (!input.hasNextInt()) {
            System.out.println("Invalid input! Grade must be a number between 0 and 100.");
            input.next();
            System.out.print("Enter student grade: ");
        }

        int grade = input.nextInt();
        input.nextLine();

        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade! Please enter a value between 0 and 100.");
            return count;
        }

        names[count] = name;
        grades[count] = grade;
        count++;

        System.out.println("Student added successfully.");

        return count;
    }

    public static void displayStudents(String[] names, int[] grades, int count) {
        if (count == 0) {
            System.out.println("No students have been added yet.");
            return;
        }

        System.out.println("List of Students and Grades:");

        for (int i = 0; i < count; i++) {
            System.out.println(names[i] + " - " + grades[i]);
        }
    }

    public static void findStudentGrade(Scanner input, String[] names, int[] grades, int count) {
        if (count == 0) {
            System.out.println("No students have been added yet.");
            return;
        }

        System.out.print("Enter student name: ");
        String searchName = input.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (names[i].equalsIgnoreCase(searchName)) {
                System.out.println(names[i] + "'s Grade: " + grades[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void calculateAverage(int[] grades, int count) {
        if (count == 0) {
            System.out.println("No grades available to calculate average.");
            return;
        }

        int total = 0;

        for (int i = 0; i < count; i++) {
            total += grades[i];
        }

        double average = (double) total / count;

        System.out.println("Class Average: " + average);
    }

    public static void findHighestAndLowest(String[] names, int[] grades, int count) {
        if (count == 0) {
            System.out.println("No students have been added yet.");
            return;
        }

        int highestIndex = 0;
        int lowestIndex = 0;

        for (int i = 1; i < count; i++) {
            if (grades[i] > grades[highestIndex]) {
                highestIndex = i;
            }

            if (grades[i] < grades[lowestIndex]) {
                lowestIndex = i;
            }
        }

        System.out.println("Highest Grade: " + names[highestIndex] + " - " + grades[highestIndex]);
        System.out.println("Lowest Grade: " + names[lowestIndex] + " - " + grades[lowestIndex]);
    }
}