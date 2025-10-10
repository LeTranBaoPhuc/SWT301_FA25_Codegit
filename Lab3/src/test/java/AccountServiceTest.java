import letranbaophuc.example.AccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {

    private static AccountService service;

    @BeforeAll
    static void setUp() {
        service = new AccountService();
        try (PrintWriter pw = new PrintWriter("UnitTestResults.csv")) {
            pw.println("username,password,email,expected,actual,result");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest(name = "Test {index} => username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, String expected) {
        boolean expectedResult = Boolean.parseBoolean(expected);
        boolean actualResult = service.registerAccount(username, password, email);

        writeTestResult(username, password, email, expectedResult, actualResult);
        assertEquals(expectedResult, actualResult);
    }

    private void writeTestResult(String username, String password, String email,
                                 boolean expectedResult, boolean actualResult) {
        try (FileWriter fw = new FileWriter("UnitTestResults.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {

            String result = (expectedResult == actualResult) ? "PASS" : "FAIL";
            pw.printf("%s,%s,%s,%s,%s,%s%n",
                    username, password, email, expectedResult, actualResult, result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
