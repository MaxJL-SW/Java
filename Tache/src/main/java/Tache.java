import java.time.LocalDate;

/**
 * Classe représentant une tache
 */
public class Tache {
    private int numero;
    private String description;
    private boolean statut;
    private LocalDate dateLimite;

    // Constructeur
    public Tache(int numero, String description, boolean statut, LocalDate dateLimite) {
        this.numero = numero;
        this.description = description;
        this.statut = statut;
        this.dateLimite = dateLimite;
    }
    
    // Getters & Setters
    public int getNumero() { return numero; }
    public String getDescription() { return description; }
    public boolean getStatut() { return statut; }
    public LocalDate getDateLimite() { return dateLimite; }
    
    // Transcription des données d'une tâche en chaine de caractère
    @Override
    public String toString() {
        return numero + " - " + description + " - Statut: " + (statut ? "Fini" : "Non fini") + " - Date Limite: " + dateLimite;
    }
}
