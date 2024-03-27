import java.sql.SQLException;

public interface DatabaseOperations {
    void add()throws ClassNotFoundException, SQLException;
    void delete()throws ClassNotFoundException, SQLException;
    void list()throws ClassNotFoundException, SQLException;
}
