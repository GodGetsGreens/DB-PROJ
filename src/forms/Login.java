package forms;

import connection.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {
    private static JFrame newLog;
    private JPanel panelLogin;
    private JTextField userField;
    private JPasswordField passField;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton loginButton;
    private JButton create_user;
    private boolean isValid = false; // added variable to keep track of when to set Login form invisible
//    private boolean isRegister = false;

    public Login() {

        frame();

        create_user.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Register newReg = new Register();
                newLog.dispose();
            }
        });
    }

    public JPanel getLoginPanel(){
        return panelLogin;
    }

    /**
     * frame() contains all the declarations for the login JFrame button listeners, etc.
     *
     */
    private void frame() {

        newLog = new JFrame();
        newLog.setContentPane(panelLogin);
        newLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public boolean getValid(){ return isValid; }

//    public boolean getRegister() {return isRegister;}

    public String getUsername(){
        return userField.getText().trim();
    }

    public static void newFrame(){

        newLog.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
