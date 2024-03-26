import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SymptomeCounter {
    public static void main(String[] args) {
        // Chemin vers le fichier d'entrée et de sortie
        Path inputPath = Paths.get("src/main/java/input.txt");
        Path outputPath = Paths.get("src/main/java/output.txt");
        
        // Utilisation d'une TreeMap pour trier les clés (noms des symptômes)
        Map<String, Integer> symptomeCounts = new TreeMap<>();
        
        try {
            // Lecture du fichier d'entrée
            List<String> lines = Files.readAllLines(inputPath);
            
            // Comptage des occurrences des symptômes
            for (String line : lines) {
                symptomeCounts.put(line, symptomeCounts.getOrDefault(line, 0) + 1);
            }
            
            // Préparation du contenu du fichier de sortie
            List<String> outputLines = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : symptomeCounts.entrySet()) {
                outputLines.add(entry.getKey() + ":" + entry.getValue());
            }
            
            // Écriture dans le fichier de sortie
            Files.write(outputPath, outputLines);
            
            System.out.println("Le fichier 'output.txt' a été écrit avec succès.");
        } catch (IOException e) {
            System.err.println("Une erreur est survenue lors de la lecture ou de l'écriture des fichiers.");
            e.printStackTrace();
        }
    }
}
