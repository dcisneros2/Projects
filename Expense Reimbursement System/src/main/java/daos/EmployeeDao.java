package daos;

import java.util.List;
import java.sql.SQLException;


public interface EmployeeDao <T> {
    T getById(int id);

    T getByUser(String username);
    
    List<T> getAll(int id) throws SQLException;

    void save(T t);

    void update(T t);

    void delete(int id);
}

