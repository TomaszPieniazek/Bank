import Bank.Bank;
import Bank.Person.Account.Account;
import Bank.Person.Person;

import java.io.*;
import java.util.Date;

public class Main {

    public static void main(String [ ] args) throws InterruptedException {
        Bank b = new Bank();
        Date data;

        //PEOPLE
        Person p1 = new Person("Miralem", "Pjanic","11111111111","11/18/1995");
        Person p2 = new Person("Federico", "Bernardeschi","22222222222","11/11/1994");
        //ACCOUNTS
        Account a1 = new Account("111111111111111111");
        Account a2 = new Account("222222222222222222");
        Account a3 = new Account("333333333333333333");
        //ADDING PEOPLE TO BANK
        b.addPerson(p1);
        b.addPerson(p2);
      /*  //ADDING ACCOUNTS TO PEOPLE
        p1.addAccount(a1);
        p1.addAccount(a2);
        p2.addAccount(a3);
*/
      b.createAccount(p1,a1);
      b.createAccount(p1,a2);
      b.createAccount(p2,a3);

        p1.wplata("111111111111111111",111.11,"DAJE ");
        p1.wplata("222222222222222222",222.22,"DAJE ");
        Thread.sleep(1500);
        p2.wplata("333333333333333333",333.33,"DAJE ");

        p2.wyplata("333333333333333333",33.33,"ZABIERAM ");
        Thread.sleep(2000);
        p1.wyplata("111111111111111111",92.11,"ZABIERAM ");

        //HISTORIA OPERACJI CALEGO BANKU
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("historia_banku.txt", true)));
            data=new Date();
            out.println("HISTORIA Z DNIA: "+data);
            for (String object: b.getOperationsHistory()) {
                out.println(object);
            }
            out.close();
        } catch (IOException e) {
                e.printStackTrace();
        }

        //HISTORIA OPERACJI KLIENTA BANKU
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("historia_klienta.txt", true)));
            data=new Date();
            out.println("HISTORIA Z DNIA: "+data);
            for (String object: b.getPerson("11111111111").getOperationsHistory()) {
                out.println(object);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //HISTORIA OPERACJI POJEDYNCZEGO KONTA
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("historia_konta.txt", true)));
            data=new Date();
            out.println("HISTORIA Z DNIA: "+data);
            for (String object: b.getPerson("11111111111").getAccount("111111111111111111").getOperationsHistory()) {
                out.println(object);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
