import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private static final String FILE_NAME = "students.csv";
    private List<Student> students = new ArrayList<>();

    public StudentManager() {
        loadStudentsFromFile();
    }

    // CRUD Operations
    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public void updateStudent(int id, String name, int age, String grade) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(name);
                student.setAge(age);
                student.setGrade(grade);
                break;
            }
        }
        saveStudentsToFile();
    }

    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
        saveStudentsToFile();
    }

    // File Handling
    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student(
                    Integer.parseInt(data[0]),
                    data[1],
                    Integer.parseInt(data[2]),
                    data[3]
                );
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("No existing data found. Starting fresh.");
        }
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.getId() + "," 
                           + student.getName() + "," 
                           + student.getAge() + "," 
                           + student.getGrade() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public int getNextId() {
        return students.isEmpty() ? 1 : students.get(students.size() - 1).getId() + 1;
    }
}