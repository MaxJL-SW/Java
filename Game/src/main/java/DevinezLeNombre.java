import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DevinezLeNombre {
    private static final String SCORE_FILE = "meilleurScore.txt";

    public static void main(String[] args) {
        int nombreMystere = (int) (Math.random() * 100) + 1;
        int nombreEssais = 0;
        int meilleurScore = lireMeilleurScore();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Je pense à un nombre entre 1 et 100.");
        if (meilleurScore > 0) {
            System.out.println("Le meilleur score est de " + meilleurScore + " tours, essayez de le battre !");
        } else {
            System.out.println("Soyez le premier à établir un score !");
        }

        int guess = 0;
        boolean nombreDevine = false;
        while (!nombreDevine) {
            System.out.print("Entrez votre suggestion : ");
            try {
                guess = scanner.nextInt();
                nombreEssais++;

                if (guess > nombreMystere) {
                    System.out.println("Trop élevé !");
                } else if (guess < nombreMystere) {
                    System.out.println("Trop bas !");
                } else {
                    nombreDevine = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next(); // Important pour ignorer l'entrée incorrecte et éviter une boucle infinie
            }
        }

        System.out.println("Bravo, vous avez deviné le nombre en " + nombreEssais + " essais !");
        if (meilleurScore == 0 || nombreEssais < meilleurScore) {
            ecrireMeilleurScore(nombreEssais);
            System.out.println("Nouveau meilleur score !");
        }
    }

    private static int lireMeilleurScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    private static void ecrireMeilleurScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
