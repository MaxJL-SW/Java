import java.sql.*;
import java.util.Scanner;
import java.util.logging.*;


public class StudentDatabaseOperations implements DatabaseOperations {
    private Scanner scanner;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    public StudentDatabaseOperations(Scanner scanner) {
    	this.scanner = scanner;
    };
    @Override
    public void add()throws ClassNotFoundException, SQLException {
    	 System.out.println("Student ID :");
         int id = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Firstname :");
         String firstname = scanner.nextLine();
         System.out.println("LastName:");
         String lastname = scanner.nextLine();
        String sql = "INSERT INTO student (id, firstname, lastname) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	System.out.println("Error adding student: " + e.getMessage());
        }
    }

	@Override
	public void delete() throws ClassNotFoundException, SQLException {
		
	}

	@Override
	public void list() throws ClassNotFoundException, SQLException {

	}
	
	public void afficherMeilleurEtudiantParMatiere() throws ClassNotFoundException, SQLException {
	    String sql = "SELECT s.name AS subject_name, st.firstname, st.lastname, g.max_grade " +
	                 "FROM (SELECT subject_id, MAX(avg_grade) AS max_grade " +
	                 "      FROM (SELECT subject_id, student_id, " +
	                 "                   SUM(grade * factor) / SUM(factor) AS avg_grade " +
	                 "            FROM grade " +
	                 "            JOIN subject ON grade.subject_id = subject.id " +
	                 "            GROUP BY subject_id, student_id) AS subgrades " +
	                 "      GROUP BY subject_id) AS g " +
	                 "JOIN (SELECT subject_id, student_id, " +
	                 "             SUM(grade * factor) / SUM(factor) AS avg_grade " +
	                 "      FROM grade " +
	                 "      JOIN subject ON grade.subject_id = subject.id " +
	                 "      GROUP BY subject_id, student_id) AS allgrades " +
	                 "ON g.subject_id = allgrades.subject_id AND g.max_grade = allgrades.avg_grade " +
	                 "JOIN student st ON allgrades.student_id = st.id " +
	                 "JOIN subject s ON g.subject_id = s.id " +
	                 "GROUP BY s.name, st.firstname, st.lastname, g.max_grade " +
	                 "ORDER BY s.name";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        while (rs.next()) {
	            System.out.println("Matière: " + rs.getString("subject_name") + 
	                               ", Meilleur étudiant: " + rs.getString("firstname") + " " + rs.getString("lastname") +
	                               ", Moyenne: " + rs.getDouble("max_grade"));
	        }
	    } catch (SQLException e) {
	        System.out.println("Erreur lors de l'affichage du meilleur étudiant par matière : " + e.getMessage());
	    }
	    logger.log(Level.INFO, "Fin du calcul - {0}", new java.util.Date());
	}


}
