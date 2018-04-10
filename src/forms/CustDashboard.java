package forms;

import javax.swing.*;

import connection.ConnectionManager;
import user.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustDashboard {

    public JPanel dashboard;
    private String userName;
    private String account;
    private JButton currentBill;
    private JLabel greeting;
    private JButton previousPayment;
    private JButton viewProfile;
    private JButton logout;
    private JLabel dash_information;
    private static JFrame dashFrame = new JFrame("Dashboard");

    public JPanel getCustDashboard(){
        return dashboard;
    }

    public void setGreeting(String name){
        greeting.setText("Welcome, " + name + "!");
    }

    public void setAccount(String act){
        account = act;
    }

    public CustDashboard(String uname, String act){

        dashFrame.setContentPane(dashboard);
        dashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashFrame.pack();
        dashFrame.setVisible(true);

        userName = uname;
        account = act;

        logout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Login.newFrame();
                dashFrame.dispose();
            }
        });
        viewProfile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                UserAccount uAc = new UserAccount(userName,account);
                dashFrame.dispose();
            }
        });

        String curBill = ConnectionManager.getCurrentBill(account);
        if(!"".equals(curBill)){
            currentBill.setText("Current Bill - $"+curBill+" - Pay Now");
        }
        else{
            currentBill.setText("No Current Bill");
        }

    }

    public static void getDash(){
        dashFrame.setVisible(true);
    }

}
