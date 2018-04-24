/**
 * EmployeeLogin.java contains all the source code for the EmployeeLogin form
 *
 * Author: Riley Wells
 */

package forms;

import connection.ConnectionManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeLogin {

    private JLabel employeeLoginGreeting;
    private JTextField employeeIDField;
    private JPasswordField employeePassField;
    private JLabel emploeeIDLabel;
    private JLabel employeePassLabel;
    private JButton signOut;
    private JButton employeeLogin;
    private static JFrame employeeLogFrame;
    private JPanel empLogPanel;

    /**
     * Constructor for the EmployeeLogin object
     */
    public EmployeeLogin(){

        employeeLogFrame = new JFrame();
        employeeLogFrame.setContentPane(empLogPanel);
        employeeLogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeLogFrame.setTitle("DB Energy Solutions");
        employeeLogFrame.pack();
        employeeLogFrame.setVisible(true);

        // signOut button closes the EmployeeLogin form and re-displays the Login form
        signOut.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                employeeLogFrame.dispose();
                Login.newFrame();
            }
        });

        // employeeLogin button checks to see if the employee ID and password are valid and if so closes the EmployeeLogin form and
        // displays a new EmployeeDashboard form.
        //
        // if the employee information is not validated then an error message is displayed
        employeeLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(ConnectionManager.validateEmployee(employeeIDField.getText().trim(),String.valueOf(employeePassField.getPassword()))){
                    employeeLogFrame.dispose();
                    employeeIDField.setText("");
                    employeePassField.setText("");
                    EmployeeDashboard newDash = new EmployeeDashboard(employeeIDField.getText().trim());
                }
                else{
                    JOptionPane.showMessageDialog(employeeLogFrame,"Employee ID and/or Password Incorrect!");
                }
            }
        });
    }

    // static function used to re-display the EmployeeLogin form
    public static void getFrame(){
        employeeLogFrame.setVisible(true);
    }
}
