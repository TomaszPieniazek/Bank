package Bank;

import Bank.Person.Account.Account;
import Bank.Person.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bank {

    private static final String bankName="Bank Pieniazek";
    private static ArrayList<Person> clients = new ArrayList<>();
    private static ArrayList<String> operationsHistory= new ArrayList<>();

    public Bank() {

    }

    public void addPerson(Person p)  {
        boolean isOne= true;

        for(int i = 0; i<this.clients.size();i++) {
            if (this.clients.get(i).getPESEL()==(p.getPESEL())) {
                isOne=false;
            }
        }
        if(this.getClients().size()==0 || isOne==true){
            this.clients.add(p);
        }else throw new IllegalArgumentException("The person with following pesel already is our client");
    }

    public static List<Person> getClients() {
        return clients;
    }

    public List<String> getOperationsHistory() {
        return operationsHistory;
    }

    public Person getPerson(String p){
        Person per=null;
        try{
        for(int i = 0; i<clients.size();i++){
            if(clients.get(i).getPESEL()==p){
                per=clients.get(i);
            }
        }}catch (NullPointerException e){
            System.out.print("NPE! OSOBA NIE ZNALEZIONA!");
        }
        return per;
    }

    public static String getBankName() {
        return bankName;
    }

    public static void addToHistory(String s)
    {
        Date d = new Date();
        operationsHistory.add(s + " | "+d);
    }

    public void editPerson(String staryPesel, String firstName, String lastName, String newPesel, String birthDate) {
        this.getPerson(staryPesel).setFirstName(firstName);
        this.getPerson(staryPesel).setLastName(lastName);
        this.getPerson(staryPesel).setBirthDate(birthDate);
        if (newPesel.length() == 11) {
            for(int i = 0; i<this.clients.size();i++) {
                if (this.clients.get(i).getPESEL()==newPesel) {
                    throw new IllegalArgumentException("EDYCJA NIE UDANA - PODANY PESEL ISTNIEJE!");
                }
            }
            this.getPerson(staryPesel).setPESEL(newPesel);
        } else throw new IllegalArgumentException("PESEL MA 11 CYFR!");
    }

    public void createAccount(Person p, Account a) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getPESEL() == p.getPESEL()) {
                p.addAccount(a);
            }
        }
    }
}
