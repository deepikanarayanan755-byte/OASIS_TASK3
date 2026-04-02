import java.util.*;

// Main Class
public class ATMInterface {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankAccount account = new BankAccount("user123", "1234", 1000);
        ATMOperations atm = new ATMOperations(account);

        System.out.println("===== ATM MACHINE =====");

        System.out.print("Enter User ID: ");
        String id = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (account.login(id, pin)) {

            int choice;

            do {

                System.out.println("\n1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Choose option: ");
                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        atm.showHistory();
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double w = sc.nextDouble();
                        atm.withdraw(w);
                        break;

                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double d = sc.nextDouble();
                        atm.deposit(d);
                        break;

                    case 4:
                        System.out.print("Enter amount to transfer: ");
                        double t = sc.nextDouble();
                        atm.transfer(t);
                        break;

                    case 5:
                        System.out.println("Thank you for using ATM!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } while (choice != 5);

        } else {
            System.out.println("Invalid User ID or PIN");
        }

        sc.close();
    }
}

// Bank Account Class
class BankAccount {

    private String userId;
    private String pin;
    private double balance;

    public BankAccount(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean login(String id, String p) {
        return id.equals(userId) && p.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

// Transaction History Class
class TransactionHistory {

    ArrayList<String> history = new ArrayList<>();

    public void addTransaction(String t) {
        history.add(t);
    }

    public void show() {

        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {

            System.out.println("\nTransaction History:");

            for (String h : history) {
                System.out.println(h);
            }
        }
    }
}

// ATM Operations Class
class ATMOperations {

    BankAccount account;
    TransactionHistory history = new TransactionHistory();

    public ATMOperations(BankAccount account) {
        this.account = account;
    }

    public void deposit(double amount) {

        double bal = account.getBalance() + amount;
        account.setBalance(bal);

        history.addTransaction("Deposited: " + amount);
        System.out.println("Deposit successful");
        System.out.println("Balance: " + bal);
    }

    public void withdraw(double amount) {

        if (amount <= account.getBalance()) {

            double bal = account.getBalance() - amount;
            account.setBalance(bal);

            history.addTransaction("Withdrawn: " + amount);
            System.out.println("Withdrawal successful");
            System.out.println("Balance: " + bal);

        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void transfer(double amount) {

        if (amount <= account.getBalance()) {

            double bal = account.getBalance() - amount;
            account.setBalance(bal);

            history.addTransaction("Transferred: " + amount);
            System.out.println("Transfer successful");
            System.out.println("Balance: " + bal);

        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void showHistory() {
        history.show();
    }
}