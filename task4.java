import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. View Balance");
        System.out.println("2. Make a Deposit");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Exit");
    }

    public void executeTransaction() {
        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            double amount;

            while (true) {
                showMenu();
                System.out.print("Select an option: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Your current balance is: Rs. " + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: Rs. ");
                        amount = scanner.nextDouble();
                        if (amount > 0) {
                            account.deposit(amount);
                            System.out.println("Deposit was successful.");
                        } else {
                            System.out.println("Invalid deposit amount.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: Rs. ");
                        amount = scanner.nextDouble();
                        if (amount > 0 && account.withdraw(amount)) {
                            System.out.println("Withdrawal was successful.");
                        } else {
                            System.out.println("Invalid amount or insufficient funds.");
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Have a nice day!");
                        return;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                }
            }
        }
    }
}

public class task4 {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Starting balance of Rs. 1000
        ATM atm = new ATM(userAccount);
        atm.executeTransaction();
    }
}
