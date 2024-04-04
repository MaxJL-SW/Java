package App;

import java.util.Scanner;

import File.FileDAOImpl;

public class Main {
    public static void main(String[] args) {
    	VehicleDAOImpl vehiclesService = new VehicleDAOImpl();
    	FileDAOImpl FileService = new FileDAOImpl();
    	// Clear de la BDD
    	vehiclesService.clearCarTable();
    	String filePath = "data.txt";
        System.out.println("Affichage de tous les véhicules :");
        FileService.readAndDisplayVehicles(filePath);
        System.out.println("\nAffichage des véhicules de moins de 20 ans :");
        FileService.filterAndDisplayVehiclesUnder20Years(filePath);
        FileService.insertVehiclesFromTextFile(filePath);
        
        Scanner scanner = new Scanner(System.in);
        
        // Menu Utilisateur
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Afficher les véhicules");
            System.out.println("2. Modifier un véhicule");
            System.out.println("3. Ajouter un véhicule");
            System.out.println("4. Supprimer un véhicule");
            System.out.println("5. Afficher les véhicules d’un âge donné");
            System.out.println("6. Afficher les véhicules dont le prix est compris entre un prix minimal et un prix maximal");
            System.out.println("7. Quitter");
            System.out.print("Entrez votre choix: ");

            int choice = scanner.nextInt();
            switch (choice) {
            case 1:
                vehiclesService.displayVehiclesFromDatabase();
                break;
            case 2:
                scanner.nextLine(); 
                System.out.print("Entrez le numéro d'immatriculation du véhicule à modifier: ");
                String registrationNumber = scanner.nextLine();
                System.out.print("Nouvelle marque: ");
                String brand = scanner.nextLine();
                System.out.print("Nouveau modèle: ");
                String model = scanner.nextLine();
                System.out.print("Nouvelle date de première immatriculation (dd/MM/yyyy): ");
                String dateOfFirstRegistration = scanner.nextLine();
                System.out.print("Nouveau prix: ");
                int price = scanner.nextInt();
                vehiclesService.modifyVehicleInDatabase(registrationNumber, brand, model, dateOfFirstRegistration, price);
                break;
            case 3:
                scanner.nextLine(); 
                System.out.print("Numéro d'immatriculation: ");
                String newRegistrationNumber = scanner.nextLine();
                System.out.print("Marque: ");
                String newBrand = scanner.nextLine();
                System.out.print("Modèle: ");
                String newModel = scanner.nextLine();
                System.out.print("Date de première immatriculation (dd/MM/yyyy): ");
                String newDate = scanner.nextLine();
                System.out.print("Prix: ");
                int newPrice = scanner.nextInt();
                vehiclesService.insertVehicleIntoDatabase(newRegistrationNumber, newBrand, newModel, newDate, newPrice);
                break;
            case 4:
                scanner.nextLine(); 
                System.out.print("Entrez le numéro d'immatriculation du véhicule à supprimer: ");
                String delRegistrationNumber = scanner.nextLine();
                vehiclesService.deleteVehicleFromDatabase(delRegistrationNumber);
                break;
            case 5:
            	System.out.print("Entrez l'âge des véhicules à afficher: ");
                int age = scanner.nextInt();
                vehiclesService.displayVehiclesByAge(age);
                break;
            case 6:
                System.out.print("Prix minimal: ");
                int minPrice = scanner.nextInt();
                System.out.print("Prix maximal: ");
                int maxPrice = scanner.nextInt();
                vehiclesService.displayVehiclesByPriceRange(minPrice, maxPrice);
                break;
            case 7:
                System.out.println("Au revoir !");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Choix invalide.");
            }
        }
    }
}
