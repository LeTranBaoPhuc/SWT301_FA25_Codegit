package letranbaophuc.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OvercatchingExceptionExample {
    private static final Logger logger = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            int[] arr = new int[5];
            arr[0] = 10;
            logger.log(Level.INFO, "First element: {0}", arr[0]);

            logger.log(Level.INFO, "Accessing element index 2: {0}", arr[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "Error: Array index out of range - {0}", e.getMessage());
        }
    }
}
