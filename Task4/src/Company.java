import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 * Created by Вика on 28.01.2016.
 */
public class Company {
    private String name;
    private int businessAccount;
    private BigDecimal budget;
    private ArrayList<String> exNames;
    private ArrayList<Integer> exAccounts;

    public Company(String newName, int newAccount) {
        name = new String(newName);
        businessAccount = newAccount;
        budget = new BigDecimal(0, new MathContext(2));
    }

    public Company(String newName, int newAccount, BigDecimal startBudget) {
        name = new String(newName);
        businessAccount = newAccount;
        budget = startBudget;
    }

    public void depositFunds(BigDecimal value) {
        budget = budget.add(value);
    }

    public boolean debitFunds(BigDecimal value) {
        if (budget.compareTo(value) >=0) {
            budget = budget.subtract(value);
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getBusinessAccount() {
        return businessAccount;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setName(String name) {
        name = name;
    }

    public void setBusinessAccount(int businessAccount) {
        businessAccount = businessAccount;
    }

}
