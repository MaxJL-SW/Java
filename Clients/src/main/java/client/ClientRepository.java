package client;

import java.util.List;

public interface ClientRepository {
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(int clientId);
    List<Client> listClients();
}