
package opintoapp.dao;

import opintoapp.domain.User;
import java.sql.SQLException;

/**
 * Interface class for DAO implementation of User class
 */
public interface UserDao {
    
    /**
     * Create new user
     * @param user user to be created
     * @return created used
     * @throws Exception Exception if the operation fails
     */
    User create(User user) throws Exception;
    
    /**
     * Find user by username and password for the loginScene
     * @param username username
     * @param password password
     * @return Found user
     * @throws SQLException Exception if the operation fails
     */
    User findByUsernameAndPassword(String username, String password) throws SQLException;
    
    /**
     * Checks whether the username is already taken
     * @param username username
     * @return true if name already taken false otherwise
     * @throws SQLException Exception if the operation fails
     */
    boolean checkUsernameAvailability(String username) throws SQLException;
    
    /**
     * Find user by id for adding the course to correct user
     * @param id id of the user
     * @return Found user
     * @throws SQLException Exception if the operation fails
     */
    User findById(int id) throws SQLException;
        
}
