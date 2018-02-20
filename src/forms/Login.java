package forms;

import javax.swing.*;

public class Login {
    private JPanel panelLogin;
    private JTextField userField;
    private JTextField passField;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton loginButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
