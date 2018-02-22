package connection;

import javax.swing.*;
import java.sql.*;

public class ConnectionManager {
    private static String url = "jdbc:mysql://localhost:3306/proj4db";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "user";
    private static String password = "1234";
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
            String sql = "select username,password from users where username = '"+ userName +"' and password = '"+ pass +"'";
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

}
