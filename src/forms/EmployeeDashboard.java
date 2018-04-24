/**
 * EmployeeDashboard.java contains all the source code for the EmployeeLogin form
 *
 * Author: Riley Wells
 */

package forms;

import connection.ConnectionManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeDashboard {

    private JLabel employeeGreetingLabel;
    private JButton employeeLogout;
    private JButton createAccountButton;
    private JButton markBillsLateButton;
    private JButton removePaidBillsButton;
    private JLabel registerNewCustomerLabel;
    private JLabel lateBillsDisplayLabel;
    private JPanel dashPanel;
    private JButton newBillButton;
    private static JFrame employeeDash;

    private String employeeID;

    /**
     * Constructor for the EmployeeDashboard object that takes in the employee ID
     * @param id - The employee ID
     */
    public EmployeeDashboard(String id){

        employeeDash = new JFrame();
        employeeDash.setContentPane(dashPanel);
        employeeDash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeDash.pack();
        employeeDash.setVisible(true);

        employeeID = id;

        setGreeting(id);

        // employeeLogout button closes the EmployeeDashboard form and re-displays the EmployeeLogin form
        employeeLogout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                employeeDash.dispose();
                EmployeeLogin.getFrame();
            }
        });

        // createAccountButton closes the EmployeeDashboard form and displays the CustomerCreate form
        createAccountButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                employeeDash.dispose();
                CustomerCreate newCust = new CustomerCreate(employeeID);
            }
        });

        // markBillsLateButton runs the ConnectionManager markLateBills function and displays a message saying late bills updated
        markBillsLateButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ConnectionManager.markLateBills();
                JOptionPane.showMessageDialog(employeeDash,"Late bills updated!");
            }
        });

        // removePaidBillsButton runs the ConnectionManager removePaidBills function and displays a message saying the paid bills were removed
        removePaidBillsButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ConnectionManager.removePaidBills();
                JOptionPane.showMessageDialog(employeeDash,"Paid bills removed!");
            }
        });

        // newBillButton closes the EmployeeDashboard form and displays the CreateBill form
        newBillButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                employeeDash.dispose();
                CreateBill newBill = new CreateBill();
            }
        });
    }

    /**
     * setGreeting takes in the employee ID and sets the greeting label to the Employee first name
     * @param id - The employee ID
     */
    public void setGreeting(String id){
        employeeGreetingLabel.setText(ConnectionManager.getEmployeeName(id));
    }

    // static function used to re-display the EmployeeDashboard form
    public static void getFrame(){
        employeeDash.setVisible(true);
    }
}
