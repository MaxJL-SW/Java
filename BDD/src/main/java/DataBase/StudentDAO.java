package DataBase;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    boolean addStudent(Student student) throws ClassNotFoundException, SQLException;

    void deleteStudent(Student student) throws ClassNotFoundException, SQLException;

    void updateStudent(Student student) throws ClassNotFoundException, SQLException;

    List<Student> getAllStudents() throws ClassNotFoundException, SQLException;

    Student findStudentById(int id) throws ClassNotFoundException, SQLException;
}