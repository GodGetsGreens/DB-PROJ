package forms;

import connection.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.*;

public class Login {
    private JPanel panelLogin;
    private JTextField userField;
    private JTextField passField;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton loginButton;
    private JButton create_user;

    public Login() {

        frame();

    }

    private void frame() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                String u = userField.getText().trim();
                String p = passField.getText().trim();
                ConnectionManager.validateUser(u,p);
            }
        });

        create_user.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame rframe = new JFrame("New User Registration");
                rframe.setContentPane(new Register().registerPanel);
                rframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                rframe.pack();
                rframe.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        new Login();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
