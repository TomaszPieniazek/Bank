package Bank.Person;

import Bank.Bank;
import Bank.Person.Account.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private String pesel;
    private String birthDate;
    private ArrayList<Account> accounts;
    private ArrayList<String> operationsHistory;

    public Person(String firstName, String lastName, String pesel, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(pesel.length()==11) {
            this.pesel = pesel;
        }else throw new IllegalArgumentException("PESEL MA 11 CYFR");
        this.birthDate = birthDate;
        this.accounts = new ArrayList<>();
        this.operationsHistory= new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPESEL() {
        return pesel;
    }

    public void setPESEL(String PESEL) {
        this.pesel = PESEL;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<String> getOperationsHistory() {
        return operationsHistory;
    }
    public void addAccount(Account a){
        boolean isOne= true;

        for(int j=0; j<Bank.getClients().size();j++) {
                for (int i = 0; i < Bank.getClients().get(j).getAccounts().size(); i++) {
                    if (Bank.getClients().get(j).getAccounts().get(i).getAccountNumber() ==a.getAccountNumber()){
                        isOne = false;
                    }
                }
        }
        if(isOne==true){
            this.accounts.add(a);
        }else throw new IllegalArgumentException("Account number already exists");
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\n' +
                "lastName='" + lastName + '\n' +
                "pesel='" + pesel + '\n' +
                "birthDate='" + birthDate + '\n' +
                "accounts=" + accounts +
                '}';
    }

    //+ wpłata (na numer konta, kwota, tytuł wpłaty)
    //+ wypłata (na numer konta, kwota, tytuł wypłaty) jesli są środki finansowe na końcie
    //+ transfer międzynarodowy (na numer konta, kod SWIFT, tytuł transferu, nazwa banku) jeśli są środki finansowe na końcie
    public void wplata(String accountNumber, double amount, String title){
        boolean isAccount = false;
        for(int i=0; i< this.accounts.size();i++){
            if(this.accounts.get(i).getAccountNumber()==accountNumber){
                isAccount=true;
                this.accounts.get(i).setBalance(this.accounts.get(i).getBalance()+amount);
                Bank.addToHistory("TYTYL: "+title+" | TYP TRANSAKCJI : WPLATA NA KONTO -"+accountNumber+" | NA SUME: "+amount);
                this.addToHistory("TYTYL: "+title+" | TYP TRANSAKCJI : WPLATA NA KONTO -"+accountNumber+" | NA SUME: "+amount);
                this.accounts.get(i).addToHistory("TYTYL: "+title+" | TYP TRANSAKCJI : WPLATA NA KONTO -"+accountNumber+" | NA SUME: "+amount);
            }
        }
        if(isAccount==false) throw new IllegalArgumentException("NIE MA TAKIEGO KONTA");
    }

    public void wyplata(String accountNumber, double amount, String title){
        boolean isAccount = false;
        for(int i=0; i< this.accounts.size();i++){
            if(this.accounts.get(i).getAccountNumber()==accountNumber){
                isAccount=true;
                if(this.accounts.get(i).getBalance()>=amount) {
                    this.accounts.get(i).setBalance(this.accounts.get(i).getBalance() - amount);
                    Bank.addToHistory("TYTUL :"+title + " | TYP TRANSAKCJI: WYPLATA Z KONTA  -" + accountNumber + " | NA SUME: " + amount);
                    this.addToHistory("TYTUL :"+title + " | TYP TRANSAKCJI: WYPLATA Z KONTA  -"  + accountNumber + " | NA SUME: " + amount);
                    this.accounts.get(i).addToHistory("TYTUL :"+title + " | TYP TRANSAKCJI: WYPLATA Z KONTA -" + accountNumber + " | NA SUME: " + amount);
                }else throw new IllegalArgumentException("ZA MALO SRODKOW NA KONCIE");
                }
        }
        if(isAccount==false) throw new IllegalArgumentException("NIE MA TAKIEGO KONTA");
    }

    public void addToHistory(String s)
    {
        Date d = new Date();
        this.operationsHistory.add(s + " | "+d);
    }

    public Account getAccount(String p){
        Account acc=null;
        try{
            for(int i = 0; i<this.accounts.size();i++){
                if(this.accounts.get(i).getAccountNumber()==p){
                    acc=this.accounts.get(i);
                }
            }}catch (NullPointerException e){
            System.out.print("NPE! OSOBA NIE ZNALEZIONA!");
        }
        return acc;
    }
}
