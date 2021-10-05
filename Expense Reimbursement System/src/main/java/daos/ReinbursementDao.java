package daos;

import java.sql.SQLException;
import java.util.List;

public interface ReinbursementDao <T> {
    T getById(int id);

    T getByUser(String username);
    
    List<T> getAll(int id) throws SQLException;

    void save(T t);

    void update(T t);

    void delete(int id);
}
