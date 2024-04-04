import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import App.VehicleDAO;
import App.VehicleDAOImpl;

@ExtendWith(MockitoExtension.class)
public class VehicleDAOTest {

    @Mock
    private VehicleDAO vehicleDAO;
    @InjectMocks
    private VehicleDAOImpl vehicleDAOImpl;

    @Test
    public void insertVehicleIntoDatabaseTest() {
        String registrationNumber = "ABC123";
        String brand = "Test-brand";
        String model = "Test-model";
        String dateOfFirstRegistration = "01/01/2000";
        int price = 20000;
        
        doNothing().when(vehicleDAO).insertVehicleIntoDatabase(registrationNumber, brand, model, dateOfFirstRegistration, price);
        vehicleDAOImpl.insertVehicleIntoDatabase(registrationNumber, brand, model, dateOfFirstRegistration, price);
        verify(vehicleDAO, times(1)).insertVehicleIntoDatabase(registrationNumber, brand, model, dateOfFirstRegistration, price);
    }

    @Test
    public void displayVehiclesFromDatabaseTest() {
        doNothing().when(vehicleDAO).displayVehiclesFromDatabase();
        vehicleDAOImpl.displayVehiclesFromDatabase();
        verify(vehicleDAO, times(1)).displayVehiclesFromDatabase();
    }

}
