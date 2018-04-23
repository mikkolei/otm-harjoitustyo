package opintoapp.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import opintoapp.dao.Database;
import opintoapp.dao.UserDao;

public class FakeUserDao implements UserDao {

    List<User> users;

    public FakeUserDao() {
        users = new ArrayList<>();
        User tester = new User(1, "mikko", "tester", "test");
        users.add(tester);
    }

    @Override
    public User create(User user) throws Exception {
        users.add(user);
        return user;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .filter(u -> u.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
