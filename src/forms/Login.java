package forms;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Login {
    private JPanel panelLogin;
    private JTextField userField;
    private JTextField passField;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton loginButton;
    private JButton register;

    public Login() {
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(userField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter a username");
                else if(passField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter password");
                else {
                    try{
                        BufferedReader fread = new BufferedReader(new FileReader(new File("forms/users.txt")));
                        String delim = "[,]";
                        String userInfo = fread.readLine();
                        while(userInfo != null){
                            String[] user = userInfo.split(delim);
                            if(userField.getText().equals(user[0]) && passField.getText().equals(user[1])){
                                JOptionPane.showMessageDialog(null, "Login Successful!");
                                break;
                            }
                            else if(userField.getText().equals(user[0]) && !passField.getText().equals(user[1])){
                                JOptionPane.showMessageDialog(null, "Invalid password!");
                                break;
                            }
                            else
                                userInfo = fread.readLine();
                        }
                        fread.close();
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
        register.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(userField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter a username");
                else if(passField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter password");
                else {
                    try {
                        BufferedReader fread = new BufferedReader(new FileReader(new File("forms/users.txt")));
                        String delim = "[,]";
                        boolean exists = false;
                        String userInfo = fread.readLine();
                        while(userInfo != null){
                            String[] user = userInfo.split(delim);
                            if(userField.getText().equals(user[0])){
                                JOptionPane.showMessageDialog(null, "Username Taken!");
                                exists = true;
                                break;
                            }
                        }
                        fread.close();
                        if(!exists){
                            BufferedWriter fwrite = new BufferedWriter(new FileWriter(new File("forms/users.txt")));
                            fwrite.write(userField.getText() + "," + passField.getText());
                            fwrite.newLine();
                            fwrite.close();
                        }

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
