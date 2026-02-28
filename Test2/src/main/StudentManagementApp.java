package main;

import dao.*;
import model.Student;
import exception.InvalidStudentDataException;

import java.util.*;

public class StudentManagementApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	
    	try {
            util.DBConnection.createTableIfNotExists();
        } catch (Exception e) {
            System.out.println("Database setup error: " + e.getMessage());
            return;
        }

        StudentDAO dao = new PostgresStudentDAO(); // Runtime Polymorphism

        while (true) {

            try {
                System.out.println("\n1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Update Email by Mobile");
                System.out.println("6. Exit");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        addStudent(dao);
                        break;
                    case 2:
                        viewStudents(dao);
                        break;
                    case 3:
                        updateStudent(dao);
                        break;
                    case 4:
                        deleteStudent(dao);
                        break;
                    case 5:
                        updateEmailByMobile(dao);
                        break;
                    case 6:
                        System.exit(0);
                }
            } catch (InvalidStudentDataException e) {
                System.out.println("Validation Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("System Error: " + e.getMessage());
            }
        }
    }

    static void addStudent(StudentDAO dao) throws Exception {

        System.out.println("Enter Name:");
        String name = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        System.out.println("Enter Age:");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Mobile:");
        String mobile = sc.nextLine();

        validate(name, email, age, mobile);

        Student student = new Student(name, email, age, mobile);

        dao.addStudent(student);

        System.out.println("Student Added Successfully!");
    }

    static void viewStudents(StudentDAO dao) throws Exception {

        List<Student> list = dao.getAllStudents();

        for (Student s : list) {
            System.out.println(
                    s.getId() + " | " +
                    s.getName() + " | " +
                    s.getEmail() + " | " +
                    s.getAge() + " | " +
                    s.getMobile()
            );
        }
    }

    static void updateStudent(StudentDAO dao) throws Exception {

        System.out.println("Enter ID to Update:");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter New Name:");
        String name = sc.nextLine();

        System.out.println("Enter New Email:");
        String email = sc.nextLine();

        System.out.println("Enter New Age:");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter New Mobile:");
        String mobile = sc.nextLine();

        validate(name, email, age, mobile);

        Student student = new Student(id, name, email, age, mobile);

        dao.updateStudent(student);

        System.out.println("Student Updated Successfully!");
    }

    static void deleteStudent(StudentDAO dao) throws Exception {

        System.out.println("Enter ID to Delete:");
        int id = sc.nextInt();

        dao.deleteStudent(id);

        System.out.println("Student Deleted Successfully!");
    }
    
    static void updateEmailByMobile(StudentDAO dao) throws Exception {

        System.out.println("Enter Mobile Number:");
        String mobile = sc.nextLine();

        System.out.println("Enter New Email:");
        String newEmail = sc.nextLine();

        if (!newEmail.contains("@")) {
            throw new InvalidStudentDataException("Invalid Email Format");
        }

        dao.updateEmailByMobile(mobile, newEmail);
    }

    static void validate(String name, String email, int age, String mobile)
            throws InvalidStudentDataException {

        if (name.isEmpty() || name.matches("\\d+"))
            throw new InvalidStudentDataException("Name cannot be empty or numeric");

        if (!email.contains("@"))
            throw new InvalidStudentDataException("Email must contain @");

        if (age <= 0)
            throw new InvalidStudentDataException("Age must be positive");

        if (!mobile.matches("\\d{10}"))
            throw new InvalidStudentDataException("Mobile must contain exactly 10 digits");
    }
}
