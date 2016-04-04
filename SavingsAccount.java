package eecs285.proj3.gguarino;

/**
 * Created by gabrielleguarino on 11/2/15.
 */
public class SavingsAccount extends FinancialAccount
    implements InterestEarnable
{
    // interest is compounded daily for a whole month
    public double accrueInterest(int days)
    {
        double addedInterest = 0;
        double currentBalance = getBalance();
        double totalInterest = 0;

        for (int i = 0; i < days; i++)
        {
            // compound each daily interest
            // add it to total interest compounded for the whole month
            addedInterest = (1/getDaysInYear()) * getBalance() * (getInterestRate()/100);
            totalInterest = totalInterest + addedInterest;
            currentBalance = currentBalance + addedInterest;
            // add interest to current balance
            setBalance(currentBalance);
        }

        // return total interst of the month
        addTransaction("Interest Earned", totalInterest);
        return totalInterest;
    }

    SavingsAccount(int accountNumIn, double balanceIn, double interestRateIn)
    {
        setAccountNum(accountNumIn);
        setBalance(balanceIn);
        setInterestRate(interestRateIn);
        setType("Savings");
    }
}
