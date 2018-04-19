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
                st.close();
                return bill;
            }
            st.close();
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

    public static void setUserName(String newName, String account){
        if(newName.equals(""))
            return;
        getConnection();
        try{
            if(!ConnectionManager.userNameExists(newName)){
                String sql1 = Queries.updateUsersUName(newName,account);
//                String sql2 = Queries.updateUserInfoUName(newName,account);

                con.setAutoCommit(false);
                st = con.createStatement();
                st.executeUpdate(sql1);
                con.commit();
//                st.executeUpdate(sql2);
//                con.commit();
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

    public static void setUserEmail(String newEmail, String account){
        if(newEmail.equals(""))
            return;
        getConnection();
        try{
            if(!ConnectionManager.emailExists(newEmail)){
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

    public static void setUserPassword(String newPass, String account){
        if(newPass.equals(""))
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

}