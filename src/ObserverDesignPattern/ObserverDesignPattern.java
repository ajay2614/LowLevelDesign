package ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverDesignPattern {

    public static void main(String[] args) {
        EmailObserver emailObserver1 = new EmailObserver("abc123", "abc123@gmail.com");
        EmailObserver emailObserver2 = new EmailObserver("xyz123", "xyz123@gmail.com");
        HeadPhoneObservable headPhoneObservable = new HeadPhoneObservable();
        headPhoneObservable.add(emailObserver1);
        headPhoneObservable.add(emailObserver2);

        headPhoneObservable.setStockCount(20);

    }
}

/**
 * The Observer design pattern is a behavioral pattern that defines a
 * one-to-many dependency between objects. When one object (the subject)
 * changes state, all of its dependent objects (the observers) are
 * notified and updated automatically.
 *
 * This is particularly useful in situations where an object needs to
 * notify other objects about changes without knowing who or what
 * those objects are.
 *
 * In simple words:
 *
 * The Observer pattern is about managing a one-to-many relationship between objects.
 * The Observable object (the subject) keeps track of a list of Observer objects.
 * When the state of the Observable changes, it triggers an update on all the Observer
 * objects in the list.
 * Each Observer has an update() method, and when the Observable changes, it calls the
 * update() method on each Observer.
 */

/**
 * Design a notify process to customers, asked in walmart
 *
 */

interface Observable {

    public void add(Observer observer);
    public void delete(Observer observer);
    public void notifyCustomers();

}
interface Observer {
    public void update(String itemname);
}
class HeadPhoneObservable implements Observable {

    List<Observer> observerList = new ArrayList<>();
    int stockCount = 0;
    String itemname = "HeadPhones";
    public void add(Observer observer) {
        observerList.add(observer);
    }
    public void delete(Observer observer){
        observerList.remove(observer);
    }
    public void notifyCustomers() {
        for(Observer observer: observerList) {
            observer.update(itemname);
        }
    }

    public void setStockCount(int newStock) {
        if(stockCount == 0)
            notifyCustomers();
        stockCount += newStock;
    }
}

class EmailObserver implements Observer {

    String username;
    String email;

    EmailObserver(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public void update(String itemname) {
        sendNotification(itemname);
    }

    public void sendNotification(String itemname) {
        System.out.println(
                "Item " + itemname + " is in stock " +
                "Sent Notification to " + username + " on email : " +email
        );
    }
}
