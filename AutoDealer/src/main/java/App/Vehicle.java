package App;

import java.util.Date;

/**
 * Classe représentant un véhicue
 * @author maximejoly
 */

// Données
public class Vehicle {
    private String registrationNumber;
    private String brand;
    private String model;
    private Date dateOfFirstRegistration;
    private int price;
    
    // Constructeur
    public Vehicle(String registrationNumber, String brand, String model, Date dateOfFirstRegistration, int price) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
    
    // Getters
    public String getRegistrationNumber() {
    	return registrationNumber;
    }
    
    public String getBrand() {
    	return brand;
    }
    
    public String getModel() {
    	return model;
    }
    
    public Date getDate() {
    	return dateOfFirstRegistration;
    }
    
    public double getPrice() {
    	return price;
    }
    
    // Setters
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setModel(String model) {
    	this.model = model;
    }

    public void setDate(Date dateOfFirstRegistration) {
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    // Méthode de transcription en chaine de caractères
    public String toString() {
        return "Car{" +
                "Immatriculation=" + registrationNumber +
                ", Marque='" + brand + '\'' +
                ", Model='" + model + '\'' +
                ", Date=" + dateOfFirstRegistration +
                ", Price=" + price +
                '}';
    }
}
