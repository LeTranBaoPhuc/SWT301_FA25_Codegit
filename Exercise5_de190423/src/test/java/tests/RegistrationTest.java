package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Registration Form Tests – demoqa.com")
public class RegistrationTest extends BaseTest {

    static RegistrationPage registrationPage;

    @BeforeAll
    static void initPage() {
        registrationPage = new RegistrationPage(driver);
    }

    @ParameterizedTest(name = "Form input: {0} {1} / {2}")
    @Order(1)
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    void testFormSubmit(String first, String last, String email, String gender, String phone,
                        String subject, String hobby, String address, String state, String city) {

        registrationPage.navigate();
        registrationPage.fillBasicInfo(first, last, email, gender, phone);
        registrationPage.fillAdditionalInfo(subject, hobby, address, state, city);
        registrationPage.submitForm();

        assertTrue(registrationPage.isSuccessModalVisible(), "Form submission failed!");
    }
}
