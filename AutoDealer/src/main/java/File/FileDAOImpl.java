package File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import App.VehicleDAOImpl;

/**
 * Classe représentant le service de gestion des méthodes via fichier txt
 * @author maximejoly
 */
public class FileDAOImpl implements FileDAO {
	private static final Logger logger = LoggerFactory.getLogger(FileDAOImpl.class);
	// Affichage des véhicules du fichier data.txt 
	 public void readAndDisplayVehicles(String filePath) {
		 logger.info("Lecture et affichage des véhicules depuis le fichier {}", filePath);
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 // Affichage des véhicules de moins de 20 ans du fichier data.txt
	 public void filterAndDisplayVehiclesUnder20Years(String filePath) {
		 logger.info("Lecture et affichage des véhicules de moins de 20 ans depuis le fichier {}", filePath);
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] vehicleData = line.split(",");
	                String dateOfFirstRegistration = vehicleData[3];
	                int age = calculateVehicleAge(dateOfFirstRegistration);
	                if (age < 20) {
	                    System.out.println(line);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 // Calcul de l'age des véhicules (utilisé pour les véhicules du fichier et de la BDD)
	 public int calculateVehicleAge(String dateOfFirstRegistration) {
		 logger.info("Calcul des âges des véhicules");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate firstRegistrationDate = LocalDate.parse(dateOfFirstRegistration, formatter);
	        return LocalDate.now().getYear() - firstRegistrationDate.getYear();
	    }
	 
	 // Insertion des véhicules du fichier dans la BDD
	 public void insertVehiclesFromTextFile(String filePath) {
		 logger.info("Insertions en bdd des véhicules depuis le fichier {}", filePath);
	        VehicleDAOImpl vehiclesRepository = new VehicleDAOImpl();
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] vehicleData = line.split(",");
	                if (calculateVehicleAge(vehicleData[3]) < 20) {
	                    int price = Integer.parseInt(vehicleData[4]);
	                    vehiclesRepository.insertVehicleIntoDatabase(vehicleData[0], vehicleData[1], vehicleData[2], vehicleData[3], price);
	                }
	            }
	        } catch (Exception e) {
	            System.err.println("Erreur lors de l'insertion des véhicules : " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

}