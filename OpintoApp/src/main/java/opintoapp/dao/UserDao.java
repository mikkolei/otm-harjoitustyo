
package opintoapp.dao;

import java.util.List;
import opintoapp.domain.User;
import java.sql.SQLException;

public interface UserDao {
    
    User create(User user) throws Exception;
    
    User findByUsernameAndPassword(String username, String password) throws SQLException;
    
    User findByUsername(String username) throws SQLException;
    
    User findById(int id) throws SQLException;
    
    List<User> getAll();
    
}
