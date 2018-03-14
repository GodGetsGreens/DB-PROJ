package forms;

import javax.swing.*;

import connection.ConnectionManager;
import user.User;

public class CustDashboard {

    private JPanel dashboard;
    private JButton currentBill;
    private JLabel greeting;
    private JButton previousPayment;
    private JButton viewProfile;
    private JButton logout;
    private JLabel dash_information;

    public CustDashboard(String name){
        JFrame dashFrame = new JFrame("Dashboard");
        dashFrame.setContentPane(dashboard);
        dashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashFrame.pack();
        dashFrame.setVisible(true);

        greeting.setText("Welcome, " + name + "!");
    }

}
