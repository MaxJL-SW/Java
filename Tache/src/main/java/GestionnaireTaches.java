import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;

// Classe de gestion des fonctionnalités des tâches
public class GestionnaireTaches {
    private List<Tache> taches;
    private Scanner scanner;
    
    public GestionnaireTaches() {
        taches = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Ajout d'une tâche
    public void ajouterTache() {
        System.out.println("Numéro de la tâche :");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Description de la tâche :");
        String description = scanner.nextLine();
        System.out.println("Statut (1 pour fini, 0 pour non fini) :");
        boolean statut = scanner.nextInt() == 1;
        scanner.nextLine();
        System.out.println("Date limite (format YYYY-MM-DD) :");
        LocalDate dateLimite = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        taches.add(new Tache(numero, description, statut, dateLimite));
    }
    
    // Suppression d'une tâche
    public void supprimerTache() {
        System.out.println("Numéro de la tâche à supprimer :");
        int numero = scanner.nextInt();
        taches.removeIf(t -> t.getNumero() == numero);
    }dtgu
    
    // Affichage des tâche terminées
    public void afficherTachesFinies() {
        taches.stream().filter(Tache::getStatut).forEach(System.out::println);
    }
    
    // Sauvegarde des tâches en cours
    public void sauvegarderTaches(String nomFichier) {
        try (PrintWriter out = new PrintWriter(nomFichier)) {
            for (Tache tache : taches) {
                out.println(tache.getNumero() + ";" + tache.getDescription() + ";" + tache.getStatut() + ";" + tache.getDateLimite());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur lors de la sauvegarde des tâches.");
        }
    }
    
    // Chargement des tâches 
    public void chargerTaches(String nomFichier) {
        try (Scanner scanner = new Scanner(new File(nomFichier))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                int numero = Integer.parseInt(data[0]);
                String description = data[1];
                boolean statut = Boolean.parseBoolean(data[2]);
                LocalDate dateLimite = LocalDate.parse(data[3]);
                taches.add(new Tache(numero, description, statut, dateLimite));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur lors du chargement des tâches.");
        }
    }
    
    // Affichage des tâches comprises entre deux dates 
    public void afficherTachesParDate() {
        System.out.println("Date de début (format YYYY-MM-DD) :");
        LocalDate debut = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("Date de fin (format YYYY-MM-DD) :");
        LocalDate fin = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        taches.stream().filter(t -> (t.getDateLimite().isEqual(debut) || t.getDateLimite().isAfter(debut)) && (t.getDateLimite().isEqual(fin) || t.getDateLimite().isBefore(fin)))
            .forEach(System.out::println);
    }
}
