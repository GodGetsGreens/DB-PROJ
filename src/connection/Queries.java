/**
 * Queries.java
 *
 * Contains static functions that return SQL statements used to retrieve, update, insert, and delete information from database tables
 *
 * Author: Riley Wells
 */

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


    /**
     * Function that returns an SQL query to update the phone number associated with the given account number
     * @param newPhone - The new phone number
     * @param account - The customer account
     * @return - The SQL query that will be passed to the database which will update the customers phone number
     */
    public static String updatePhone(String newPhone, String account){
        return "update user_information set ui_phone = '"+newPhone+"' where ui_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query to update a customers username for their account
     * @param newName - The customers new username
     * @param account - The customers account number
     * @return - The SQL query that will be passed to the database which will update the customers username
     */
    public static String updateUsersUName(String newName, String account){
        return "update users set u_name = '"+newName+"' where u_accountnum = '"+account+"'";
    }


    /**
     * Function that returns an SQL query to update a customers email for their account (for users table)
     * @param newEmail - The customers new email
     * @param account - The customers account number
     * @return - The SQL query that will be passed to the database which will update the customers email
     */
    public static String updateUsersUEmail(String newEmail, String account){
        return "update users set u_email = '"+newEmail+"' where u_accountnum = '"+account+"'";
    }


    /**
     * Function that returns an SQL query to update a customers email for their account (for user_information table)
     * @param newEmail - The customers new email
     * @param account - The customers account number
     * @return - The SQL query that will be passed to the database which will update the customers email
     */
    public static String updateUserInfoUEmail(String newEmail, String account){
        return "update user_information set ui_email = '"+newEmail+"' where ui_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query to update a customers password for their account
     * @param newPass - The customers new password
     * @param account - The customers account number
     * @return - The SQL query that will be passed to the database which will update the customers password
     */
    public static String updateUserPassword(String newPass, String account){
        return "update users set u_pass = '"+newPass+"' where u_accountnum = '"+account+"'";
    }


    /**
     * Function that returns an SQL query to check and see if an employee id and password match
     * @param eid - The employee's ID
     * @param pass - The employee's password
     * @return - The SQL query that will be passed to the database which will return the employee's first name if the ID and pass match what is in the database
     */
    public static String employeeExists(String eid, String pass){
        return "select e_fname from employee where e_id = '"+eid+"' and e_pass = '"+pass+"'";
    }


    /**
     * Function that returns an SQL query to get the first name for an employee
     * @param id - The employee's ID
     * @return - The SQL query that will be passed to the database which will return the first name of the employee
     */
    public static String getEmployeeName(String id){
        return "select e_fname from employee where e_id = '"+id+"'";
    }


    /**
     * Function that returns an SQL query that will be used to create a new customers' information in order to register
     * @param account - The new customers account number
     * @param serviceAddress - The new customers service address
     * @param email - The new customers email address
     * @param phonePlaceHolder - The placeholder phone number which will be replaced when a customer registers
     * @return - The SQL query that will be passed to the database which will create a new row in the user_information table
     */
    public static String createCustomer(String account, String serviceAddress, String email, String phonePlaceHolder){
        return "insert into user_information(ui_account, ui_email, ui_phone, ui_serviceaddress)" +
                " values('"+account+"'"+",'"+email+"'"+",'"+phonePlaceHolder+"'"+",'"+serviceAddress+"')";
    }


    /**
     * Function that returns an SQL query that will be used to check and see if a customer account number is already in use
     * @param account - The account number in question
     * @return - The SQL query that will return the customer account number if it exists
     */
    public static String getUserAccount(String account){
        return "select ui_account from user_information where ui_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query that will be used to return all current bill information stored in the database
     * @return - The SQL query that fetches all current bill information stored
     */
    public static String getBills(){
        return "select * from current_bills";
    }


    /**
     * Function that returns an SQL query that will be used to update the late status of a customers current bill
     * @param account - The customer account number
     * @return - The SQL query that updates the current bill for the given account number to late
     */
    public static String updateLate(String account){
        return "update current_bills set cb_late = 1 where cb_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query that will be used to check and see if a bill is late
     * @param account - The customer account number
     * @return - The SQL query that returns the late status of the current bill for a customer account
     */
    public static String isBillLate(String account){
        return "select cb_late from current_bills where cb_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query that will be used to get the due date of a customers current bill
     * @param account - The customer account number
     * @return - The SQL query that returns the due date of the customers current bill
     */
    public static String getBillDueDate(String account){
        return "select cb_duedate from current_bills where cb_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query that will be used to get the customers payment methods on file
     * @param account - The customer's account number
     * @return - The SQL query that returns the customers payment method on file
     */
    public static String getPaymentMethods(String account){
        return "select pm_method from payment_methods where pm_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query that is used to update the paid status of a customers current bill
     * @param account - The customer's account number
     * @return - The SQL query that updates the customers current bill status to PAID
     */
    public static String payCurrentBill(String account){
        return "update current_bills set cb_paid = 1 where cb_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query that is used to check and see if a customers current bill is paid
     * @param account - The customer's account number
     * @return - The SQL query that returns (SOMETHING) if a customers current bill is paid and nothing otherwise
     */
    public static String isBillPaid(String account){
        return "select * from current_bills where cb_account = '"+account+"' and cb_paid = 1";
    }


    /**
     * Function that returns an SQL query that is used to get the full current bill information for a customer
     * @param account - The customer's account number
     * @return - The SQL query that retrieves the current bill information for the customer
     */
    public static String getFullCurrentBill(String account){
        return "select * from current_bills where cb_account = '"+account+"'";
    }


    /**
     * Function that returns an SQL query that is used to remove ALL current bills that have been paid
     * @return - The SQL query that deletes all current bills that are paid from the current_bills database table
     */
    public static String deletePaidBills(){
        return "delete from current_bills where cb_paid = 1";
    }


    /**
     * Function that returns an SQL query that is used to insert new bill information that has been paid into the account_history table
     * @param account - The customer's account number
     * @param billAmount - The customers bill amount
     * @param dueDate - The customers bill due date
     * @param paidDate - The date that the customer paid their bill
     * @param wasLate - Whether or not the customer paid the bill late
     * @return - The SQL query that inserts paid bill information into the account_history table
     */
    public static String addAccountHistory(String account, String billAmount, String dueDate, String paidDate, String wasLate){
        return "insert into account_history(ah_accountnum,ah_billamount,ah_billdate,ah_paydate,ah_waslate) values" +
                "('"+account+"'"+",'"+billAmount+"'"+",'"+dueDate+"'"+",'"+paidDate+"'"+",'"+wasLate+"')";
    }


    /**
     * Function that returns an SQL query that is used to get all information for previous paid bills for a customer account
     * @param account - The customer's account number
     * @return - The SQL query that retrieves all previously paid bills for a certain customer account
     */
    public static String getPastBills(String account){
        return "select * from account_history where ah_accountnum = '"+account+"'";
    }


    /**
     * Function that returns an SQL query that is used to insert new bill information for a customer account
     * @param account - The customer's account number
     * @param amount - The new bill amount
     * @param duedate - The new bill's due date
     * @return - The SQL query that inserts the new bill information into the current_bills table
     */
    public static String addBill(String account, String amount, String duedate){
        return "insert into current_bills(cb_account,cb_bill,cb_duedate,cb_paid,cb_late) values('"+account+"'"+",'"+amount+"'"+",'"+duedate+"',0,0)";
    }

}
