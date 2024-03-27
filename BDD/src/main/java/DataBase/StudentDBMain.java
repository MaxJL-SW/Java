package DataBase;

import java.sql.SQLException;
import java.util.Random;

public class StudentDBMain {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();
        try {
            int id = new Random().nextInt(10000);
            if (studentDAO.addStudent(new Student("sika" + id, "dove" + id, id))) {
                for (Student student : studentDAO.getAllStudents()) {
                    System.out.println(student.toString());
                }
            } else {
                System.out.println("Insertion failed !");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}