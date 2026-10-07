import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    // Student information
    static class Student {
        String name;
        double score;

        Student(String name, double score) {
            this.name = name;
            this.score = score;
        }

        // Calculate grade letter
        String getGrade() {
            if (score >= 90) {
                return "A";
            } else if (score >= 80) {
                return "B";
            } else if (score >= 70) {
                return "C";
            } else if (score >= 60) {
                return "D";
            } else {
                return "F";
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=================================");
        System.out.println("     STUDENT GRADE TRACKER");
        System.out.println("=================================");

        System.out.print("Enter number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine();

        // Input student information
        for (int i = 0; i < numberOfStudents; i++) {

            System.out.println();
            System.out.println("Student " + (i + 1));

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter score: ");
            double score = scanner.nextDouble();
            scanner.nextLine();

            students.add(new Student(name, score));
        }

        // Calculate total
        double total = 0;

        for (Student student : students) {
            total += student.score;
        }

        double average = total / students.size();

        // Find highest and lowest
        Student highest = students.get(0);
        Student lowest = students.get(0);

        for (Student student : students) {

            if (student.score > highest.score) {
                highest = student;
            }

            if (student.score < lowest.score) {
                lowest = student;
            }
        }

        // Display student report
        System.out.println();
        System.out.println("=================================");
        System.out.println("         STUDENT REPORT");
        System.out.println("=================================");

        for (Student student : students) {
            System.out.println(
                "Name: " + student.name +
                " | Score: " + student.score +
                " | Grade: " + student.getGrade()
            );
        }

        // Display summary
        System.out.println();
        System.out.println("=================================");
        System.out.println("         SUMMARY REPORT");
        System.out.println("=================================");

        System.out.println("Total Students : " + numberOfStudents);
        System.out.println("Average Score  : " + average);
        System.out.println("Highest Score  : " + highest.score);
        System.out.println("Highest Student: " + highest.name);
        System.out.println("Lowest Score   : " + lowest.score);
        System.out.println("Lowest Student : " + lowest.name);

        System.out.println("=================================");

        scanner.close();
    }
}