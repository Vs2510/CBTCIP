import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ATMInterface {
    private static double balance = 0;
    private static ArrayList<String> transactions = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM Interface");
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel balanceLabel = new JLabel("Balance: " + balance);
        balanceLabel.setBounds(10, 20, 80, 25);
        panel.add(balanceLabel);

        JTextArea transactionArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(transactionArea);
        scrollPane.setBounds(10, 60, 300, 150);
        panel.add(scrollPane);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 220, 80, 25);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 220, 100, 25);
        panel.add(withdrawButton);

        JButton transactionButton = new JButton("Transaction");
        transactionButton.setBounds(210, 220, 100, 25);
        panel.add(transactionButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(320, 220, 60, 25);
        panel.add(exitButton);

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(panel, "Enter amount to deposit:");
                if (input != null) {
                    double amount = Double.parseDouble(input);
                    balance += amount;
                    balanceLabel.setText("Balance: " + balance);
                    transactions.add("Deposit: " + amount);
                    updateTransactions(transactionArea);
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(panel, "Enter amount to withdraw:");
                if (input != null) {
                    double amount = Double.parseDouble(input);
                    if (balance >= amount) {
                        balance -= amount;
                        balanceLabel.setText("Balance: " + balance);
                        transactions.add("Withdraw: " + amount);
                        updateTransactions(transactionArea);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Insufficient balance.");
                    }
                }
            }
        });

        transactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder transactionHistory = new StringBuilder();
                for (String transaction : transactions) {
                    transactionHistory.append(transaction).append("\n");
                }
                JOptionPane.showMessageDialog(panel, transactionHistory.toString());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private static void updateTransactions(JTextArea transactionArea) {
        StringBuilder transactionHistory = new StringBuilder();
        for (String transaction : transactions) {
            transactionHistory.append(transaction).append("\n");
        }
        transactionArea.setText(transactionHistory.toString());
    }
}
