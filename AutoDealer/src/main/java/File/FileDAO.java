package File;

public interface FileDAO {
    void readAndDisplayVehicles(String filePath);
    void filterAndDisplayVehiclesUnder20Years(String filePath);
    void insertVehiclesFromTextFile(String filePath);
}
