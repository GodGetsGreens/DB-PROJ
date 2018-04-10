package forms;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserAccount {

    private String customerUserName;
    private JPanel panel1;
    private JLabel userName;
    private JLabel userAccount;
    private JLabel userPhone;
    private JLabel userAddress;
    private JLabel userEmail;
    private JButton returnToDash;
    private JButton changeAccountInfo;
    private JLabel accountGreeting;

    public UserAccount(String name){
        customerUserName = name;
        final JFrame uAccountFrame = new JFrame();
        uAccountFrame.setContentPane(panel1);
        uAccountFrame.pack();
        uAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uAccountFrame.setVisible(true);


        returnToDash.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                uAccountFrame.dispose();
                CustDashboard.getDash();
            }
        });
    }
}
