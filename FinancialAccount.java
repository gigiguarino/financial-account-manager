package eecs285.proj3.gguarino;
import java.util.LinkedList;
import java.util.ListIterator;
import static java.lang.System.out;

/**
 * Created by gabrielleguarino on 11/2/15.
 */
public class FinancialAccount
{
    static private double daysInYear = 365;
    private int accountNum;
    private double balance;
    private double interestRate;
    private String type;
    private LinkedList< Transaction > transactions = new LinkedList< Transaction >();

    // type of transaction made to the account
    // interest earned
    // interest charged
    // payment made
    private class Transaction
    {
        private String type;
        private double amount;

        Transaction(String typeIn, double amountIn)
        {
            type = typeIn;
            amount = amountIn;
        }

        String getType()
        {
            return type;
        }

        double getAmount()
        {
            return amount;
        }
    }

    FinancialAccount()
    {
        accountNum = 0;
        balance = 0;
        interestRate = 0;
    }

    FinancialAccount(int accountNumIn, double balanceIn, double interestRateIn)
    {
        accountNum = accountNumIn;
        balance = balanceIn;
        interestRate = interestRateIn;
    }

    void setAccountNum(int accountNumIn)
    {
        accountNum = accountNumIn;
    }

    void setBalance(double balanceIn)
    {
        balance = balanceIn;
    }

    void setInterestRate(double interestRateIn)
    {
        interestRate = interestRateIn;
    }

    void setType(String typeIn)
    {
        type = typeIn;
    }

    double getDaysInYear()
    {
        return daysInYear;
    }

    int getAccountNum()
    {
        return accountNum;
    }

    double getBalance()
    {
        return balance;
    }

    double getInterestRate()
    {
        return interestRate;
    }

    String getType()
    {
        return type;
    }

    // add transaction to transaction list
    void addTransaction(String type, double amount)
    {
        Transaction t = new Transaction(type, amount);
        transactions.add(t);
    }

    // print contents of transaction list
    void printTransactionList()
    {
        ListIterator < Transaction > iter = transactions.listIterator();
        while (iter.hasNext())
        {
            Transaction t1 = iter.next();
            out.print("  Type: " + t1.getType());
            out.println(" Amount: " + t1.getAmount());
        }
    }

    // print account data
    void printAccountData()
    {
        out.print(type + " Account " + "#");
        out.print(accountNum + " Balance: ");
        out.println(balance);
    }
}
