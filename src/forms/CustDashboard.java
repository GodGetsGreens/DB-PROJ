/**
 * CustDashboard.java contains all the source code for the CustDashboard form
 *
 * Author: Riley Wells
 */

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
    private JButton viewProfile;
    private JButton logout;
    private JLabel dash_information;
    private JTextPane databaseEnergySolutionsWorksTextPane;
    private JButton accountHistory;
    private static JFrame dashFrame = new JFrame("Dashboard");

    public void setGreeting(String name){
        greeting.setText("Welcome, " + name + "!");
    }

    public void setAccount(String act){
        account = act;
    }

    public CustDashboard(String uname, String act){

        dashFrame.setContentPane(dashboard);
        dashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashFrame.setTitle("DB Energy Solutions");
        dashFrame.pack();
        dashFrame.setVisible(true);

        userName = uname;
        account = act;

        setGreeting(ConnectionManager.getName(userName));

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

//        String curBill = ConnectionManager.getCurrentBill(account);
//        ConnectionManager.updateBillLate(account);
//        if(!"".equals(curBill)){
//            if(!ConnectionManager.isBillPaid(account)) {
//                if (ConnectionManager.isBillLate(account)) {
//                    currentBill.setText("LATE - $" + curBill + " - Pay Now");
//                } else {
//                    currentBill.setText("Current Bill - $" + curBill + " - Pay Now");
//                }
//            }
//            else{
//                currentBill.setText("No Current Bill");
//            }
//        }
//        else{
//            currentBill.setText("No Current Bill");
//        }

        currentBill.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dashFrame.dispose();
                BillPayment newPayment = new BillPayment(account);
            }
        });

        accountHistory.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dashFrame.dispose();
                AccountHistory customerHistory = new AccountHistory(account);
            }
        });
    }

//    public void updateCurrentBillButton(){
//
//        String curBill = ConnectionManager.getCurrentBill(account);
//        ConnectionManager.updateBillLate(account);
//
//        if(!"".equals(curBill)){
//            if(!ConnectionManager.isBillPaid(account)) {
//                if (ConnectionManager.isBillLate(account)) {
//                    currentBill.setText("LATE - $" + curBill + " - Pay Now");
//                } else {
//                    currentBill.setText("Current Bill - $" + curBill + " - Pay Now");
//                }
//            }
//            else{
//                currentBill.setText("No Current Bill");
//            }
//        }
//        else{
//            currentBill.setText("No Current Bill");
//        }
//
//    }

    public static void getDash(){

        dashFrame.setVisible(true);
    }

}
