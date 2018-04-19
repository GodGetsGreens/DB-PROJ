package forms;

import connection.ConnectionManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerCreate {
    private JButton returnDash;
    private JButton registerCustomer;
    private JTextField newUserAccount;
    private JTextField newUserEmail;
    private JTextField newUserAddress;
    private JLabel newUserAccountLabel;
    private JLabel newUserEmailLabel;
    private JLabel newUserAddressLabel;
    private JPanel customerCreate;
    private static JFrame custCreate;

    private String eid;

    public CustomerCreate(String id){
        custCreate = new JFrame();
        custCreate.setContentPane(customerCreate);
        custCreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        custCreate.pack();
        custCreate.setVisible(true);

        returnDash.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                custCreate.dispose();
                EmployeeDashboard.getFrame();
            }
        });

        registerCustomer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(newUserAccount.getText().trim().equals("") || newUserAddress.getText().trim().equals("") || newUserEmail.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(custCreate,"Please fill out all fields!");
                }
                else{
                    if(ConnectionManager.createNewCustomer(newUserAccount.getText().trim(),newUserEmail.getText().trim(),
                            "",newUserAddress.getText().trim())){
                        JOptionPane.showMessageDialog(custCreate,"Success!");
                        custCreate.dispose();
                        EmployeeDashboard.getFrame();
                    }
                }
            }
        });

    }

}
