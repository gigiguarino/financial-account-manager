package eecs285.proj3.gguarino;

/**
 * Created by gabrielleguarino on 11/2/15.
 */
public class AutoLoanAccount extends FinancialAccount
        implements InterestChargeable, MonthlyPayable
{
    private double monthlyPayment;

    // interest componded monthly
    // doesn't change balance
    // money just gets paid to the institution directly
    public double chargeInterest(int days)
    {
        double addedInterest = 0;

        if (getBalance() != 0)
        {
            addedInterest = (days/getDaysInYear()) * getBalance() * (getInterestRate()/100);
            addTransaction("Interest Charged", addedInterest);
        }

        // return added interest
        return addedInterest;
    }

    // monthly payment made to loan
    public double receivePayment()
    {
        double returnAmount = 0;

        if (getBalance() == 0)
        {
            // if the balance of the loan is already 0,
            // there is nothing left to pay
            return 0;
        }

        else if (getBalance() - monthlyPayment < 0)
        {
            // if their is more than the monthly payment left in the loan,
            // then you only pay that amount
            // can't have a negative loan balance

            addTransaction("Payment Made", getBalance());
            returnAmount = getBalance();
            setBalance(0);
            return returnAmount;
        }

        else
        {
            // pay the monthly amount on the loan
            setBalance(getBalance() - monthlyPayment);
            addTransaction("Payment Made", monthlyPayment);
            return monthlyPayment;
        }
    }

    AutoLoanAccount(int accountNumIn, double balanceIn, double interestRateIn, double monthlyPaymentIn)
    {
        setAccountNum(accountNumIn);
        setBalance(balanceIn);
        setInterestRate(interestRateIn);
        setType("Auto Loan");
        monthlyPayment = monthlyPaymentIn;
    }
}
