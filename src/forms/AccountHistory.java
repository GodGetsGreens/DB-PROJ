package forms;

import connection.ConnectionManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AccountHistory {
    private JButton returnDash;
    private JPanel historyPanel;
    private JList historyList;

    private String account;

    public AccountHistory(String account){
        final JFrame historyFrame = new JFrame();
        historyFrame.setContentPane(historyPanel);
        historyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        historyFrame.pack();
        historyFrame.setVisible(true);

        this.account = account;

        DefaultListModel temp1 = new DefaultListModel();
        Vector temp = ConnectionManager.getPastBills(account);
        for(int i = 0; i < temp.size(); ++i){
            temp1.addElement(String.valueOf(temp.get(i)));
        }
//        temp1.addElement("one");
//        temp1.addElement("two");
//        temp1.addElement("three");
//
//        historyList.setModel(temp1);

//        String[] test = new String[]{"one","two","three"};
//        historyList = new JList(test);
//        historyList.getModel();

        historyList.setModel(temp1);

        returnDash.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                historyFrame.dispose();
                CustDashboard.getDash();
            }
        });
    }

}
