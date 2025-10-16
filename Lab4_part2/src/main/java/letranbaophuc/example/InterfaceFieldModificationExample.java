package letranbaophuc.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class AppConstants {
    public static final int MAX_USERS = 100;

    private AppConstants() {
        throw new UnsupportedOperationException("Utility class");
    }
}

public class InterfaceFieldModificationExample {
    private static final Logger logger = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public static void main(String[] args) {
        logger.log(Level.INFO, "Max users allowed: {0}", AppConstants.MAX_USERS);
    }
}
