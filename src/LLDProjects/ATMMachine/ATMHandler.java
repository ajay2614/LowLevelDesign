package LLDProjects.ATMMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

enum ATMStatus {
    ATM_STATUS_IDLE,
    ATM_STATUS_CARD_INSERTED,
    ATM_STATUS_AUTHENTICATED
}

class ATMCard {
    private final int cardNumber;
    private final int pin;

    public ATMCard(int cardNumber, int pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }
}

class ATMMachine {

    ATMStatus atmStatus;
    private final HashMap<Integer, Integer> atmCardPins;
    private final HashMap<Integer, Integer> bankAccountBalances;

    public ATMMachine(HashMap<Integer, Integer> atmCardPins, HashMap<Integer, Integer> bankAccountBalances) {
        this.atmStatus = ATMStatus.ATM_STATUS_IDLE;
        this.atmCardPins = atmCardPins;
        this.bankAccountBalances = bankAccountBalances;
    }

    public boolean validateCard(ATMCard atmCard) {
        return atmCardPins.containsKey(atmCard.getCardNumber());
    }

    public boolean validatePin(int enteredPin, int actualPin) {
        return actualPin == enteredPin;
    }

    public int checkBalance(int cardNumber) {
        return bankAccountBalances.getOrDefault(cardNumber, 0);
    }

    public boolean withdrawAmount(int amount, int cardNumber) {
        int currentBalance = bankAccountBalances.getOrDefault(cardNumber, 0);
        if (currentBalance < amount) {
            return false;
        }
        bankAccountBalances.put(cardNumber, currentBalance - amount);
        return true;
    }
}

public class ATMHandler {
    private ATMMachine atmMachine;

    public ATMHandler(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
        atmMachine.atmStatus = ATMStatus.ATM_STATUS_IDLE;
    }

    public void insertCard(ATMCard card) {
        if (!atmMachine.validateCard(card)) {
            System.out.println("Invalid card. Please insert correct card.");
            return;
        }

        atmMachine.atmStatus = ATMStatus.ATM_STATUS_CARD_INSERTED;
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        boolean authenticated = false;

        while (attempts < 3) {
            System.out.print("Please Enter Your ATM PIN: ");
            int pin = scanner.nextInt();
            if (atmMachine.validatePin(pin, card.getPin())) {
                authenticated = true;
                break;
            }
            System.out.println("Incorrect PIN.");
            attempts++;
        }

        if (!authenticated) {
            System.out.println("Authentication failed. Please try again later.");
            atmMachine.atmStatus = ATMStatus.ATM_STATUS_IDLE;
            return;
        }

        atmMachine.atmStatus = ATMStatus.ATM_STATUS_AUTHENTICATED;
        sessionOps(scanner, card);
    }

    private void sessionOps(Scanner scanner, ATMCard card) {
        if(atmMachine.atmStatus != ATMStatus.ATM_STATUS_AUTHENTICATED) {
            System.out.println("ATM is not authenticated. Please insert card again.");
            return;
        }
        int attempts = 0;
        boolean exit = false;

        while (attempts < 3 && !exit) {
            System.out.println("\n1. Check Balance\n2. Withdraw\n3. Exit");
            System.out.print("Select Option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    int balance = atmMachine.checkBalance(card.getCardNumber());
                    System.out.println("Your current balance is: ₹" + balance);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    int amount = scanner.nextInt();
                    boolean success = atmMachine.withdrawAmount(amount, card.getCardNumber());
                    if (success) {
                        System.out.println("Withdrawal successful. New balance: ₹" + atmMachine.checkBalance(card.getCardNumber()));
                    } else {
                        System.out.println("Insufficient balance. Withdrawal failed.");
                    }
                }
                case 3 -> {
                    atmMachine.atmStatus = ATMStatus.ATM_STATUS_IDLE;
                    exit = true;
                }
                default -> System.out.println("Invalid option.");
            }

            attempts++;
        }

        System.out.println("Transaction Completed. Transaction Id: " + UUID.randomUUID());
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> atmCardPins = new HashMap<>();
        atmCardPins.put(1234, 1111);
        atmCardPins.put(5678, 2222);

        HashMap<Integer, Integer> balances = new HashMap<>();
        balances.put(1234, 1000);
        balances.put(5678, 500);

        ATMMachine atmMachine = new ATMMachine(atmCardPins, balances);
        ATMHandler atmHandler = new ATMHandler(atmMachine);

        ATMCard card = new ATMCard(1234, 1111);  // Simulating card input
        atmHandler.insertCard(card);
    }
}
