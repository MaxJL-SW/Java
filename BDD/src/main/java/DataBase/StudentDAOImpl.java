package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean addStudent(Student student) throws ClassNotFoundException, SQLException {
        StudentDBConfig studentDBConfig = new StudentDBConfig();
        Connection connection = studentDBConfig.getConnection();
        String insertionQuery = "INSERT INTO student (id, firstName, lastName) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);
        preparedStatement.setInt(1, student.getId());
        preparedStatement.setString(2, student.getFirstName());
        preparedStatement.setString(3, student.getLastName());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }

    @Override
    public List<Student> getAllStudents() throws ClassNotFoundException, SQLException {
        ArrayList<Student> students = new ArrayList<>();
        StudentDBConfig studentDBConfig = new StudentDBConfig();
        Connection connection = studentDBConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            System.out.println("id: " + id);
            students.add(new Student(firstName, lastName, id));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return students;
    }

    @Override
    public void deleteStudent(Student student) throws ClassNotFoundException, SQLException {
        StudentDBConfig studentDBConfig = new StudentDBConfig();
        Connection connection = studentDBConfig.getConnection();
        String deletionQuery = "DELETE FROM student WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deletionQuery);
        preparedStatement.setInt(1, student.getId());
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Student with ID " + student.getId() + " has been deleted successfully.");
        } else {
            System.out.println("No student found with ID " + student.getId() + ".");
        }
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void updateStudent(Student student) throws ClassNotFoundException, SQLException {
        StudentDBConfig studentDBConfig = new StudentDBConfig();
        Connection connection = studentDBConfig.getConnection();
        String updateQuery = "UPDATE student SET firstName = ?, lastName = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setInt(3, student.getId());
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Student with ID " + student.getId() + " has been updated successfully.");
        } else {
            System.out.println("No student found with ID " + student.getId() + ".");
        }
        preparedStatement.close();
        connection.close();
    }


    @Override
    public Student findStudentById(int id) throws ClassNotFoundException, SQLException {
    	StudentDBConfig studentDBConfig = new StudentDBConfig();
    	Connection connection = studentDBConfig.getConnection();
    	String selectQuery = "SELECT * FROM student WHERE id = ?";
    	PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
    	preparedStatement.setInt(1, id);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	Student student = null;
    	if (resultSet.next()) {
    		String firstName = resultSet.getString("firstName");
    		String lastName = resultSet.getString("lastName");
    		student = new Student(firstName, lastName, id);
    	}
    	resultSet.close();
    	preparedStatement.close();
    	connection.close();
    	return student;
    	}
}