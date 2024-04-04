import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import client.ClientDAO;
import client.ClientDAOImpl;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	Scanner scanner = new Scanner(System.in);
    	ClientDAO clientDao = new ClientDAOImpl(scanner);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Modifier un client");
            System.out.println("3. Supprimer un client");
            System.out.println("4. Afficher tous les clients");
            System.out.println("5. Afficher les clients par date de contact");
            System.out.println("6. Afficher les clients par montant de facturation");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    clientDao.addClient();
                    break;
                case 2:
                   	System.out.println("Mise à jour d'un client");
                    System.out.print("ID du client : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau name : ");
                    String name = scanner.nextLine();
                    System.out.print("Nouvelle date de contact (YYYY-MM-DD) : ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Nouveau montant journalier de facturation : ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                	clientDao.updateClient(id,name,date,amount);
                    break;
                case 3:
                	clientDao.deleteClient();
                    break;
                case 4:
                	clientDao.listClients();
                    break;
                case 5:
                	clientDao.findClientByDate();
                    break;
                case 6:
                	clientDao.findClientByAmount();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}