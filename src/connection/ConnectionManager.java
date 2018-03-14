package connection;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.sql.*;

public class ConnectionManager {
    private static String url = "jdbc:mysql://localhost:3306/proj4";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "Zeppelin6894!";
    private static Connection con;
    private static Statement st;
    private static ResultSet rs;



    public static Connection getConnection() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());

        }

        return con;
    }

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

            if(rs.getFetchSize() > 0){
                return true;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return false;
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
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if(rs.getFetchSize() > 0)
                    return true;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return false;
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

            if(rs.getFetchSize() > 0)
                return true;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return false;
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

}
