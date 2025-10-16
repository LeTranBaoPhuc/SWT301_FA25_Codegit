import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import letranbaophuc.example.InsecureLogin;

class InsecureLoginTest {

    @Test
    void testLoginSuccess() {
        assertDoesNotThrow(() -> InsecureLogin.login("admin", "123456"),
                "Login with correct credentials should not throw exception");
    }

    @Test
    void testLoginFail() {
        assertDoesNotThrow(() -> InsecureLogin.login("user", "wrongpassword"),
                "Login with wrong credentials should not throw exception");
    }

    @Test
    void testPrintUserInfo() {
        InsecureLogin insecureLogin = new InsecureLogin();
        assertDoesNotThrow(() -> insecureLogin.printUserInfo("John Doe"),
                "Printing user info should not throw exception");
    }
}
