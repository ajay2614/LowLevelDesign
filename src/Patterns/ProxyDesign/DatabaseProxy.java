package Patterns.ProxyDesign;

public class DatabaseProxy implements Database{

    @Override
    public void addUser(String userType, String userName) {

        if(userType.equals("ADMIN")) {
            DatabaseImpl database = new DatabaseImpl();
            database.addUser(userType, userName);
        }
        else {
            System.out.println("Incorrect user type");
        }
    }

    public static void main(String[] args) {
        Database database = new DatabaseProxy();
        database.addUser("ADMIN", "name");
    }
}
