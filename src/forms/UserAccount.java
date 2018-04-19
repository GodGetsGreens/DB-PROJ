package forms;

import connection.ConnectionManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserAccount {

    private String customerUserName;
    private String customerAccount;
    private JPanel panel1;
    private JLabel userName;
    private JLabel userAccount;
    private JLabel userPhone;
    private JLabel userAddress;
    private JLabel userEmail;
    private JButton returnToDash;
    private JButton changeAccountInfo;
    private JLabel accountGreeting;
    private static JFrame uAccountFrame;


    public UserAccount(String name, String account){
        customerAccount = account;
        customerUserName = name;
        uAccountFrame = new JFrame();
        uAccountFrame.setContentPane(panel1);
        uAccountFrame.pack();
        uAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uAccountFrame.setTitle("DB Energy Solutions");
        uAccountFrame.setVisible(true);

        userName.setText("Username: " + customerUserName);
        userAccount.setText("Account: " + customerAccount);
        userPhone.setText("Phone Number: " + ConnectionManager.getUserPhone(customerAccount));
        userAddress.setText("Service Address: " + ConnectionManager.getUserAddress(customerAccount));
        userEmail.setText("Email: " + ConnectionManager.getUserEmail(customerAccount));


        returnToDash.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                uAccountFrame.dispose();
                CustDashboard.getDash();
            }
        });
        changeAccountInfo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EditUserAccount edit = new EditUserAccount(customerAccount);
                uAccountFrame.dispose();
            }
        });
    }

    public void update(){
        customerUserName = ConnectionManager.getUserName(customerAccount);
        userName.setText("Username: " + customerUserName);
        userAccount.setText("Account: " + customerAccount);
        userPhone.setText("Phone Number: " + ConnectionManager.getUserPhone(customerAccount));
        userAddress.setText("Service Address: " + ConnectionManager.getUserAddress(customerAccount));
        userEmail.setText("Email: " + ConnectionManager.getUserEmail(customerAccount));

    }

    public static void getFrame(){
        uAccountFrame.setVisible(true);
    }
}
