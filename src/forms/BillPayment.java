/**
 * BillPayment.java contains all the source code for the BillPayment form
 *
 * Author: Riley Wells
 */

package forms;

import connection.ConnectionManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.lang.String;

public class BillPayment {
    private JButton submitPayment;
    private JLabel currentBillLabel;
    private JComboBox comboBox1;
    private JTextField paymentAmount;
    private JLabel paymentMethodLabel;
    private JPanel paymentPanel;
    private JLabel currentBill;
    private JButton returnDashButton;
    private String theCurrentBill;
    private boolean hasCurrentBill;

    private static JFrame paymentFrame;
    private String accountNumber;

    public BillPayment(String account){

        paymentFrame = new JFrame();
        paymentFrame.setContentPane(paymentPanel);
        paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paymentFrame.pack();
        paymentFrame.setVisible(true);

        accountNumber = account;

        Vector userPayMethods = ConnectionManager.getPaymentMethods(accountNumber);

        comboBox1.addItem("-");
        for(int i = 0; i < userPayMethods.size(); ++i){
            comboBox1.addItem("Card Ending With - "+String.valueOf(userPayMethods.get(i)).substring(
                    String.valueOf(userPayMethods.get(i)).length()-5,String.valueOf(userPayMethods.get(i)).length()-1));
        }

        theCurrentBill = ConnectionManager.getCurrentBill(accountNumber);
        if(theCurrentBill.equals("")){
            currentBill.setText("No current bill.");
            hasCurrentBill = false;
        }
        else{
            if(ConnectionManager.isBillPaid(accountNumber)){
                currentBill.setText("No current bill.");
                hasCurrentBill = false;
            }
            else {
                currentBill.setText(theCurrentBill);
                hasCurrentBill = true;
            }
        }

        submitPayment.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(hasCurrentBill) {
                    if (!currentBill.getText().equals(paymentAmount.getText().trim())) {
                        JOptionPane.showMessageDialog(paymentFrame, "You must pay the full amount due!");
                    } else {
                        if (String.valueOf(comboBox1.getSelectedItem()).equals("-")) {
                            JOptionPane.showMessageDialog(paymentFrame, "You must choose a payment method!");
                        } else {
                            ConnectionManager.payBill(accountNumber);
                            JOptionPane.showMessageDialog(null, "Payment Successful!");
                            paymentFrame.dispose();
                            CustDashboard.getDash();
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(paymentFrame,"No current bill to pay!");
                }
            }
        });

        returnDashButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                paymentFrame.dispose();
                CustDashboard.getDash();
            }
        });

    }
}
