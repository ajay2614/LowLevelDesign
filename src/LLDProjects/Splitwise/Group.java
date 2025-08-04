package LLDProjects.Splitwise;

import java.util.*;

public class Group {
    String name;
    Map<String, UserSplitwise> userSplitwises = new LinkedHashMap<>();
    Map<UserSplitwise, Double> userTotalBalances = new HashMap<>();

    Group(String name) {
        this.name = name;
    }

    public void addUser(String name, UserSplitwise user) {
        userSplitwises.put(name, user);
        userTotalBalances.put(user, 0.0);
    }

    public void addTransaction(UserSplitwise paidBy, Map<UserSplitwise, Double> amounts, Double totalAmount) {
        for(Map.Entry<UserSplitwise, Double> entry : userTotalBalances.entrySet()) {
            UserSplitwise user = entry.getKey();
            Double currentBalance = entry.getValue();
            if(user.id.equals(paidBy.id)) {
                userTotalBalances.put(user, currentBalance + totalAmount);
            }
            for (Map.Entry<UserSplitwise, Double> amountEntry : amounts.entrySet()) {
                UserSplitwise userContribution = amountEntry.getKey();
                if(userContribution.id.equals(user.id)) {
                    Double contribution = amountEntry.getValue();
                    userTotalBalances.put(user, currentBalance - contribution);
                }
            }
        }
    }

    public Map<UserSplitwise, Map<UserSplitwise, Double>> getSettlementAmounts() {
        Map<UserSplitwise, Map<UserSplitwise, Double>> settlementMap = new HashMap<>();
        PriorityQueue<Map.Entry<UserSplitwise, Double>> debtors = new PriorityQueue<>(Comparator.comparingDouble(Map.Entry :: getValue));
        PriorityQueue<Map.Entry<UserSplitwise, Double>> creditors = new PriorityQueue<>((a,b) -> Double.compare(b.getValue(), a.getValue()));

        for(Map.Entry<UserSplitwise, Double> entry : userTotalBalances.entrySet()) {
            UserSplitwise user = entry.getKey();
            Double balance = entry.getValue();
            if(balance < -1e-6) {
                debtors.offer(entry);
            } else if(balance > 1e-6) {
                creditors.offer(entry);
            }
        }

        while (!debtors.isEmpty() && !creditors.isEmpty()) {
            Map.Entry<UserSplitwise, Double> debtorEntry = debtors.poll();
            Map.Entry<UserSplitwise, Double> creditorEntry = creditors.poll();

            UserSplitwise debtor = debtorEntry.getKey();
            Double amountOwed = -debtorEntry.getValue();

            UserSplitwise creditor = creditorEntry.getKey();
            Double amountCredited = creditorEntry.getValue();

            Double amountToSettle = Math.min(amountOwed, amountCredited);

            settlementMap.computeIfAbsent(debtor, k -> new HashMap<>()).put(creditor, amountToSettle);

            /**
             We are using 'amountToSettle' here to calculate the remainingCredit and remainingDebt
             because this is the exact amount that was actually settled between the creditor and the debtor.
             Even if amountCredited and amountOwed were originally equal (e.g., both 33.335),
             the actual settlement might be rounded during the transaction (e.g., to 33.33 due to currency or formatting constraints).
             If we now try to recompute the leftover using (amountCredited - amountOwed), it would misleadingly give 0.0,
             even though we only settled 33.33 and there is still a small amount (e.g., 0.005) left to be transacted.
             Since amountToSettle is the final, rounded amount that was actually processed,
             we must use it again here to get the accurate remaining balance that should be reinserted into the heap.
             This prevents silent losses or imbalances due to floating-point rounding issues in financial calculations.
            */

            double remainingCredit = amountCredited - amountToSettle;
            double remainingDebt = amountOwed - amountToSettle;

            // Only re-insert if balance is still significant
            //1e-6 is 0.000001 — a very small threshold used to ignore floating-point precision errors (also called an epsilon).
            if (remainingCredit > 1e-6) {
                creditors.offer(new AbstractMap.SimpleEntry<>(creditor, remainingCredit));
            }
            if (remainingDebt > 1e-6) {
                debtors.offer(new AbstractMap.SimpleEntry<>(debtor, -remainingDebt));
            }

        }
        return settlementMap;
    }





}
