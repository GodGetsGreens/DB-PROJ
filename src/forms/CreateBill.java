/**
 * CreateBill.java contains all the source code for the CreateBill form
 *
 * Author: Riley Wells
 */

package forms;

import connection.ConnectionManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateBill {
    private JButton returnToEmployeeDashboardButton;
    private JButton submitNewBillButton;
    private JTextField billAccountField;
    private JTextField billAmountField;
    private JTextField billDateField;
    private JPanel billForm;

    private JFrame newBillFrame;

    public CreateBill(){

        newBillFrame = new JFrame("DB Energy Solutions");
        newBillFrame.setContentPane(billForm);
        newBillFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newBillFrame.pack();
        newBillFrame.setVisible(true);


        returnToEmployeeDashboardButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                newBillFrame.dispose();
                EmployeeDashboard.getFrame();
            }
        });

        submitNewBillButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if((billAccountField.getText().trim().equals("") || billAmountField.getText().trim().equals("") || billDateField.getText().trim().equals(""))){
                    JOptionPane.showMessageDialog(newBillFrame,"All fields must be filled out!");
                }
                else{

                    if(!ConnectionManager.accountExists(billAccountField.getText().trim())){
                        JOptionPane.showMessageDialog(newBillFrame,"The account number is not valid!");
                    }
                    else{
                        ConnectionManager.createNewBill(billAccountField.getText().trim(),billAmountField.getText().trim(),billDateField.getText().trim());
                        JOptionPane.showMessageDialog(newBillFrame,"Bill successfully created!");
                        newBillFrame.dispose();
                        EmployeeDashboard.getFrame();
                    }

                }
            }
        });

    }

}
