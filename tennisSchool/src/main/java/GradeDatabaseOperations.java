import java.sql.*;
import java.util.Scanner;
import java.util.logging.*;


public class GradeDatabaseOperations implements DatabaseOperations {
    private Scanner scanner;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    public GradeDatabaseOperations(Scanner scanner) {
    	this.scanner = scanner;
    };
    @Override
    public void add()throws ClassNotFoundException, SQLException {
    	 System.out.println("Student ID :");
         int studentId = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Subject ID :");
         int subjectId = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Note :");
         double grade = scanner.nextDouble();
         scanner.nextLine();
        String sql = "INSERT INTO grade (student_id, subject_id, grade) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, subjectId);
            pstmt.setDouble(3, grade);
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
	
	public void afficherMoyennesParEtudiantEtMatiere() throws ClassNotFoundException, SQLException {
	    String sql = "SELECT student.firstname, student.lastname, subject.name AS subject_name, " +
	                 "SUM(grade.grade * subject.factor) / SUM(subject.factor) AS moyenne_ponderee " +
	                 "FROM grade " +
	                 "JOIN subject ON grade.subject_id = subject.id " +
	                 "JOIN student ON grade.student_id = student.id " +
	                 "GROUP BY grade.student_id, subject.id, student.firstname, student.lastname, subject.name " +
	                 "ORDER BY student.lastname, student.firstname, subject.name";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        while (rs.next()) {
	            System.out.println("Étudiant: " + rs.getString("firstname") + " " + rs.getString("lastname") +
	                               ", Matière: " + rs.getString("subject_name") +
	                               ", Moyenne: " + rs.getDouble("moyenne_ponderee"));
	        }
	    } catch (SQLException e) {
	        System.out.println("Erreur lors du calcul des moyennes par étudiant et par matière : " + e.getMessage());
	    }
	}
	
	public void afficherMatieresARetravailler() throws ClassNotFoundException, SQLException {
	    String sql = "SELECT student.firstname, student.lastname, subject.name AS subject_name, grade.grade " +
	                 "FROM grade " +
	                 "JOIN student ON grade.student_id = student.id " +
	                 "JOIN subject ON grade.subject_id = subject.id " +
	                 "WHERE grade.grade < 15 " +
	                 "ORDER BY student.lastname, student.firstname, subject.name";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        while (rs.next()) {
	            System.out.println("Étudiant: " + rs.getString("firstname") + " " + rs.getString("lastname") +
	                               ", Matière à retravailler: " + rs.getString("subject_name") +
	                               ", Note: " + rs.getDouble("grade"));
	        }
	    } catch (SQLException e) {
	        System.out.println("Erreur lors de l'affichage des matières à retravailler : " + e.getMessage());
	    }
	}
	
	public void modifierNote() throws ClassNotFoundException, SQLException {
        System.out.println("ID de l'étudiant :");
        int studentId = scanner.nextInt();
        
        System.out.println("ID de la matière :");
        int subjectId = scanner.nextInt();
        
        System.out.println("Nouvelle note :");
        double newGrade = scanner.nextDouble();

        String sql = "UPDATE grade SET grade = ? WHERE student_id = ? AND subject_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newGrade);
            pstmt.setInt(2, studentId);
            pstmt.setInt(3, subjectId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                logger.log(Level.INFO, "Note mise à jour avec succès pour l'étudiant ID: {0} en matière ID: {1} à la note: {2} - {3}", 
                          new Object[]{studentId, subjectId, newGrade, new java.util.Date()});
            } else {
                logger.log(Level.WARNING, "Mise à jour échouée pour l'étudiant ID: {0} en matière ID: {1} - {2}", 
                          new Object[]{studentId, subjectId, new java.util.Date()});
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de la mise à jour de la note : " + e.getMessage(), e);
        }
    }
}
