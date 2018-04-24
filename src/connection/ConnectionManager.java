/**
 * ConnectionManager.java
 *
 * Contains two main things:
 *
 *      1: Database connectivity
 *      2: Static functions to execute SQL queries
 *
 * All SQL queries are referenced from a static context from all other .java files, and are passed zero to multiple
 * parameters and retrieve, update, insert or delete information to/from the database
 *
 * Author(Lines 1 - 87): Justin Bramel
 *
 * Author: Riley Wells
 */

package connection;

import com.sun.codemodel.internal.JOp;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

import javax.swing.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class ConnectionManager {
    private static String url = "jdbc:mysql://localhost:3306/proj4";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "Riley6894!";
    private static Connection con;
    private static Statement st;
    private static ResultSet rs;


    /**
     * Constructor that connects to the database and returns the connection if it exists
     * @return - The connection to the database
     */
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());

        }

        return con;
    }

    /**
     * validateUser takes in a username and password and checks to see if the password is associated with the username
     * @param userName - The username the customer entered
     * @param pass - The password the customer entered
     * @return - The boolean value for whether or not the user entered the correct information or not
     */
    public static boolean validateUser(String userName, String pass) {
        getConnection();
        try {
            String sql = "select u_name,u_pass from users where u_name = '"+ userName +"' and u_pass = '"+ pass +"'";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            int count = 0;
            while(rs.next()) {
                count ++;
            }
            st.close();
            if(count == 1) {
                JOptionPane.showMessageDialog(null, "Access Granted");
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null, "Access Denied");
                return false;
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        return false;



    }

    /**
     * getName makes a connection to the database, and using a username, returns the first name of the user who has the
     * username in question
     * @param username - The username of the customer in question
     * @return - Either returns the first name of the customer, or an empty string if the database connection is unsuccessful
     */
    public static String getName(String username){
        getConnection();
        try{
            String sql = "select u_fname from users where u_name = '"+ username +"'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            String name = rs.getString("u_fname");
            st.close();
            return name;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * userNameExists makes a connection with the database, and a SQL query is created from the given potential username
     *
     * @param uname - the username in question
     * @return returns true or false, depending on whether the username is already taken
     */
    public static boolean userNameExists(String uname){
        getConnection();
        try{
            String sql = Queries.userNameValid(uname);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if(!rs.next()){
                return false;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return true;
    }

    /**
     * Does the same thing as the function above, but this time with an email account in question
     *
     * @param email - the email account in question
     * @return true or false depending on  whether the email is already in use
     */
    public static boolean emailExists(String email){
        getConnection();
        try{
            String sql = Queries.emailValid(email);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if(!rs.next()) {
                rs.close();
                st.close();
                con.close();
                return false;
            }

            rs.close();
            st.close();
            con.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return true;
    }

    /**
     * This function does the same thing as the two function above, but this time with an account number in question
     * @param account - the potential account number
     * @return true or false depending on whether the account number is already in use
     */
    public static boolean accountExists(String account){
        getConnection();
        try{
            String sql = Queries.accountValid(account);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if(!rs.next())
                return false;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return true;
    }

    /**
     * insertUser takes in a SQL query represented as a string, and the query is executed and committed to the database
     *
     * @param query - the SQL query containing all relevant information
     * @throws SQLException
     */
    public static void insertUser(String query) throws SQLException{
        getConnection();
        try {
            String sql = query;
            con.setAutoCommit(false);
            st = con.createStatement();
            st.executeUpdate(sql);
            con.commit();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
        finally{
            st.close();
            con.setAutoCommit(true);
        }
    }

    /**
     * getAccountNumber takes in a username and queries the database to return the account number associated with that username
     *
     * @param userName - The username of the customer for who the account number is sought
     * @return - Returns
     */
    public static String getAccountNumber(String userName){
        getConnection();
        try{
            String sql = Queries.getAccountNumber(userName);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            String account = rs.getString("u_accountnum");
            st.close();
            return account;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return "";
    }

    /**
     * getCurrentBill takes a users account number as the single argument and queries the current_bills database
     * table and returns the users current bill balance
     *
     * @param accountNumber
     * @return The current bill balance or an empty string if no balance exists
     */
    public static String getCurrentBill(String accountNumber){
        getConnection();
        try{
            String sql = Queries.getCurrentBill(accountNumber);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                String bill = rs.getString("cb_bill");
                rs.close();
                st.close();
                con.close();
                return bill;
            }
            rs.close();
            st.close();
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return "";
    }

    /**
     * getUserPhone takes a users account number and queries the user_information database table and returns the
     * phone number associated with that account number.
     *
     * @param accountNumber
     * @return The phone number for the user in question
     */
    public static String getUserPhone(String accountNumber){
        getConnection();
        try{
            String sql = Queries.getUserPhone(accountNumber);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            String phone = rs.getString("ui_phone");
            st.close();
            return phone;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

        return "";
    }

    /**
     * getUserEmail takes a users account number and queries the user_information database table and returns the
     * email address associated with that account number
     *
     * @param accountNumber
     * @return The users email address
     */
    public static String getUserEmail(String accountNumber){
        getConnection();
        String temp;
        try{
            String sql = Queries.getUserEmail(accountNumber);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                temp = rs.getString("ui_email");
                rs.close();
                st.close();
                con.close();
                return temp;
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }

        return "";
    }

    /**
     * getUserAddress takes a users account number and queries the user_information database table and returns the
     * service address for the associated account
     * @param accountNumber
     * @return The accounts service address
     */
    public static String getUserAddress(String accountNumber){
        getConnection();
        try{
            String sql = Queries.getUserAddress(accountNumber);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            String address = rs.getString("ui_serviceaddress");
            st.close();
            return address;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

        return "";
    }

    /**
     * getUserName takes in an account number and returns the username associated with that customer account
     * @param accountNumber - The customer account number
     * @return - An empty string if the account number does not exist, or the username if the account number exists
     */
    public static String getUserName(String accountNumber){
        getConnection();
        String uname = "";
        try{
            String sql = Queries.getUserName(accountNumber);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            uname = rs.getString("u_name");
            st.close();
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

        return uname;
    }

    /**
     * setUserPhone takes in a new phone number and a customer account number and updates the phone number associated with
     * that account
     * @param newPhone - The customer's new phone number
     * @param account - The customer's account number
     */
    public static void setUserPhone(String newPhone, String account){
        if(newPhone.equals(""))
            return;
        getConnection();
        try{
            String sql = Queries.updatePhone(newPhone,account);
            con.setAutoCommit(false);
            st = con.createStatement();
            st.executeUpdate(sql);
            con.commit();
            st.close();
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }

    /**
     * setUserName takes in a new username and a customer account number and updates the username IF the new username does
     * NOT exist
     * @param newName - The customer's new user name
     * @param account - The customer's account number
     */
    public static void setUserName(String newName, String account){

        if(newName.equals("")) // check and make sure the user entered a new username
            return;

        getConnection();

        try{

            if(!ConnectionManager.userNameExists(newName)){ // check and make sure the username does not already exist

                String sql1 = Queries.updateUsersUName(newName,account);

                con.setAutoCommit(false);
                st = con.createStatement();
                st.executeUpdate(sql1);
                con.commit();
                st.close();
                con.close();

            }
            else{

                con.close();

            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }

    /**
     * setUserEmail takes in a new email and a customers account number and updates the email associated with their account
     * IF the email is NOT already in use
     * @param newEmail - The customer's new email
     * @param account - The customer's account number
     */
    public static void setUserEmail(String newEmail, String account){

        if(newEmail.equals("")) // check and make sure the user entered a new email
            return;

        getConnection();

        try{

            if(!ConnectionManager.emailExists(newEmail)){ // check and make sure the new email isnt already in use

                String sql1 = Queries.updateUsersUEmail(newEmail,account);
                String sql2 = Queries.updateUserInfoUEmail(newEmail,account);

                con.setAutoCommit(false);
                st = con.createStatement();
                st.executeUpdate(sql1);
                con.commit();
                st.executeUpdate(sql2);
                con.commit();
                st.close();
                con.close();

            }
            else{

                con.close();

            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }

    /**
     * setUserPassword takes in a new password and a customers account number and updates the stored password for the customer
     * to the new password
     * @param newPass
     * @param account
     */
    public static void setUserPassword(String newPass, String account){

        if(newPass.equals("")) // check and make sure that the customer entered a new password
            return;

        getConnection();

        try{

            String sql = Queries.updateUserPassword(newPass,account);
            con.setAutoCommit(false);
            st = con.createStatement();
            st.executeUpdate(sql);
            con.commit();
            st.close();
            con.close();

        }
        catch (Exception e){

            JOptionPane.showMessageDialog(null,e.toString());

        }
    }

    /**
     * validateEmployee takes in an employee ID and a password and returns a boolean in regards to whether the password and
     * ID match in the employee database table
     * @param eid - The employee ID
     * @param pass - The employee password
     * @return - True if the password and ID match what is stored in the database and False otherwise
     */
    public static boolean validateEmployee(String eid, String pass){

        try{

            getConnection();
            String sql = Queries.employeeExists(eid,pass);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if(rs.next()){

                rs.close();
                st.close();
                con.close();
                return true;

            }

            rs.close();
            st.close();
            con.close();

        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null,e.toString());

        }
        return false;
    }

    /**
     * getEmployeeName takes in an employee ID and returns the first name of the employee
     * @param id - The employee ID
     * @return - The employee's first name if on file, otherwise just returns "Employee"
     */
    public static String getEmployeeName(String id){

        String name = "Employee";
        getConnection();

        try{

            String sql = Queries.getEmployeeName(id);
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if(rs.next()) {
                name = rs.getString(1);
            }

            rs.close();
            st.close();
            con.close();

        }
        catch (Exception e){

            JOptionPane.showMessageDialog(null,e.toString());

        }

        return name;
    }

    /**
     * createNewCustomer takes in an account number, customer email, placeholder phone number, and a service address so a new
     * customer can register with the application
     * @param account - The new customer account number
     * @param email - The new customer email
     * @param placeholderPhone - A placeholder phone number
     * @param serviceAddress - The new customer's service address
     * @return - True if the new customer information was successfully added, and False if the email or account number is already in use
     */
    public static boolean createNewCustomer(String account, String email, String placeholderPhone, String serviceAddress){

        getConnection();

        try{

            String sql = Queries.createCustomer(account,serviceAddress,email,placeholderPhone);
            String sql1 = Queries.getUserEmail(account);
            String sql2 = Queries.getUserAccount(account);


            con.setAutoCommit(false);
            st = con.createStatement();

            rs = st.executeQuery(sql1);
            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Email is already in use!");
                rs.close();
                st.close();
                con.close();
                return false;
            }
            rs.close();

            rs = st.executeQuery(sql2);
            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Account is already in use!");
                rs.close();
                st.close();
                con.close();
                return false;
            }
            rs.close();


            st.executeUpdate(sql);
            con.commit();
            st.close();
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

        return true;

    }

    /**
     * markLateBills uses the current date and checks ALL current bills, and if the current date is past the bill's
     * due date then it adds that account to a vector.
     *
     * After all bills have been checked, all accounts in the vector are passed to the updateLate function and the
     * late status of those bills is set to 1
     */
    public static void markLateBills(){

        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        getConnection();
        try{

            String get = Queries.getBills();

            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(get);

            Vector accounts = new Vector();

            while(rs.next()){
                if(currentDate.after(java.sql.Date.valueOf(rs.getString("cb_duedate")))){
                    if(currentDate.toString().equals(rs.getString("cb_duedate"))){
                        continue;
                    }
                    System.out.println(rs.getString("cb_duedate"));
                    System.out.println(currentDate);
                    System.out.println();
                    if(rs.getString("cb_late").equals("0")) {
                        accounts.addElement(rs.getString("cb_account"));
                    }
                }
            }

            for(int i = 0; i < accounts.size(); ++i){
                st.executeUpdate(Queries.updateLate(String.valueOf(accounts.get(i))));
            }

            con.commit();

            rs.close();
            st.close();
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

    }

//    public static void updateBillLate(String account){
//
//        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
//        String sql = Queries.getBillDueDate(account);
//        getConnection();
//
//        try{
//
//            boolean isLate = false;
//            con.setAutoCommit(false);
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//
//            if(rs.next()){
//                if(currentDate.after(java.sql.Date.valueOf(rs.getString("cb_duedate")))){
//                    if(currentDate.toString().equals(rs.getString("cb_duedate"))){
//                        isLate = false;
//                    }
//                    else {
//                        isLate = true;
//                    }
//                }
//            }
//
//            rs.close();
//            if(isLate) {
//                st.executeUpdate(Queries.updateLate(account));
//            }
//            con.commit();
//            st.close();
//            con.close();
//
//        }
//        catch (Exception e){
//            JOptionPane.showMessageDialog(null,e.toString());
//        }
//
//    }

    /**
     * getPaymentMethods takes in a customer account and returns a vector of all the payment options that are on file
     * for the given account
     * @param account - The customer's account number
     * @return - The vector that contains all the payment options that are on file for the given account
     */
    public static Vector getPaymentMethods(String account){

        Vector payMeth = new Vector();
        getConnection();

        try{

            String sql = Queries.getPaymentMethods(account);

            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                payMeth.addElement(rs.getString("pm_method"));
            }

            rs.close();
            st.close();
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

        return payMeth;

    }

    /**
     * payBill takes in a customer account number and sets the status of the current bill associated with that account to
     * paid
     * @param account - The customer's account number
     */
    public static void payBill(String account){

        getConnection();
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Vector billInfo = new Vector();

        try{

            String sql1 = Queries.getFullCurrentBill(account);
            String sql2 = Queries.payCurrentBill(account);

            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql1);

            if(rs.next()){
                billInfo.addElement(rs.getString("cb_bill"));
                billInfo.addElement(rs.getString("cb_duedate"));
                billInfo.addElement(rs.getString("cb_late"));
            }
            rs.close();

            st.executeUpdate(sql2);
            con.commit();

            st.executeUpdate(Queries.addAccountHistory(account,String.valueOf(billInfo.get(0)),String.valueOf(billInfo.get(1)),
                    String.valueOf(currentDate),String.valueOf(billInfo.get(2))));
            con.commit();

            st.close();
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

    }

    /**
     * isBillPaid takes in a customer account number and returns a boolean for whether the current bill associated with that
     * account has been paid
     * @param account - The customer's account number
     * @return - True if the current bill has been paid or False if the current bill hasn't been paid or doesn't exist
     */
    public static boolean isBillPaid(String account){

        getConnection();

        try{

            String sql = Queries.isBillPaid(account);

            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if(rs.next()){
                rs.close();
                st.close();
                con.close();
                return true;
            }

            rs.close();
            st.close();
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

        return false;

    }

    /**
     * getPastBills takes a customer account number and returns a vector of all the past bill information associated
     * with that account number
     * @param account - The customer's account number
     * @return - The vector that contains all the past bill information for the customer's account
     */
    public static Vector getPastBills(String account){

        getConnection();
        Vector history = new Vector();

        try{

            String sql = Queries.getPastBills(account);

            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                String due = rs.getString("ah_billdate");
                String paid = rs.getString("ah_paydate");
                String amount = rs.getString("ah_billamount");
                String late = rs.getString("ah_waslate");

                if(late.equals("1"))
                    late = "Late";
                else
                    late = "On Time";

                history.addElement(due + "  -  " + paid + "  -  " + amount + "  -  " + late);
            }

            rs.close();
            st.close();
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

        return history;

    }

    /**
     * removePaidBills deletes all current bills that have been paid already
     */
    public static void removePaidBills(){

        getConnection();

        try{

            String sql = Queries.deletePaidBills();

            con.setAutoCommit(false);
            st = con.createStatement();

            st.executeUpdate(sql);
            con.commit();
            st.close();
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

    }

    /**
     * createNewBill takes in a customer account number, new bill amount and new bill due date and inserts the new bill
     * information into the current_bills database table
     * @param account - The customer's account number
     * @param amount - The new bill amount
     * @param duedate - The new bill's due date
     */
    public static void createNewBill(String account, String amount, String duedate){

        getConnection();

        try{

            String sql = Queries.addBill(account,amount,duedate);

            con.setAutoCommit(false);
            st = con.createStatement();
            st.executeUpdate(sql);
            con.commit();

            st.close();
            con.close();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

    }

}