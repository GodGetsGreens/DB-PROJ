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

    /**
     * Function that returns the sql query to get the current bill associated with the given accountnumber
     * @param accountNumber - The account number of the user whose current bill is sought
     * @return - The SQL query that will be passed to the database, which will retrieve the current bill if any
     */
    public static String getCurrentBill(String accountNumber){
        return "select cb_bill from current_bills where cb_account = '"+accountNumber+"'";
    }

    /**
     * Function that returns the SQL query to get the phone number associated with the given account number
     * @param accountNumber - The account number of the user whose phone number is sought
     * @return - The SQL query that will be passed to the database, which will retrieve the users phone number
     */
    public static String getUserPhone(String accountNumber){
        return "select ui_phone from user_information where ui_account = '"+accountNumber+"'";
    }

    /**
     * Function that returns the SQL query to get the service address associated with the given account number
     * @param accountNumber - The account number of the user whose service address is sought
     * @return - The SQL query that will be passed to the database, which will retrieve the users service address
     */
    public static String getUserAddress(String accountNumber){
        return "select ui_serviceaddress from user_information where ui_account = '"+accountNumber+"'";
    }

    /**
     * Function that returns the SQL query to get the email associated with the given account number
     * @param accountNumber - The account number of the user whose email is sought
     * @return - The SQL query thta will be passed to the database, which will retrieve the users email
     */
    public static String getUserEmail(String accountNumber){
        return "select ui_email from user_information where ui_account = '"+accountNumber+"'";
    }

    /**
     * Function that returns an SQL query to get the username associated with the given account number
     * @param accountNumber - The account number of the customer whose username is sought
     * @return - The SQL query that will be passed to the database, which will retrieve the customers username
     */
    public static String getUserName(String accountNumber){
        return "select u_name from users where u_accountnum = '" + accountNumber +"'";
    }

    public static String updatePhone(String newPhone, String account){
        return "update user_information set ui_phone = '"+newPhone+"' where ui_account = '"+account+"'";
    }

    public static String updateUsersUName(String newName, String account){
        return "update users set u_name = '"+newName+"' where u_accountnum = '"+account+"'";
    }

    public static String updateUsersUEmail(String newEmail, String account){
        return "update users set u_email = '"+newEmail+"' where u_accountnum = '"+account+"'";
    }

    public static String updateUserInfoUEmail(String newEmail, String account){
        return "update user_information set ui_email = '"+newEmail+"' where ui_account = '"+account+"'";
    }

    public static String updateUserPassword(String newPass, String account){
        return "update users set u_pass = '"+newPass+"' where u_accountnum = '"+account+"'";
    }

    public static String employeeExists(String eid, String pass){
        return "select e_fname from employee where e_id = '"+eid+"' and e_pass = '"+pass+"'";
    }

    public static String getEmployeeName(String id){
        return "select e_fname from employee where e_id = '"+id+"'";
    }

    public static String createCustomer(String account, String serviceAddress, String email, String phonePlaceHolder){
        return "insert into user_information(ui_account, ui_email, ui_phone, ui_serviceaddress)" +
                " values('"+account+"'"+",'"+email+"'"+",'"+phonePlaceHolder+"'"+",'"+serviceAddress+"')";
    }

    public static String getUserAccount(String account){
        return "select ui_account from user_information where ui_account = '"+account+"'";
    }

    public static String getBills(){
        return "select * from current_bills";
    }

    public static String updateLate(String account){
        return "update current_bills set cb_late = 1 where cb_account = '"+account+"'";
    }

}
