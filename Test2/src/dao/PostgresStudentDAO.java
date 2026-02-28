package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class PostgresStudentDAO implements StudentDAO {

    public void addStudent(Student student) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO student (name, email, age, mobile) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setInt(3, student.getAge());
        ps.setString(4, student.getMobile());

        ps.executeUpdate();

        con.close();
    }

    public List<Student> getAllStudents() throws Exception {

        List<Student> list = new ArrayList<>();

        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM student";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age"),
                    rs.getString("mobile")
            );

            list.add(s);
        }

        con.close();
        return list;
    }

    public void updateStudent(Student student) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "UPDATE student SET name=?, email=?, age=?, mobile=? WHERE id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setInt(3, student.getAge());
        ps.setString(4, student.getMobile());
        ps.setInt(5, student.getId());

        ps.executeUpdate();
        con.close();
    }

    public void deleteStudent(int id) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM student WHERE id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ps.executeUpdate();
        con.close();
    }
    
    public void updateEmailByMobile(String mobile, String newEmail) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "UPDATE student SET email = ? WHERE mobile = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, newEmail);
        ps.setString(2, mobile);

        int rows = ps.executeUpdate();

        if (rows == 0) {
            System.out.println("No student found with this mobile number.");
        } else {
            System.out.println("Email updated successfully!");
        }

        con.close();
    }
}
