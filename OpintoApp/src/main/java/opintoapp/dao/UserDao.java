
package opintoapp.dao;

import java.util.List;
import opintoapp.domain.User;

public interface UserDao {
    
    User create(User user) throws Exception;
    
    User findByName(String username);
    
    List<User> getAll();
    
}
