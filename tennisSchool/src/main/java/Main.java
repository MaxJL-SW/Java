import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;
        GradeDatabaseOperations gradeOperations = new GradeDatabaseOperations(scanner);
        StudentDatabaseOperations studentOperations = new StudentDatabaseOperations(scanner);
        SubjectDatabaseOperations subjectOperations = new SubjectDatabaseOperations(scanner);
        
        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
      
        while (!quitter) {
            System.out.println("Menu:");
            System.out.println("1. Enregistrer une note");
            System.out.println("2. Enregistrer un élève");
            System.out.println("3. Enregistrer une matière");
            System.out.println("4. Calculer et afficher les moyennes des étudiants par matière");
            System.out.println("5. Afficher afficher le meilleur étudiant par matière");
            System.out.println("6. Afficher pour chaque étudiant les matières à retravailler (en dessous de 15)");
            System.out.println("7. Modifier une note");
            System.out.println("8. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    gradeOperations.add();
                    break;
                case 2:
                	studentOperations.add();
                	break;
                case 3:
                	subjectOperations.add();
                	break;
                case 4:
                	gradeOperations.afficherMoyennesParEtudiantEtMatiere();
                	break;
                case 5:
                	studentOperations.afficherMeilleurEtudiantParMatiere();
                	break;
                case 6:
                	gradeOperations.afficherMatieresARetravailler();
                	break;
                case 7:
                	gradeOperations.modifierNote();
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
                    
                    
