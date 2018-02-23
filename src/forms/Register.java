package forms;

import connection.ConnectionManager;
import connection.Queries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Register {
    protected JPanel registerPanel;
    private JTextField new_fname;
    private JTextField new_lname;
    private JTextField new_username;
    private JTextField new_email;
    private JPasswordField new_pass;
    private JLabel f_name;
    private JLabel l_name;
    private JLabel u_name;
    private JPasswordField new_pass_conf;
    private JLabel u_pass;
    private JLabel confirm_pass;
    private JLabel account;
    private JTextField new_account;
    private JButton user_register;

    /**
     * The Register constructor creates the new JFrame for registering users, as well as contains the function for the
     * user_register button!
     *
     */
    public Register() {


        /*
        The new JFrame is created
         */
        final JFrame new_user = new JFrame("New User Registration");
        new_user.setContentPane(registerPanel);
        new_user.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        new_user.pack();
        new_user.setVisible(true);


        /*
        The user_register listener first declares a boolean and sets it to true. All of the textfields are then
        checked for errors, and for the user table values that need to be unique, the database is checked to make sure
        that username, email, accountnum are not taken. (taking advantage of the ConnectionManager functions)**

        If all error checks are passed, then the information for the new user is added to the user table, taking advantage
        of the Queries functions to format the data to proper SQL
         */
        user_register.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                boolean add = true;
                if(new_fname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Must enter first name");
                    add = false;
                }
                else if(new_lname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Must enter last name");
                    add = false;
                }
                else if(new_username.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Must enter username");
                    add = false;
                }
                else if(ConnectionManager.userNameExists(new_username.getText().trim())){ // check to make sure the userName doesnt exist
                    new_username.setText("Username already exists!");
                    add = false;
                }
                else if(new_email.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Must enter email");
                    add = false;
                }
                else if(ConnectionManager.emailExists(new_email.getText().trim())){ // check to make sure that the email doesnt exist
                    new_email.setText("Email already in use!");
                    add = false;
                }
                else if(String.valueOf(new_pass.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(null, "Must enter password");
                    add = false;
                }
                else if(String.valueOf(new_pass_conf.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(null, "Must confirm password");
                    add = false;
                }
                else if(new_account.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Must enter account number");
                    add = false;
                }
                else if(ConnectionManager.accountExists(new_account.getText().trim())){ // check to make sure that the accountnum doesnt exist
                    new_account.setText("Account number already exists!");
                    add = false;
                }
                else if(!String.valueOf(new_pass.getPassword()).equals(String.valueOf(new_pass_conf.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!");
                    add = false;
                }
                else {
                    if(add) {
                        JOptionPane.showMessageDialog(null, "Registration Successful!");
                        try {
                            ConnectionManager.insertUser(Queries.addUser(new_username.getText().trim(), String.valueOf(new_pass.getPassword()).trim(),
                                    new_email.getText().trim(), new_fname.getText().trim(),
                                    new_lname.getText().trim(), new_account.getText().trim()));
                        }
                        catch(SQLException s){
                            JOptionPane.showMessageDialog(null,s.toString());
                        }
                        new_user.setVisible(false); //if the registration goes through, hide the registration form
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
