package LLDProjects.Splitwise;

import java.util.*;

public class SplitwiseController {


    public void createGroup(String groupName, String groupDescription, UserSplitwise createdBy) {
        // Logic to create a new group with the given name and description
        // and associate it with the user who created it.

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of group: ");
        String name = scanner.nextLine();
        Group group = new Group(name);
        System.out.println("Group created successfully with name: " + group.name);
    }

    public void addUserToGroup(Group group) {
        // Logic to add a user to the specified group
        System.out.println("Enter the name of user to be added: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Enter the email of user to be added: ");
        String userEmail = scanner.nextLine();
        Integer id = group.userSplitwises.size() + 1;
        UserSplitwise userSplitwise = new UserSplitwise(id, name, userEmail);
        group.addUser(name, userSplitwise);
        System.out.println("User " + userSplitwise.name + " added to group " + group.name);
    }

    public void addBalance(Group group) {
        // Logic to add balance to the group based on the type of balance (equal, unequal, percentage)

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the user who paid: ");
        String paidByName = scanner.nextLine();
        UserSplitwise paidBy = group.userSplitwises.get(paidByName);

        System.out.println("Enter the total amount paid: ");
        Double totalAmount = scanner.nextDouble();

        System.out.println("Enter the type of balance (equal, unequal, percentage): ");
        String balanceType = scanner.next();
        AddBalance addBalance = new AddBalance();

        switch (balanceType.toLowerCase()) {
            case "equal":
                addBalance.addEqualBalance(paidBy, totalAmount, group);
                break;
            case "unequal":
                Map<UserSplitwise, Double> userContributions = inputUserContributions(group, false, true, scanner);
                addBalance.addUnequalBalance(paidBy, totalAmount, group, userContributions);
                break;
            case "percentage":
                Map<UserSplitwise, Double> userContributionAverage = inputUserContributions(group, false, true, scanner);
                addBalance.addBalanceByPercentage(paidBy, totalAmount, group, userContributionAverage);
                break;
            default:
                System.out.println("Invalid balance type specified.");
        }
    }

    public Map<UserSplitwise, Double> inputUserContributions(Group group, boolean average, boolean unequal, Scanner scanner) {
        Map<String, UserSplitwise> users = group.userSplitwises;
        Map<UserSplitwise, Double> amountPerUser = new HashMap<>();
        for(Map.Entry<String, UserSplitwise> entry : users.entrySet()) {
            if(average) {
                System.out.println("Enter the average shared by: " + entry.getKey());
                Double averageForUser = scanner.nextDouble();
                amountPerUser.put(entry.getValue(), averageForUser);
            } else if (unequal) {
                System.out.println("Enter the amount shared by: " + entry.getKey());
                Double amountForUser = scanner.nextDouble();
                amountPerUser.put(entry.getValue(), amountForUser);
            }

        }
        return amountPerUser;
    }

    public  void settleUp(Group group) {
        Map<UserSplitwise, Map<UserSplitwise, Double>> balancesToSettle = new HashMap<>();
        balancesToSettle = group.getSettlementAmounts();

        for(Map.Entry<UserSplitwise, Map<UserSplitwise, Double>> userSplitwiseMapEntry : balancesToSettle.entrySet()) {
            UserSplitwise userToPay = userSplitwiseMapEntry.getKey();
            System.out.println(userToPay.name + " has to pay: ");
            for (Map.Entry<UserSplitwise, Double> amountToPayWhom : userSplitwiseMapEntry.getValue().entrySet()) {
                System.out.println(amountToPayWhom.getKey().name + " amount: " + amountToPayWhom.getValue());
            }
        }
    }
}

