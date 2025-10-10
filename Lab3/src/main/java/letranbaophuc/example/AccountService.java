package letranbaophuc.example;

import java.util.HashSet;
import java.util.Set;

public class AccountService {

    private final Set<String> existingUsers = new HashSet<>();
    private final Set<String> existingEmails = new HashSet<>();
    public boolean registerAccount(String username, String password, String email) {
        if (isNullOrEmpty(username) || isNullOrEmpty(password) || isNullOrEmpty(email)) {
            return false;
        }
        if (username.length() <= 3 || existingUsers.contains(username)) {
            return false;
        }
        if (!isValidPassword(password) || !isValidEmail(email)) {
            return false;
        }


        if (existingEmails.contains(email)) {
            return false;
        }

        existingUsers.add(username);
        existingEmails.add(email);
        return true;
    }

    public boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public boolean isValidPassword(String password) {
        if (password == null || password.length() <= 6) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasDigit && hasSpecial;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
