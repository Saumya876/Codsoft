import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Studentdetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Sname;
    private int rollnumber;
    private String grade;

    public Studentdetail(String Sname, int rollnumber, String grade) {
        this.Sname = Sname;
        this.rollnumber = rollnumber;
        this.grade = grade;
    }

    public String getNamee() {
        return Sname;
    }

    public int getRollno() {
        return rollnumber;
    }

    public String getGradee() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + Sname + ", Roll no.: " + rollnumber + ", Grade: " + grade;
    }
}

class Management {
    private List<Studentdetail> students = new ArrayList<>();

    public void addstud(Studentdetail student) {
        students.add(student);
    }

    public void removestud(int rollnumber) {
        students.removeIf(student -> student.getRollno() == rollnumber);
    }

    public Studentdetail findStudent(int rollnumber) {
        for (Studentdetail student : students) {
            if (student.getRollno() == rollnumber) {
                return student;
            }
        }
        return null;
    }

    public List<Studentdetail> getAllStudents() {
        return students;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                if (list.isEmpty() || list.get(0) instanceof Studentdetail) {
                    students = (List<Studentdetail>) list;
                } else {
                    throw new IOException("Data type mismatch");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class task3 {
    public static void main(String[] args) {
        Management sms = new Management();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String Sname = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    Studentdetail student = new Studentdetail(Sname, rollNumber, grade);
                    sms.addstud(student);
                    break;
                case 2:
                    System.out.print("Enter roll number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    scanner.nextLine();
                    sms.removestud(rollToRemove);
                    break;
                case 3:
                    System.out.print("Enter roll number to search: ");
                    int rollToSearch = scanner.nextInt();
                    scanner.nextLine();
                    Studentdetail foundStudent = sms.findStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    List<Studentdetail> allStudents = sms.getAllStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println("No students in the system.");
                    } else {
                        for (Studentdetail s : allStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 5:
                    sms.saveToFile("students.dat");
                    System.out.println("Data saved to file.");
                    break;
                case 6:
                    sms.loadFromFile("students.dat");
                    System.out.println("Data loaded from file.");
                    break;
                case 7:
                    System.out.println("Exiting application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
