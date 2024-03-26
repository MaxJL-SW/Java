import java.util.Scanner;

/**
 * Classe Main :)
 */
public class Main {
    public static void main(String[] args) {
        GestionnaireTaches gestionnaire = new GestionnaireTaches();
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;
        
        // Menu proposant les choix 
        while (!quitter) {
            System.out.println("Menu:");
            System.out.println("1. Ajouter une tache");
            System.out.println("2. Supprimer une tache");
            System.out.println("3. Afficher la liste des tâches finis");
            System.out.println("4. Enregistrer la liste des tâches dans un fichier");
            System.out.println("5. Charger la liste des tâches depuis un fichier");
            System.out.println("6. Afficher la liste des taches dont la date est comprise entre 2 dates");
            System.out.println("7. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    gestionnaire.ajouterTache();
                    break;
                case 2:
                    gestionnaire.supprimerTache();
                    break;
                case 3:
                    gestionnaire.afficherTachesFinies();
                    break;
                case 4:
                    gestionnaire.sauvegarderTaches("taches.txt");
                    break;
                case 5:
                    gestionnaire.chargerTaches("taches.txt");
                    break;
                case 6:
                    gestionnaire.afficherTachesParDate();
                    break;
                case 7:
                    quitter = true;
                    break;
                default:
                    System.out.println("Option non reconnue. Veuillez réessayer.");
                    break;
            }
        }
        scanner.close();
        System.out.println("Programme terminé.");
    }
}
