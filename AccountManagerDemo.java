package eecs285.proj3.gguarino;

//Programmer: Andrew Morgan
//Date:       October 2015
//Purpose:    Develop some intitial test cases to demonstrate the
//            functionality being developed for EECS285 project 3
//            consisting of a simple financial account management
//            system.  The functionality developed on this
//            project involves multiple account types with
//            different account features.

import static java.lang.System.out;

import java.util.LinkedList;

public class AccountManagerDemo
{
  //These constants control the later tests where accounts
  //are handled over the months of (potentially) multiple years.
  //Set the END_YEAR to be less than the START_YEAR if you don't
  //want the flood of output that comes after the more simple
  //single-day tests at the beginning.
  public static final int [] DAYS_FOR_MONTHS = {31, 28, 31, 30, 31, 30,
                                                31, 31, 30, 31, 30, 31};
  public static final int START_YEAR = 2016;
  public static final int END_YEAR = 2018;
  
  public static void main(String [] args)
  {
    double institutionCash;
    double paymentMade;

    //------- THIS SECTION IS JUST SOME BASIC-TYPE TESTS -------
    SavingsAccount savingsAcct;
    double intPaidToClient;
    double intPaidByClient;
    CheckingAccount checkingAcct;
    AutoLoanAccount autoLoanAcct;
    
    institutionCash = 5000;
    
    //Make a savings account with account number 100, an initial
    //balance of 10000 and a yearly interest rate of 3.5%.
    savingsAcct = new SavingsAccount(100, 10000, 3.5);
    //Make a checking account with account number 200, an initial
    //balance of 40000 and a yearly interest rate of 2.0%.
    checkingAcct = new CheckingAccount(200, 40000, 2.0);
    //Make an auto loan account with account number 300, an initial
    //balance of 1000, a charged interest rate of 5.9%, where
    //the client makes a monthly payment of $515.58.
    autoLoanAcct = new AutoLoanAccount(300, 1000, 5.9, 515.58);

    //---Savings, single day
    out.println("SAVINGS - OUTGOING INTEREST");
    intPaidToClient = savingsAcct.accrueInterest(1);
    institutionCash -= intPaidToClient;
    out.println("After accruing " + intPaidToClient + " of interest, " +
                "balance is: " + savingsAcct.getBalance() + 
                " and our cash is: " + institutionCash);

    intPaidToClient = savingsAcct.accrueInterest(1);
    institutionCash -= intPaidToClient;
    out.println("After accruing " + intPaidToClient + " of interest, " +
        "balance is: " + savingsAcct.getBalance() + 
        " and our cash is: " + institutionCash);
    
    //---Checking, single day
    out.println("CHECKING - OUTGOING INTEREST");
    intPaidToClient = checkingAcct.accrueInterest(1);
    institutionCash -= intPaidToClient;
    out.println("After accruing " + intPaidToClient + " of interest, " +
                "balance is: " + checkingAcct.getBalance() + 
                " and our cash is: " + institutionCash);

    intPaidToClient = checkingAcct.accrueInterest(1);
    institutionCash -= intPaidToClient;
    out.println("After accruing " + intPaidToClient + " of interest, " +
        "balance is: " + checkingAcct.getBalance() + 
        " and our cash is: " + institutionCash);

    //---Auto Loan, single day
    out.println("AUTO LOAN - INCOMING INTEREST");
    intPaidByClient = autoLoanAcct.chargeInterest(1);
    institutionCash += intPaidByClient;
    out.println("After receiving " + intPaidByClient + " of interest, " +
                "balance is: " + autoLoanAcct.getBalance() + 
                " and our cash is: " + institutionCash);

    paymentMade = autoLoanAcct.receivePayment();
    out.println("A monthly payment was made of: " + paymentMade +
                " new balance is: " + autoLoanAcct.getBalance());

    intPaidByClient = autoLoanAcct.chargeInterest(1);
    institutionCash += intPaidByClient;
    out.println("After receiving " + intPaidByClient + " of interest, " +
                "balance is: " + autoLoanAcct.getBalance() + 
                " and our cash is: " + institutionCash);

    paymentMade = autoLoanAcct.receivePayment();
    out.println("A monthly payment was made of: " + paymentMade +
                " new balance is: " + autoLoanAcct.getBalance());

    intPaidByClient = autoLoanAcct.chargeInterest(1);
    institutionCash += intPaidByClient;
    out.println("After receiving " + intPaidByClient + " of interest, " +
                "balance is: " + autoLoanAcct.getBalance() + 
                " and our cash is: " + institutionCash);

    paymentMade = autoLoanAcct.receivePayment();
    out.println("A monthly payment was made of: " + paymentMade +
                " new balance is: " + autoLoanAcct.getBalance());

    //------------------- Additional Testing -----------------------
    LinkedList< FinancialAccount > acctList;
    double intAmt; //Amount of interest accrued or charged...
    
    acctList = new LinkedList< FinancialAccount >();

    institutionCash = 100000; //reset to a nice round number
    
    acctList.add(new SavingsAccount(618, 15000, 1.75));
    acctList.add(new AutoLoanAccount(998, 8000, 4.9, 950));
    acctList.add(new SavingsAccount(317, 87500, 2.5));
    acctList.add(new CheckingAccount(400, 5750, 1.0));
    acctList.add(new AutoLoanAccount(999, 24000, 5.9, 435));
    
    for (int curYear = START_YEAR; curYear <= END_YEAR; curYear++)
    {
      for (int monthInd = 0; monthInd < DAYS_FOR_MONTHS.length; monthInd++)
      {
        out.println();
        out.println("Before month " + (monthInd + 1) + "/" + curYear +
                    ", our cash balance is " + institutionCash);
        for (FinancialAccount curAcct : acctList)
        {
          curAcct.printAccountData();

          //Apply the misc. account features supported by our 
          //different accounts...
          if (curAcct instanceof MonthlyPayable)
          {
            paymentMade = ((MonthlyPayable)curAcct).receivePayment();
            //If the balance is now 0, and some payment was made,
            //then this means the loan balance was paid off just now.
            if (curAcct.getBalance() == 0 && paymentMade > 0)
            {
              out.println("  Auto-loan has been paid off this month!");
            }
          }
          
          if (curAcct instanceof InterestEarnable)
          {
            intAmt = ((InterestEarnable)curAcct).accrueInterest(
                                                   DAYS_FOR_MONTHS[monthInd]);
            institutionCash -= intAmt;
          }
          if (curAcct instanceof InterestChargeable)
          {
            intAmt = ((InterestChargeable)curAcct).chargeInterest(
                                                   DAYS_FOR_MONTHS[monthInd]);
            institutionCash += intAmt;
          }
        }
      }
    }
    
    out.println("Ending institution cash balance: " + institutionCash);

    out.println();
    
    //Print out some transaction lists for reference..
    out.println("Printing transactions for account at index 1:");
    acctList.get(1).printTransactionList();
    out.println();
    out.println("Printing transactions for account at index 2:");
    acctList.get(2).printTransactionList();

  }
}
