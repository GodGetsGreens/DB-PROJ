package forms;

import javax.swing.*;

public class CustDashboard {

    private JPanel dashboard;

    public CustDashboard(){
        final JFrame dashFrame = new JFrame("Dashboard");
        dashFrame.setContentPane(dashboard);
        dashFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dashFrame.pack();
        dashFrame.setVisible(true);
    }

}
