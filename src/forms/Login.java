package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Login {
    private JPanel panelLogin;
    private JTextField userField;
    private JPasswordField passField;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton loginButton;
    private JButton create_user;

    public Login() {
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(userField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter a username");
                else if(passField.getPassword().toString().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter password");
                else {
                    try{
                        BufferedReader fread = new BufferedReader(new FileReader(new File("/Users/godgetsgreens/Documents/Database Design/Projects/Project4/DB-PROJ/src/forms/users.txt")));
                        String delim = "[,]";
                        String userInfo = fread.readLine();
                        boolean exists = false;
                        while(userInfo != null){
                            String[] user = userInfo.split(delim);
                            if(userField.getText().equals(user[0]) && String.valueOf(passField.getPassword()).equals(user[1])){
                                JOptionPane.showMessageDialog(null, "Login Successful!");
                                exists = true;
                                break;
                            }
                            else if(userField.getText().equals(user[0]) && !String.valueOf(passField.getPassword()).equals(user[1])){
                                JOptionPane.showMessageDialog(null, "Invalid password!");
                                exists = true;
                                break;
                            }
                            else
                                userInfo = fread.readLine();
                        }
                        fread.close();
                        if(!exists)
                            JOptionPane.showMessageDialog(null, "Username does not exist!");
                    }
                    catch(FileNotFoundException e1){
                        System.out.println(e1);
                    }
                    catch(IOException e2){
                        System.out.println(e2);
                    }
                }
            }
        });

        create_user.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame rframe = new JFrame("New User Registration");
                rframe.setContentPane(new Register().registerPanel);
                rframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                rframe.pack();
                rframe.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
