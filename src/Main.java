import forms.Login;
import user.User;

public class Main {

    public static void main(String[] args) {

        Login newLogin = new Login();
        User currentUser = new User();
        if (newLogin.getValid()) {
            currentUser.setUserName(newLogin.getUsername());
        }
    }

};
