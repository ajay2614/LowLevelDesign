package LLDProjects.ATMMachine;// Interfaces and States

interface ATMState {
    void insertCard(ATMContextNew context, ATMCardNew card);
    void enterPin(ATMContextNew context, int pin);
    void withdraw(ATMContextNew context, int amount);
    int checkBalance(ATMContextNew context);
    void ejectCard(ATMContextNew context);
}

class IdleStateNew implements ATMState {
    public void insertCard(ATMContextNew context, ATMCardNew card) {
        context.setCurrentCard(card);
        context.setState(new CardInsertedStateNew());
        System.out.println("Card inserted.");
    }
    public void enterPin(ATMContextNew context, int pin) {
        System.out.println("Insert card first.");
    }
    public void withdraw(ATMContextNew context, int amount) {
        System.out.println("Insert card first.");
    }
    public int checkBalance(ATMContextNew context) {
        System.out.println("Insert card first.");
        return -1;
    }
    public void ejectCard(ATMContextNew context) {
        System.out.println("No card inserted.");
    }
}

class CardInsertedStateNew implements ATMState {
    public void insertCard(ATMContextNew context, ATMCardNew card) {
        System.out.println("Card already inserted.");
    }
    public void enterPin(ATMContextNew context, int pin) {
        if (context.getCardPin().equals(pin)) {
            context.setState(new AuthenticatedStateNew());
            System.out.println("PIN correct. Authenticated.");
        } else {
            System.out.println("Incorrect PIN.");
        }
    }
    public void withdraw(ATMContextNew context, int amount) {
        System.out.println("Enter PIN first.");
    }
    public int checkBalance(ATMContextNew context) {
        System.out.println("Enter PIN first.");
        return -1;
    }
    public void ejectCard(ATMContextNew context) {
        context.setCurrentCard(null);
        context.setState(new IdleStateNew());
        System.out.println("Card ejected.");
    }
}

class AuthenticatedStateNew implements ATMState {
    public void insertCard(ATMContextNew context, ATMCardNew card) {
        System.out.println("Already authenticated.");
    }
    public void enterPin(ATMContextNew context, int pin) {
        System.out.println("Already authenticated.");
    }
    public void withdraw(ATMContextNew context, int amount) {
        System.out.println("Withdrawn: " + amount);
    }
    public int checkBalance(ATMContextNew context) {
        return 1000; // Mock balance
    }
    public void ejectCard(ATMContextNew context) {
        context.setCurrentCard(null);
        context.setState(new IdleStateNew());
        System.out.println("Card ejected. Logged out.");
    }
}

// Context and Model

class ATMContextNew {
    private ATMState currentState;
    private ATMCardNew currentCard;

    public ATMContextNew() {
        this.currentState = new IdleStateNew();
    }

    public void setState(ATMState state) {
        this.currentState = state;
    }

    public void setCurrentCard(ATMCardNew card) {
        this.currentCard = card;
    }

    public Integer getCardPin() {
        return 1234; // mock PIN
    }

    // Delegating to current state
    public void insertCard(ATMCardNew card) { currentState.insertCard(this, card); }
    public void enterPin(int pin) { currentState.enterPin(this, pin); }
    public void withdraw(int amount) { currentState.withdraw(this, amount); }
    public int checkBalance() { return currentState.checkBalance(this); }
    public void ejectCard() { currentState.ejectCard(this); }
}

class ATMCardNew {
    private String cardNumber;

    public ATMCardNew(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}

// Main Handler

public class ATMStatePatternDemo {
    public static void main(String[] args) {
        ATMContextNew atm = new ATMContextNew();
        ATMCardNew card = new ATMCardNew("5678-XXXX-XXXX-4321");

        atm.insertCard(card);
        atm.enterPin(1234);
        int balance = atm.checkBalance();
        System.out.println("Balance: " + balance);
        atm.withdraw(500);
        atm.ejectCard();
    }
}
