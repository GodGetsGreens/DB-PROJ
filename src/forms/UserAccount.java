/**
 * UserAccount.java contains all source code for the UserAccount form
 *
 * Author: Riley Wells
 */

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


    /**
     * UserAccount constructor
     * @param name - The customer username
     * @param account - The customer account number
     */
    public UserAccount(String name, String account){

        customerAccount = account;
        customerUserName = name;
        uAccountFrame = new JFrame();
        uAccountFrame.setContentPane(panel1);
        uAccountFrame.pack();
        uAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uAccountFrame.setTitle("DB Energy Solutions");
        uAccountFrame.setVisible(true);

        // setting all labels on form to contain the proper customer information
        userName.setText("Username: " + customerUserName);
        userAccount.setText("Account: " + customerAccount);
        userPhone.setText("Phone Number: " + ConnectionManager.getUserPhone(customerAccount));
        userAddress.setText("Service Address: " + ConnectionManager.getUserAddress(customerAccount));
        userEmail.setText("Email: " + ConnectionManager.getUserEmail(customerAccount));

        // returnToDash button closes the UserAccount form and re-displays the CustDashboard form
        returnToDash.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                uAccountFrame.dispose();
                CustDashboard.getDash();
            }
        });

        //changeAccountInfo button closes the UserAccount form and displays an EditUserAccount form
        changeAccountInfo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EditUserAccount edit = new EditUserAccount(customerAccount);
                uAccountFrame.dispose();
            }
        });
    }

    // static method used to re-display the UserAccount form
    public static void getFrame(){
        uAccountFrame.setVisible(true);
    }
}
