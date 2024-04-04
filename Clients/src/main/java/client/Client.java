package client;

import java.time.LocalDate;
import java.util.Date;

/**
 * Classe représentant un client 
 * @author maximejoly
 */

// Données
public class Client {
    private int id;
    private String name;
    private Date date;
    private double amount;
    
    // Constructeur
    public Client(int id, String name, Date date, double amount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
    }
    
    // Getters
    public int getId() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public Date getDate() {
    	return date;
    }
    
    public double getAmount() {
    	return amount;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    // Méthode de transcription en chaine de caractères
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + name + '\'' +
                ", dateDeContact=" + date +
                ", montantJournalierFacturation=" + amount +
                '}';
    }
}
