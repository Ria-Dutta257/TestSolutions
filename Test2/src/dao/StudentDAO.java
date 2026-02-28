package dao;

import model.Student;
import java.util.List;

public interface StudentDAO {

    void addStudent(Student student) throws Exception;

    List<Student> getAllStudents() throws Exception;

    void updateStudent(Student student) throws Exception;

    void deleteStudent(int id) throws Exception;
    
    void updateEmailByMobile(String mobile, String newEmail) throws Exception;
}
