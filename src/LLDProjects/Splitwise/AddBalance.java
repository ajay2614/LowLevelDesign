package LLDProjects.Splitwise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBalance {

    public void addEqualBalance(UserSplitwise paidBy, Double totalAmount, Group group) {
        Map<String, UserSplitwise> users = group.userSplitwises;
        Double amountPerUser = totalAmount/users.size();
        Map<UserSplitwise, Double> userSplitwiseMap = new HashMap();
        for(Map.Entry<String, UserSplitwise> entry : users.entrySet()) {
            UserSplitwise user = entry.getValue();
            userSplitwiseMap.put(user, amountPerUser);
        }
        group.addTransaction(paidBy, userSplitwiseMap, totalAmount);
    }
    public void addUnequalBalance(UserSplitwise paidBy, Double totalAmount, Group group, Map<UserSplitwise, Double> userContributions) {
        group.addTransaction(paidBy, userContributions, totalAmount);
    }
    public void addBalanceByPercentage(UserSplitwise paidBy, Double totalAmount, Group group, Map<UserSplitwise, Double> userContributions) {
        Map<UserSplitwise, Double> userSplitwiseMap = new HashMap<>();
        for (Map.Entry<UserSplitwise, Double> entry : userContributions.entrySet()) {
            UserSplitwise user = entry.getKey();
            Double percentage = entry.getValue();
            Double contribution = (percentage / 100) * totalAmount;
            userSplitwiseMap.put(user, contribution);
        }
        group.addTransaction(paidBy, userSplitwiseMap, totalAmount);
    }
}
