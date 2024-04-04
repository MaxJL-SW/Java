package client;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.DBConnection;

public class ClientDAOImpl implements ClientDAO {
	private Scanner scanner;
	
	public ClientDAOImpl(Scanner scanner) {
		this.scanner = scanner;
	}
    @Override
    public boolean addClient(Client client) throws ClassNotFoundException, SQLException {
    	System.out.println("Entrez le name du client : ");
        String name = scanner.nextLine();
        System.out.println("Entrez la date de contact (YYYY-MM-DD) : ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.println("Entrez le montant journalier de facturation : ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        String query = "INSERT INTO client (name, date, amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
    }

    @Override
    public boolean updateClient(int id, String name, Date date, int amount) throws ClassNotFoundException {
 

        String query = "UPDATE client SET name = ?, date = ?, amount = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDate(2, date);
            stmt.setDouble(3, amount);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Client mis à jour avec succès.");
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
       
    }

    @Override
    public boolean deleteClient(int id) throws ClassNotFoundException, SQLException {
    	System.out.println("Suppression d'un client");
        System.out.print("ID du client à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        String query = "DELETE FROM client WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Client supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listClients() throws ClassNotFoundException, SQLException {
        List<Client> clients = new ArrayList<>();
        System.out.println("Liste de tous les clients :");
        String query = "SELECT * FROM client";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", name: " + rs.getString("name") +
                        ", Date de Contact: " + rs.getDate("date").toLocalDate() +
                        ", Montant Journalier de Facturation: " + rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findClientByDate() throws ClassNotFoundException, SQLException {
        System.out.println("Recherche de clients par date de contact");
        System.out.print("Date de début (YYYY-MM-DD) : ");
        LocalDate debut = LocalDate.parse(scanner.nextLine());
        System.out.print("Date de fin (YYYY-MM-DD) : ");
        LocalDate fin = LocalDate.parse(scanner.nextLine());

        String query = "SELECT * FROM client WHERE date BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(debut));
            stmt.setDate(2, Date.valueOf(fin));
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Aucun client trouvé dans cette plage de dates.");
                    return; 
                } else {
                    do {
                       
                        System.out.println("ID: " + rs.getInt("id") + ", Nom: " + rs.getString("name") +
                            ", Date de Contact: " + rs.getDate("date").toLocalDate() +
                            ", Montant Journalier de Facturation: " + rs.getDouble("amount"));
                    } while (rs.next());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void findClientByAmount() throws ClassNotFoundException, SQLException {
        System.out.println("Recherche de clients par montant de facturation");
        System.out.print("Montant de facturation : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        String query = "SELECT * FROM client WHERE amount = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, montant);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Aucun client trouvé avec ce montant de facturation.");
                    return;
                } else {
                    do {
                        System.out.println("ID: " + rs.getInt("id") + ", Nom: " + rs.getString("name") +
                            ", Date de Contact: " + rs.getDate("date").toLocalDate() +
                            ", Montant Journalier de Facturation: " + rs.getDouble("amount"));
                    } while (rs.next());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
