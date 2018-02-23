package connection;

public class Queries {

    public static String userNameValid(String name){
        return "select u_name from users having u_name = '" + name + "'";
    }

    public static String emailValid(String email){
        return "select u_email from users having u_email = '" + email + "'";
    }

    public static String accountValid(String account){
        return "select u_accountnum from users having u_accountnum = '" + account + "'";
    }

    public static String addUser(String name, String pass, String email, String fname, String lname, String account){
        String sql = "insert into users(u_name,u_pass,u_email,u_fname,u_lname,u_accountnum) values ('" + name + "'" + ",'" + pass + "'" + ",'"
                + email + "'" + ",'" + fname + "'" + ",'" + lname + "'" + ",'" + account + "')";
        return sql;
    }

}
