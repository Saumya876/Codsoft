import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Account {
    private double balance;

    public Account(double initialBalance) {
        balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public void makeDeposit(double amount) {
        balance += amount;
    }

    public boolean makeWithdrawal(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATMInterface extends JFrame implements ActionListener {
    private Account bankAccount;
    private JTextField balanceDisplayField;
    private JTextField amountField;
    private JTextArea messageArea;

    public ATMInterface(Account account) {
        this.bankAccount = account;

        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel balanceLabel = new JLabel("Current Balance:");
        balanceDisplayField = new JTextField(Double.toString(bankAccount.checkBalance()));
        balanceDisplayField.setEditable(false);

        JLabel amountLabel = new JLabel("Transaction Amount:");
        amountField = new JTextField();

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);

        JButton depositButton = new JButton("Deposit Funds");
        depositButton.addActionListener(this);

        JButton withdrawButton = new JButton("Withdraw Funds");
        withdrawButton.addActionListener(this);

        messageArea = new JTextArea();
        messageArea.setEditable(false);

        panel.add(balanceLabel);
        panel.add(balanceDisplayField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(messageArea);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Check Balance")) {
            messageArea.setText("Your current balance is: Rs. " + bankAccount.checkBalance());
        } else if (e.getActionCommand().equals("Deposit Funds")) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    bankAccount.makeDeposit(amount);
                    balanceDisplayField.setText(Double.toString(bankAccount.checkBalance()));
                    messageArea.setText("Deposit successful.");
                } else {
                    messageArea.setText("Invalid deposit amount.");
                }
            } catch (NumberFormatException ex) {
                messageArea.setText("Invalid input. Please enter a valid amount.");
            }
        } else if (e.getActionCommand().equals("Withdraw Funds")) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0 && bankAccount.makeWithdrawal(amount)) {
                    balanceDisplayField.setText(Double.toString(bankAccount.checkBalance()));
                    messageArea.setText("Withdrawal successful.");
                } else {
                    messageArea.setText("Invalid withdrawal amount or insufficient balance.");
                }
            } catch (NumberFormatException ex) {
                messageArea.setText("Invalid input. Please enter a valid amount.");
            }
        }
    }
}

public class task4_GUI {
    public static void main(String[] args) {
        Account userAccount = new Account(1000.0);
        ATMInterface atmInterface = new ATMInterface(userAccount);
        atmInterface.setVisible(true);
    }
}
