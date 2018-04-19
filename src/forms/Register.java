package forms;

import com.sun.codemodel.internal.JOp;
import connection.ConnectionManager;
import connection.Queries;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Register {
    public JPanel registerPanel;
    private JTextArea pleaseUseTheEmailTextArea;
    private JTextField new_email;
    private JTextField new_phone;
    private JPasswordField new_passconf;
    private JTextField new_fname;
    private JTextField new_lname;
    private JTextField new_username;
    private JPasswordField new_pass;
    private JTextField new_account;
    private JButton user_register;
    private JButton loginReturn;
    private static JFrame regFrame;

    public JPanel getRegisterPanel(){
        return registerPanel;
    }

    /**
     * The Register constructor creates the new JFrame for registering users, as well as contains the function for the
     * user_register button!
     *
     */
    public Register() {

        regFrame = new JFrame();
        regFrame.setContentPane(registerPanel);
        regFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        regFrame.setTitle("DB Energy Solutions");
        regFrame.pack();
        regFrame.setVisible(true);


        /*
        The user_register listener first declares a boolean and sets it to true. All of the textfields are then
        checked for errors, and for the user table values that need to be unique, the database is checked to make sure
        that username, email, accountnum are not taken. (taking advantage of the ConnectionManager functions)**

        If all error checks are passed, then the information for the new user is added to the user table, taking advantage
        of the Queries functions to format the data to proper SQL
         */

        loginReturn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                regFrame.dispose();
                Login.newFrame();
            }
        });

        user_register.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(new_fname.getText().trim().equals("") || new_lname.getText().trim().equals("") || String.valueOf(new_pass.getPassword()).equals("")
                        || String.valueOf(new_passconf.getPassword()).equals("") || new_email.getText().trim().equals("")
                        || new_account.getText().trim().equals("") || new_username.getText().trim().equals("") ||
                        new_username.getText().trim().equals("") || new_phone.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(regFrame,"Please fill out all fields!");
                }

                else{

                    if(!String.valueOf(new_pass.getPassword()).equals(String.valueOf(new_passconf.getPassword()))){
                        JOptionPane.showMessageDialog(regFrame,"Passwords must match!");
                        return;
                    }
                    else if(ConnectionManager.emailExists(new_email.getText().trim()) || ConnectionManager.getUserEmail(new_account.getText().trim()).equals("")){
                        JOptionPane.showMessageDialog(regFrame,"Email is already in use or account number does not exist!");
                        return;
                    }
                    else if(ConnectionManager.userNameExists(new_username.getText().trim())){
                        JOptionPane.showMessageDialog(regFrame,"Username is already in use; Please choose another!");
                        return;
                    }
                    else if(!ConnectionManager.getUserEmail(new_account.getText().trim()).equals(new_email.getText().trim())){
                        JOptionPane.showMessageDialog(regFrame,"Be sure to use the same email account you used to request service!");
                        return;
                    }
                    else if(ConnectionManager.accountExists(new_account.getText().trim())){
                        JOptionPane.showMessageDialog(regFrame,"This account number is already in use! Double check you are entering the correct account number.");
                        return;
                    }
                    else{
                        try{
                            ConnectionManager.insertUser(Queries.addUser(new_username.getText().trim(),String.valueOf(new_pass.getPassword()),
                                    new_email.getText().trim(),new_fname.getText().trim(),new_lname.getText().trim(),new_account.getText().trim()));
                            ConnectionManager.setUserPhone(new_phone.getText().trim(),new_account.getText().trim());
                            regFrame.dispose();
                            Login.newFrame();
                        }
                        catch (Exception a){
                            JOptionPane.showMessageDialog(regFrame,a.toString());
                        }
                    }

                }

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
