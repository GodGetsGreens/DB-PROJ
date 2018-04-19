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

    public EmployeeLogin(){

        employeeLogFrame = new JFrame();
        employeeLogFrame.setContentPane(empLogPanel);
        employeeLogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeLogFrame.setTitle("DB Energy Solutions");
        employeeLogFrame.pack();
        employeeLogFrame.setVisible(true);

        signOut.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                employeeLogFrame.dispose();
                Login.newFrame();
            }
        });

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

//    public void setEmployeeLoginGreeting(String id){
//        employeeLoginGreeting.setText(ConnectionManager.getEmployeeName(id));
//    }

    public static void getFrame(){
        employeeLogFrame.setVisible(true);
    }
}
