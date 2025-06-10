package LLDProjects.ProxyDesign;

public class DatabaseImpl implements Database {

    public void addUser(String userType, String userName) {
        System.out.println("User with : " + userName + " added");
    }
}
