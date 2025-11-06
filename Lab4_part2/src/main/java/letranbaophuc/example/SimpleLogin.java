package letranbaophuc.example;

import java.util.logging.Level;
import java.util.logging.Logger;

interface LoginHandler {
    void login(String username, String password);
}

public class SimpleLogin implements LoginHandler {
    private static final Logger logger = Logger.getLogger(SimpleLogin.class.getName());

    @Override
    public void login(String username, String password) {
        if ("admin".equals(username) && "1234".equals(password)) {
            logger.log(Level.INFO, "Login successful for user: {0}", username);
        } else {
            logger.log(Level.WARNING, "Login failed for user: {0}", username);
        }
    }

    public static void main(String[] args) {
        LoginHandler user = new SimpleLogin();
        user.login("admin", "1234");
        user.login("guest", "wrongpass");
    }
}
