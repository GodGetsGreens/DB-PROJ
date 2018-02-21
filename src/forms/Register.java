package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends Login {
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

    public Register() {
        user_register.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(new_fname.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Must enter first name");
                else if(new_lname.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Must enter last name");
                else if(new_username.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Must enter username");
                else if(new_email.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Must enter email");
                else if(String.valueOf(new_pass.getPassword()).equals(""))
                    JOptionPane.showMessageDialog(null,"Must enter password");
                else if(String.valueOf(new_pass_conf.getPassword()).equals(""))
                    JOptionPane.showMessageDialog(null,"Must confirm password");
                else if(new_account.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Must enter account number");
                else if(!String.valueOf(new_pass.getPassword()).equals(String.valueOf(new_pass_conf.getPassword())))
                    JOptionPane.showMessageDialog(null,"Passwords do not match!");
                else
                    JOptionPane.showMessageDialog(null,"Registration Successful!");

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
