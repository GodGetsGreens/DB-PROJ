/**
 * Login.java contains all the source code for the Login form
 *
 * Authors: Riley Wells and Justin Bramel
 */

package forms;

import connection.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Login {

    private static JFrame newLog;
    private JPanel panelLogin;
    private JButton employeeLogin;
    private JTextPane loginHeader;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton create_user;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private boolean isValid = false; // added variable to keep track of when to set Login form invisible


    /*
    The Login constructor
     */
    public Login() {

        frame();

        // create user button pulls up the registration form
        create_user.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Register newReg = new Register();
                newLog.dispose();
            }
        });

        // employeeLogin button pulls up the login form for employees
        employeeLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EmployeeLogin newEmployee = new EmployeeLogin();
                newLog.dispose();
            }
        });
    }

    /**
     * frame() contains all the declarations for the login JFrame button listeners, etc.
     *
     */
    private void frame() {

        newLog = new JFrame();
        newLog.setContentPane(panelLogin);
        newLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newLog.setTitle("DB Energy Solutions");
        newLog.pack();
        newLog.setVisible(true);

        /*
        The loginButton listener checks to see if the user is a valid user. If so, isValid is set to true, the CustDashboard
        is created, and the Login frame is make invisible.
         */
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(userField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "Username required!");
                }
                else{
                    String u = userField.getText().trim();
                    String p = String.valueOf(passField.getPassword());
                    isValid = ConnectionManager.validateUser(u,p);
                    if(isValid) {
                        CustDashboard newCust = new CustDashboard(u,ConnectionManager.getAccountNumber(u));
                        newCust.setAccount(ConnectionManager.getAccountNumber(u));
                        newCust.setGreeting(ConnectionManager.getName(u));
                        newLog.dispose();
                        userField.setText("");
                        passField.setText("");
                    }
                    else{
                        userField.setText("");
                        passField.setText("");
                    }
                }
            }
        });

        /*
        The create_user button, when clicked, creates a new Register object, and all the good stuff that comes with it.
         */

    }

//    public String getUsername(){
//        return userField.getText().trim();
//    }


    // newFrame is a static method used to re-display the login form after logging out, etc.
    public static void newFrame(){

        newLog.setVisible(true);
    }

}
