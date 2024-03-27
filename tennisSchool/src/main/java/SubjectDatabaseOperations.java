import java.sql.*;
import java.util.Scanner;


public class SubjectDatabaseOperations implements DatabaseOperations {
    private Scanner scanner;
    
    public SubjectDatabaseOperations(Scanner scanner) {
    	this.scanner = scanner;
    };
    @Override
    public void add()throws ClassNotFoundException, SQLException {
    	 System.out.println("Subject ID :");
         int id = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Name :");
         String name = scanner.nextLine();
         System.out.println("Factor :");
         String factor = scanner.nextLine();
        String sql = "INSERT INTO subject (id, name, factor) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, factor);
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
}
