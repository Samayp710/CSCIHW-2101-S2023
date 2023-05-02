package Project1;

import java.util.Scanner;
/*
 * Appas Bank
 * Bank Account Parameters:
 * public Name -> String ✓
 * private Balance -> double ✓ 
 * private Date of Birth -> String ✓
 * private SSN -> int (Maybe String) ✓
 * private Account Number -> int ✓
 * 
 * Bank Account Methods:
 * Constructor 
 * Getters and Setters
 * Deposit
 * Withdraw
 * Transfer (HomeWork)
 * Balance
 * Menu
 * Compound Interest
 * Simple Interest (HomeWork)
 * Authentication Basic
 * Authentication Advanced (HomeWork?)
 * 
 * Bank Account Constructor
 * Name, Balance, SSN, Account Number, Date of Birth
 * Name, SSN
 */

public class BankAccount {

    // Bank Account Parameters
    public String name;
    private double balance;
    private String dob;
    private int ssn;
    private int accountNumber;
    public double interestRate = 0.01;
    public int period = 4; // quarterly

    // Bank Account Constructor
    public BankAccount(String name, int ssn, double balance, int accountNumber, String dob) {
        this.name = name;
        this.ssn = ssn;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.dob = dob;
    }

    // Bank Account Constructor
    public BankAccount(String name, int ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    // Bank Account Methods

    // Note you do need to create a getter for public variables however it is best
    // practice to create a getter for all variables

    /*
     * Getters and Setters
     */

    public String getName() {
        return this.name;
    }

    public double getbalance() {
        return this.balance;
    }

    public String getdob() {
        return this.dob;
    }

    public int getssn() {
        return this.ssn;
    }

    public int getaccountNumber() {
        return this.accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * Should only be used by during the creation of the account never after instead
     * see deposit and withdraw
     */
    public void setbalance(double balance) {
        this.balance = balance;
    }

    public void setdob(String dob) {
        this.dob = dob;
    }

    public void setssn(int ssn) {
        this.ssn = ssn;
    }

    public void setaccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    // Create a mthod that will subtract a mothly fee from the balance
    // this method will take a double as a parameter
    // this method will subtract the fee from the balance
    // Netflix subscription is $12.99
    public void monthlyFee(double fee) {
        this.balance -= fee;
        withdraw(fee);

    }

    // Create a method that will print a menu to the user
    // This is called a helper method / function
    public static int menu() {
        int choice;
        Scanner input = new java.util.Scanner(System.in);
        System.out.println("Welcome to Appas Bank");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer"); // HomeWork
        System.out.println("4. Balance");
        System.out.println("5. Compound Interest");
        System.out.println("6. Simple Interest"); // HomeWork
        System.out.println("0. Exit");
        System.out.println("Please enter your choice: ");
        choice = input.nextInt();
        return choice;
    }

    public void printBalance() {
        System.out.println("Your balance is: $" + this.balance);
    }

    // Create a method that will interact with the user based on their choice from
    // the menu method
    // This method will take a Bank Account as a parameter
    public static void interact(BankAccount account, BankAccount account2) {
        // get the choice from the menu method
        int choice = menu();
        // create a scanner object
        Scanner input = new java.util.Scanner(System.in);
        // use that choice and run the method associated with that choice
        if (account.getbalance() >= 10000) {
            account.savingsAccount(account.getbalance(), 1, 0.0001, 4);
        }
        if (choice == 1) {
            System.out.println("Please enter the amount you would like to deposit: ");
            double amount = input.nextDouble();
            account.deposit(amount);
            account.printBalance();
        } else if (choice == 2) {
            System.out.println("How much would you like to withdraw?");
            double amount = input.nextDouble();
            account.overDraft(account, amount);
        
        } else if (choice == 3){
            System.out.println("How much would you like to transfer?");
            double amount = input.nextDouble();
            account.transferMoney(account, account2, amount);
        } else if (choice == 4) {
            account.printBalance();
        } else if (choice == 5) {
            System.out.println("How many years? (Whole numbers only)");
            int years = input.nextInt();
            account.compoundInterest(account.getbalance(), years, account.interestRate, account.period);
            account.printBalance();
        } else if (choice == 6){
            System.out.println("How many years? (Whole numbers only)");
            int years = input.nextInt();
            account.simpleInterest(account.getbalance(), years, account.interestRate);
        }
        else if (choice == 0) {
            System.out.println("Thank you for banking with Appas Bank");
        } else { // this would catch any invalid choices like
            System.out.println("Invalid choice");
        }

    }

    // Create a method that will calculate the interest on the balance using
    // compound interest
    // P(1 + R/n)^(nt) - P
    // A = P(1 + R/n)^(nt)
    // P = Principal
    // R = Rate
    // n = number of times interest is compounded per year (annum)
    // t = number of years
    // This method will take a double as an argument
    // This method will return a double

    // P can be this.balance or getBalance() if you would like to use a getter

    public void compoundInterest(double principal, int time, double rate, int annum) {
        double amount = principal * Math.pow(1 + (rate / annum), annum * time);
        double roundedAmount = Math.round(amount * 100.0) / 100.0;
        double compinterest = amount - principal; // A-P
        compinterest = Math.round(compinterest * 100.0) / 100;

        System.out.println("Current Rate is: " + rate + "%");
        System.out.println("Current Period is: " + annum + " times per year");
        System.out.println("Selected Time is: " + time + " years");
        System.out.println("Compound Interest after " + time + " years is: $" + compinterest);
        System.out.println("Total Amount after " + time + " years is: $" + roundedAmount);
    }

    /*Overdraft method
    The Overdraft Method should be used when I withdraw money
    and if I have $0 or less in my account it will charge me a
    fee of $35. It should also see if the amount withdrawn puts me
    in the negative and prompt the user if this is okay and warn them
    that they will be charged a fee.
    Hint 1: You will need a method with parameters for the bank account
    as well as the amount to withdraw
    Hint 2: You can use the withdraw method in the overdraft method or vice
    versa depending on your implementation.
    Hint 3: You will need to do some logic to check if the balance is less than
    0 or if the withdraw would bring my balance to negative.
        remember this is the format for if statements:
        if
        else if
        else if
        else
    Hint 4: Method should look something like overDraft(BankAccount bank, double value)*/
    public void overDraft(BankAccount bank, double value) {
        Scanner input = new java.util.Scanner(System.in);
        if (bank.getbalance() <= 0) {
            System.out.println("You are currently in the negative.");
            System.out.println("Would you like to continue? A fee of $35 will be charged. (Y/N)");
            String answer = input.next();
            if (answer.equalsIgnoreCase("Y")) {
                bank.withdraw(value + 35);
                bank.printBalance();
            } else if (answer.equalsIgnoreCase("N")) {
                System.out.println("Thank you for banking with Appas Bank");
            } else {
                System.out.println("Invalid choice");
            }
        } else {
            bank.withdraw(value);
            bank.printBalance();
        }
    }
    /*Create a method for transfer Money. The method should take in 2 bank account objects and an amount to transfer. You can assume the second account is Katara (or use the name of your choice)
    Hint 1. This will require you to take in 3 parameters at min
        Bank Account 1, Bank Account 2, Amount to transfer
    Hint 2. The math involved would be two folded.
         You need to subtract from one bank account and add to another
    Hint 3. We already have methods for withdrawing and depositing. Be sure to use them.
    Hint 4. You will need to add a second bank account in the interact method's parameters*/
    public void transferMoney(BankAccount bank1, BankAccount bank2, double value) {
        Scanner input = new java.util.Scanner(System.in);
        if (bank1.getbalance() <= 0) {
            System.out.println("You are currently in the negative.");
            System.out.println("Would you like to continue? A fee of $35 will be charged. (Y/N)");
            String answer = input.next();
            if (answer.equalsIgnoreCase("Y")) {
                bank1.withdraw(value + 35);
                bank2.deposit(value);
                bank1.printBalance();
                bank2.printBalance();
            } else if (answer.equalsIgnoreCase("N")) {
                System.out.println("Thank you for banking with Appas Bank");
            } else {
                System.out.println("Invalid choice");
            }
        } else {
            bank1.withdraw(value);
            bank2.deposit(value);
            bank1.printBalance();
            System.out.println("Their balance is: $" + bank2.getbalance());   
        }
    }
    /*Create a method to calculate simple interest.
    The formula to use is
    Simple Interest = (P × R × T)/100
    P is Principal amount.
    R is rate per annum. (: by the year : in or for each year : annually. per annum.)
    T is time in years.

    Make a return statement similar to the one for compound interest */
    public void simpleInterest(double principal, int time, double rate) {
        double amount = (principal * rate * time) / 100;
        double roundedAmount = Math.round(amount * 100.0) / 100.0;
        System.out.println("Current Rate is: " + rate + "%");
        System.out.println("Selected Time is: " + time + " years");
        System.out.println("Simple Interest after " + time + " years is: $" + roundedAmount);
        System.out.println("Total Amount after " + time + " years is: $" + (roundedAmount + principal));
    }
    /*Create a savings account option. If the user creates a bank account with 10,000 or more, prompt if they want to create a savings account. This will accept an inital value and a selection on compound or flat rate interest. The selection will
    then ask the user for the variables they would like for instance the Principal and years
    but with fixed amounts for the apy. For the compount the APY is 0.0001
    and for the simple interest account it is 0.001.
    NOTE: The value of n in compounded must be 4 for quarterly*/
    public void savingsAccount(double principal, int time, double rate, int annum) {
        Scanner input = new java.util.Scanner(System.in);
        System.out.println("You have $10,000 or more in your account, would you like to create a savings account? (Y/N)");
        String answer = input.next();
        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("Would you like to use compound interest or simple interest? (C/S)");
            String answer2 = input.next();
            if (answer2.equalsIgnoreCase("C")) {
                double amount = principal * Math.pow(1 + (rate / annum), annum * time);
                double roundedAmount = Math.round(amount * 100.0) / 100.0;
                double compinterest = amount - principal; // A-P
                compinterest = Math.round(compinterest * 100.0) / 100;
        
                System.out.println("Current Rate is: " + rate + "%");
                System.out.println("Current Period is: " + annum + " times per year");
                System.out.println("Selected Time is: " + time + " years");
                System.out.println("Compound Interest after " + time + " years is: $" + compinterest);
                System.out.println("Total Amount after " + time + " years is: $" + roundedAmount);
            } else if (answer2.equalsIgnoreCase("S")) {
                double amount = (principal * rate * time) / 100;
                double roundedAmount = Math.round(amount * 100.0) / 100.0;
                System.out.println("Current Rate is: " + rate + "%");
                System.out.println("Selected Time is: " + time + " years");
                System.out.println("Simple Interest after " + time + " years is: $" + roundedAmount);
                System.out.println("Total Amount after " + time + " years is: $" + (roundedAmount + principal));
            } else {
                System.out.println("Invalid choice");
            }
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.print("");
        } else {
            System.out.println("Invalid choice");
        }
    }
}
