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
    private JButton button3;
    private JLabel registerNewCustomerLabel;
    private JLabel lateBillsDisplayLabel;
    private JPanel dashPanel;
    private static JFrame employeeDash;

    private String employeeID;

    public EmployeeDashboard(String id){
        employeeDash = new JFrame();
        employeeDash.setContentPane(dashPanel);
        employeeDash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeDash.pack();
        employeeDash.setVisible(true);

        employeeID = id;

        setGreeting(id);


        employeeLogout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                employeeDash.dispose();
                EmployeeLogin.getFrame();
            }
        });
        createAccountButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                employeeDash.dispose();
                CustomerCreate newCust = new CustomerCreate(employeeID);
            }
        });
        markBillsLateButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ConnectionManager.markLateBills();
                JOptionPane.showMessageDialog(employeeDash,"Late bills updated!");
            }
        });
    }

    public void setGreeting(String id){
        employeeGreetingLabel.setText(ConnectionManager.getEmployeeName(id));
    }

    public static void getFrame(){
        employeeDash.setVisible(true);
    }
}
