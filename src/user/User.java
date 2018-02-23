package user;

public class User {

    private String userName;
    private String accountNumber;
    private boolean loggedIn = false;

    public User(){
        userName = "";
        accountNumber = "";
    }

    public String getUserName(){
        return userName;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setUserName(String name){
        userName = name;
        loggedIn = true;
    }

    public void setAccountNumber(String account){
        accountNumber = account;
    }

}
