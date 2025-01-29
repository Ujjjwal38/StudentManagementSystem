import java.util.Scanner;

public class StudentManagementApp {
  private static Scanner scanner = new Scanner(System.in);
  private static StudentManager manager = new StudentManager();

  public static void main(String[] args) {
    while (true) {
      printMenu();
      int choice = getIntInput("Enter your choice: ");

      switch (choice) {
        case 1:
          addStudent();
          break;
        case 2:
          viewStudents();
          break;
        case 3:
          updateStudent();
          break;
        case 4:
          deleteStudent();
          break;
        case 5:
          System.out.println("Exiting...");
          System.exit(0);
        default:
          System.out.println("Invalid choice!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("\nStudent Management System");
    System.out.println("1. Add Student");
    System.out.println("2. View Students");
    System.out.println("3. Update Student");
    System.out.println("4. Delete Student");
    System.out.println("5. Exit");
  }

  private static void addStudent() {
    int id = manager.getNextId();
    String name = getStringInput("Enter student name: ");
    int age = getIntInput("Enter student age: ");
    String grade = getStringInput("Enter student grade: ");

    manager.addStudent(new Student(id, name, age, grade));
    System.out.println("Student added successfully!");
  }

  private static void viewStudents() {
    List<Student> students = manager.getAllStudents();
    if (students.isEmpty()) {
      System.out.println("No students found!");
      return;
    }
    System.out.println("\nStudent List:");
    for (Student student : students) {
      System.out.println(student);
    }
  }

  private static void updateStudent() {
    int id = getIntInput("Enter student ID to update: ");
    String name = getStringInput("Enter new name: ");
    int age = getIntInput("Enter new age: ");
    String grade = getStringInput("Enter new grade: ");

    manager.updateStudent(id, name, age, grade);
    System.out.println("Student updated successfully!");
  }

  private static void deleteStudent() {
    int id = getIntInput("Enter student ID to delete: ");
    manager.deleteStudent(id);
    System.out.println("Student deleted successfully!");
  }

  private static String getStringInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  private static int getIntInput(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input! Please enter a number.");
      }
    }
  }
}