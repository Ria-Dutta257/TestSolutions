package dao;

import model.Student;
import java.util.*;

public class OracleStudentDAO implements StudentDAO {

    public void addStudent(Student student) {
        System.out.println("Oracle Add Student");
    }

    public List<Student> getAllStudents() {
        System.out.println("Oracle View Students");
        return new ArrayList<>();
    }

    public void updateStudent(Student student) {
        System.out.println("Oracle Update Student");
    }

    public void deleteStudent(int id) {
        System.out.println("Oracle Delete Student");
    }

	@Override
	public void updateEmailByMobile(String mobile, String newEmail) throws Exception {
		System.out.println("Oracle Update Email by Mobile");
		
	}
}
