/**
 * EditUserAccount.java contains all the source code for the EditUserAccount form
 *
 * Author: Riley Wells
 */

package forms;

import connection.ConnectionManager;

import javax.swing.*;
import java.awt.event.*;

public class EditUserAccount {

    private JPanel panel1;
    private JLabel editUserTitle;
    private JButton submitChanges;
    private JButton cancelChanges;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel userNameLabel;
    private JLabel userPhoneLabel;
    private JLabel userEmailLabel;
    private JPasswordField textField4;
    private JPasswordField textField5;
    private JLabel userPasswordLabel;
    private JLabel userPasswordConfirmedLabel;

    private String uact;
    private boolean nameChange;
    private boolean phoneChange;
    private boolean emailChange;
    private boolean passwordChange;
    private boolean passwordChangeConfirm;

    /**
     * Constructor for EditUserAccount object that takes in the customers account number
     * @param account - The customer's account number
     */
    public EditUserAccount(String account){

        final JFrame editUser = new JFrame();
        editUser.setContentPane(panel1);
        editUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editUser.setTitle("DB Energy Solutions");
        editUser.pack();
        editUser.setVisible(true);

        // the boolean values below are used to check if the user has entered anything into the respective text fields
        uact = account;
        nameChange = false;
        phoneChange = false;
        emailChange = false;
        passwordChange = false;
        passwordChangeConfirm = false;

        /*
        submitChanges button
         */
        submitChanges.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(!(nameChange || phoneChange || emailChange || passwordChange || passwordChangeConfirm)){
                    JOptionPane.showMessageDialog(editUser,"No changes made.");
//                    editUser.dispose();
//                    UserAccount.getFrame();
                }
                else{
                    if(passwordChangeConfirm || passwordChange) {

                        if (phoneChange)
                            ConnectionManager.setUserPhone(textField2.getText().trim(), uact);

                        if(nameChange){

                            if (!ConnectionManager.userNameExists(textField1.getText().trim()) && nameChange)
                                ConnectionManager.setUserName(textField1.getText().trim(), uact);
                            else{
                                JOptionPane.showMessageDialog(null,"Username already exists!");
                                return;
                            }

                        }

                        if(emailChange){

                            if (!ConnectionManager.emailExists(textField3.getText().trim()))
                                ConnectionManager.setUserEmail(textField3.getText().trim(), uact);
                            else{
                                JOptionPane.showMessageDialog(null,"Email already exists!");
                                return;
                            }

                        }

                        if(String.valueOf(textField4.getPassword()).equals(String.valueOf(textField5.getPassword()))){
                            ConnectionManager.setUserPassword(String.valueOf(textField4.getPassword()),uact);
                        }
                        else{
                            JOptionPane.showMessageDialog(editUser,"Passwords do not match!");
                        }

                        JOptionPane.showMessageDialog(editUser, "Changes saved!");
                        CustDashboard newDash = new CustDashboard(ConnectionManager.getUserName(uact), uact);
                        editUser.dispose();

                    }
                    else{
                        if (phoneChange)
                            ConnectionManager.setUserPhone(textField2.getText().trim(), uact);

                        if(nameChange){

                            if (!ConnectionManager.userNameExists(textField1.getText().trim()) && nameChange)
                                ConnectionManager.setUserName(textField1.getText().trim(), uact);
                            else{
                                JOptionPane.showMessageDialog(null,"Username already exists!");
                                return;
                            }

                        }

                        if(emailChange){

                            if(!ConnectionManager.emailExists(textField3.getText().trim()))
                                ConnectionManager.setUserEmail(textField3.getText().trim(),uact);
                            else{
                                JOptionPane.showMessageDialog(null,"Email already exists!");
                                return;
                            }

                        }

                        JOptionPane.showMessageDialog(editUser, "Changes saved!");
                        CustDashboard newDash = new CustDashboard(ConnectionManager.getUserName(uact), uact);
                        editUser.dispose();

                    }
                }
            }
        });

        cancelChanges.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                editUser.dispose();
                UserAccount.getFrame();
            }
        });

        textField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                nameChange = true;
                System.out.println(nameChange);
            }
        });

        textField2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                phoneChange = true;
            }
        });

        textField3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                emailChange = true;
            }
        });

        textField4.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                passwordChange = true;
            }
        });
        textField5.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                passwordChangeConfirm = true;
            }
        });
    }

}
