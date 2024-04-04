package App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import database.DBConnection;

/**
 * Service contenant les méthodes de BBD sur les vhéicules
 * @author maximejoly
 */
public class VehicleDAOImpl implements VehicleDAO {
	// Logger
	private static final Logger logger = LoggerFactory.getLogger(VehicleDAOImpl.class);
	// Méthode d'insertion d'un véhicule en BDD
	public void insertVehicleIntoDatabase(String registrationNumber, String brand, String model, String dateOfFirstRegistration, int price) {
		logger.info("Insertion d'un véhicule en base de données : {}", registrationNumber);
        String sql = "INSERT INTO car (registration_number, brand, model, date_of_first_registration, price) VALUES (?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, registrationNumber);
            statement.setString(2, brand);
            statement.setString(3, model);
            statement.setString(4, dateOfFirstRegistration);
            statement.setInt(5, price);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	// Affichage des véhicules de la BDD
	public void displayVehiclesFromDatabase() {
		logger.info("Affichage des véhicules de la base de donnée.");
	    String sql = "SELECT * FROM car";
	    
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {
	        
	        while (resultSet.next()) {
	            System.out.println("Numéro d'immatriculation: " + resultSet.getString("registration_number") +
	                    ", Marque: " + resultSet.getString("brand") +
	                    ", Modèle: " + resultSet.getString("model") +
	                    ", Date de première immatriculation: " + resultSet.getDate("date_of_first_registration") +
	                    ", Prix: " + resultSet.getInt("price"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// Modifier un véhicule de la BDD
	public void modifyVehicleInDatabase(String registrationNumber, String newBrand, String newModel, String newDateOfFirstRegistration, int newPrice) {
		logger.info("Modification du véhicule en base de donnée : {}", registrationNumber);
	    String sql = "UPDATE car SET brand = ?, model = ?, date_of_first_registration = STR_TO_DATE(?, '%d/%m/%Y'), price = ? WHERE registration_number = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, newBrand);
	        statement.setString(2, newModel);
	        statement.setString(3, newDateOfFirstRegistration);
	        statement.setInt(4, newPrice);
	        statement.setString(5, registrationNumber);
	        int rowsAffected = statement.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Véhicule mis à jour avec succès.");
	        } else {
	            System.out.println("Aucun véhicule trouvé avec ce numéro d'immatriculation.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// Supprimer un véhicule de la BDD
	public void deleteVehicleFromDatabase(String registrationNumber) {
		logger.info("Suppression d'un véhicule en base de données : {}", registrationNumber);
	    String sql = "DELETE FROM car WHERE registration_number = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, registrationNumber);
	        int rowsAffected = statement.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Véhicule supprimé avec succès.");
	        } else {
	            System.out.println("Aucun véhicule trouvé avec ce numéro d'immatriculation.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// Affichage des véhicules de la BDD compris dans une tranche d'âge
	public void displayVehiclesByPriceRange(int minPrice, int maxPrice) {
		logger.info("Affichage des véhicules dans la plage de prix: {} - {}", minPrice, maxPrice);
	    String sql = "SELECT * FROM car WHERE price BETWEEN ? AND ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setInt(1, minPrice);
	        statement.setInt(2, maxPrice);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                System.out.println("Numéro d'immatriculation: " + resultSet.getString("registration_number") +
	                        ", Marque: " + resultSet.getString("brand") +
	                        ", Modèle: " + resultSet.getString("model") +
	                        ", Date de première immatriculation: " + resultSet.getDate("date_of_first_registration") +
	                        ", Prix: " + resultSet.getInt("price"));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// Affichage des véhicules de la BDD pour un âge donné
	public void displayVehiclesByAge(int targetAge) {
		logger.info("Affichage des véhicules de l'âge: {}", targetAge);
	    String sql = "SELECT * FROM car";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {
	        
	        while (resultSet.next()) {
	            String registrationNumber = resultSet.getString("registration_number");
	            String brand = resultSet.getString("brand");
	            String model = resultSet.getString("model");
	            String dateOfFirstRegistration = resultSet.getString("date_of_first_registration");
	            int price = resultSet.getInt("price");
	            
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDate date = LocalDate.parse(dateOfFirstRegistration, formatter);
	            int age = Period.between(date, LocalDate.now()).getYears();

	            if (age == targetAge) {
	                System.out.println("Numéro d'immatriculation: " + registrationNumber +
	                                   ", Marque: " + brand +
	                                   ", Modèle: " + model +
	                                   ", Date de première immatriculation: " + dateOfFirstRegistration +
	                                   ", Prix: " + price);
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("Erreur lors de l'affichage des véhicules par âge: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	// Suppression du contenu de la table Car pour le lancement du programme sans erreurs
	public void clearCarTable() {
		logger.info("Vidage de la table car");
	    String sql = "TRUNCATE TABLE car";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.executeUpdate();
	        System.out.println("La table 'car' a été vidée.");
	    } catch (Exception e) {
	        System.err.println("Erreur lors de la vidange de la table 'car': " + e.getMessage());
	        e.printStackTrace();
	    }
	}


}