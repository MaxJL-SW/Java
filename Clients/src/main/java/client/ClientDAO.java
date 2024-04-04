package client;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {

    boolean addClient(Client client) throws ClassNotFoundException, SQLException;

    boolean deleteClient(int id);

    boolean updateClient(int id, String name, Date date, int amount) throws ClassNotFoundException, SQLException;

    List<Client> getAllClients() throws ClassNotFoundException, SQLException;

    List<Client> getClientsContactDateBetween(Date start, Date end) throws ClassNotFoundException, SQLException;

    List<Client> getClientsByDailyAmount() throws ClassNotFoundException, SQLException;

} 