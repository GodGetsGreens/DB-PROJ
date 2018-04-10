package connection;


/**
 * Queries - This class holds several functions that uses the parameters passed to them to return a query statement that
 *          will query a database table(s) and return values sought after
 */
public class Queries {

    /**
     * Function that checks to see if a userName is already in use in the database
     * @param name - The userName in question
     * @return - The query to be passed to the database
     */
    public static String userNameValid(String name){
        return "select u_name from users having u_name = '" + name + "'";
    }

    /**
     * Function that checks to see if an email is already in use in the database
     * @param email - The email in question
     * @return - The query to be passed to the database
     */
    public static String emailValid(String email){
        return "select u_email from users having u_email = '" + email + "'";
    }

    /**
     * Function that checks to see if an account number is valid and not already in use in the database
     * @param account - The account number in question
     * @return - The query that is passed to the database
     */
    public static String accountValid(String account){
        return "select u_accountnum from users having u_accountnum = '" + account + "'";
    }

    /**
     * Function that is used to add new customer information into the database table of users
     * @param name - The username of the new customer
     * @param pass - The password of the new customer
     * @param email - The email of the new customer
     * @param fname - The first name of the new customer
     * @param lname - The last name of the new customer
     * @param account - The account number of the new customer
     * @return - The query that will be passed to the database, which will add a new user to the user table
     */
    public static String addUser(String name, String pass, String email, String fname, String lname, String account){
        String sql = "insert into users(u_name,u_pass,u_email,u_fname,u_lname,u_accountnum) values ('" + name + "'" + ",'" + pass + "'" + ",'"
                + email + "'" + ",'" + fname + "'" + ",'" + lname + "'" + ",'" + account + "')";
        return sql;
    }

    /**
     * Function that is used to get the account number for a certain customer
     * @param user - The username of the customer whose account number is sought
     * @return - The query that will be passed to the database, which will retrieve the customer account number
     */
    public static String getAccountNumber(String user){
        return "select u_accountnum from users where u_name = '" + user + "'";
    }

//    public static String addBill(String account, String bill, String duedate, String paid, String late){
//        String sql = "insert into current_bills(cb_account,cb_bill,cb_duedate,cb_paid,cb_late) values" +
//                "('"+account+"'"+",'"+Float.parseFloat(bill)+",'"+duedate+",'"+Integer.valueOf(paid)+",'"+Integer.valueOf(late)+"')";
//        return sql;
//    }

    public static String getCurrentBill(String accountNumber){
        return "select cb_bill from current_bills where cb_account = '"+accountNumber+"'";
    }

}
