import forms.Login;
import user.User;

public class Main {

    public static void main(String[] args) {

        User current = new User();
        Login newLogin = new Login();
        current.setUserName(newLogin.getUsername());

    }

};
