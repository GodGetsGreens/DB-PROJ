package forms;

import connection.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {
    private JPanel panelLogin;
    private JTextField userField;
    private JTextField passField;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton loginButton;
    private JButton create_user;
    private boolean isValid = false; // added variable to keep track of when to set Login form invisible

    public Login() {

        frame();

    }

    /**
     * frame() contains all the declarations for the login JFrame button listeners, etc.
     *
     */
    private void frame() {
        final JFrame frame = new JFrame("Login");
        frame.setContentPane(panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /*
        The loginButton listener checks to see if the user is a valid user. If so, isValid is set to true, the CustDashboard
        is created, and the Login frame is make invisible.
         */
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                String u = userField.getText().trim();
                String p = passField.getText().trim();
                isValid = ConnectionManager.validateUser(u,p);
                if(isValid) {
                    CustDashboard newDash = new CustDashboard();
                    frame.setVisible(false);
                }
            }
        });

        /*
        The create_user button, when clicked, creates a new Register object, and all the good stuff that comes with it.
         */
        create_user.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Register new_user = new Register();
            }
        });

    }

    public boolean getValid(){
        return isValid;
    }

    public String getUsername(){
        return userField.getText().trim();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
