package eecs285.proj3.gguarino;

/**
 * Created by gabrielleguarino on 11/2/15.
 */
public class CheckingAccount extends FinancialAccount
    implements InterestEarnable
{
    // interest is compounded monthly
    public double accrueInterest(int days)
    {
        double addedInterest;
        addedInterest = (days/getDaysInYear()) * getBalance() * (getInterestRate()/100);
        // change balance to include new interest
        setBalance(getBalance() + addedInterest);
        addTransaction("Interest Earned", addedInterest);
        return addedInterest;
    }

    CheckingAccount(int accountNumIn, double balanceIn, double interestRateIn)
    {
        setAccountNum(accountNumIn);
        setBalance(balanceIn);
        setInterestRate(interestRateIn);
        setType("Checking");
    }
}
