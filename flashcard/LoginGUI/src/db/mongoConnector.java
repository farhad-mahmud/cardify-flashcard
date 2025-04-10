package db;

public class mongoConnector {

    // Simulate checking username and password
    public static boolean validateLogin(String username, String password) {
        // Dummy login logic for now
        return username.equals("admin") && password.equals("1234");
    }

    // In future, you’ll add methods like:
    // saveUser(), fetchUser(), etc.
}
