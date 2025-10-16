package letranbaophuc.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UnreachableCodeExample {
    private static final Logger logger = Logger.getLogger(UnreachableCodeExample.class.getName());

    public static int getNumber() {
        logger.log(Level.INFO, "Before returning value");
        return 42;
    }

    public static void main(String[] args) {
        logger.log(Level.INFO, "Returned number: {0}", getNumber());
    }
}
