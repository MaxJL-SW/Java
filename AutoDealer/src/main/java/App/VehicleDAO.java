package App;

public interface VehicleDAO {
    void insertVehicleIntoDatabase(String registrationNumber, String brand, String model, String dateOfFirstRegistration, int price);
    void displayVehiclesFromDatabase();
    void modifyVehicleInDatabase(String registrationNumber, String newBrand, String newModel, String newDateOfFirstRegistration, int newPrice);
    void deleteVehicleFromDatabase(String registrationNumber);
    void displayVehiclesByPriceRange(int minPrice, int maxPrice);
    void displayVehiclesByAge(int targetAge);
    void clearCarTable();
}