package letranbaophuc.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class Printer {
    private static final Logger logger = Logger.getLogger(Printer.class.getName());

    void print(String message) {
        logger.log(Level.INFO, message);
    }
}

class Report {
    private final Printer printer = new Printer();

    void generate() {
        printer.print("Generating report...");
        printer.print("Report completed successfully!");
    }

    public static void main(String[] args) {
        Report report = new Report();
        report.generate();
    }
}
