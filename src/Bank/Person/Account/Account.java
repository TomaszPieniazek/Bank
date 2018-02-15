package Bank.Person.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance = 0.00;
    private ArrayList<String> operationsHistory;

    public Account(String accountNumber) {
        if(accountNumber.length()==18) {
            this.accountNumber = accountNumber;
        }else throw new IllegalArgumentException("NUMER KONTA MA 18 CYFR");
        this.operationsHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<String> getOperationsHistory() {
        return operationsHistory;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void addToHistory(String s){
        Date d = new Date();
        this.operationsHistory.add(s +" | "+d );
    }
}
