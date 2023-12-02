import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingApplication {
    private static final int OPTION_EXIT = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount myAccount = new BankAccount();

        myAccount.displayWelcomeMessage();

        int choice;
        do {
            myAccount.displayMenu();
            choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    myAccount.createAccount(scanner);
                    break;
                case 2:
                    myAccount.deposit(scanner);
                    break;
                case 3:
                    myAccount.withdraw(scanner);
                    break;
                case 4:
                    myAccount.displayBalance();
                    break;
                case 5:
                    myAccount.displayTransactionHistory();
                    break;
                case 6:
                    myAccount.displayAccountDetails();
                    break;
                case OPTION_EXIT:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != OPTION_EXIT);

        scanner.close();
    }

    private static int getIntInput(Scanner scanner, String message) {
        int input = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(message);
                input = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!validInput);

        return input;
    }
}

class BankAccount {
    private String accountNumber;
    private String fullName;
    private String phoneNumber;
    private String emailAddress;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount() {
        this.transactionHistory = new ArrayList<>();
    }

    public void displayWelcomeMessage() {
        System.out.println("******************************************************");
        System.out.println("*              Welcome to Murex Banking              *");
        System.out.println("*        Your Gateway to Financial Excellence         *");
        System.out.println("******************************************************");
    }

    public void displayMenu() {
        System.out.println("\n1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Display Balance");
        System.out.println("5. Display Transaction History");
        System.out.println("6. Display Account Details");
        System.out.println("7. Exit");
    }

    public void createAccount(Scanner scanner) {
        System.out.print("Enter your full name: ");
        this.fullName = scanner.nextLine();
        System.out.print("Enter your phone number (+961): ");
        this.phoneNumber = "+961 " + getNumericInput(scanner, "");
        System.out.print("Enter your email address: ");
        this.emailAddress = scanner.nextLine();

        this.accountNumber = generateAccountNumber();

        System.out.println("Account created successfully. Account Number: " + accountNumber);
    }

    private String generateAccountNumber() {
        String[] nameParts = fullName.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }

        if (phoneNumber.length() >= 9) {
            return initials.toString().toUpperCase() + phoneNumber.substring(5, 9);
        } else {
            // Handle the error or throw an exception
            return "";
        }
    }

    public void deposit(Scanner scanner) {
        double amount = getDoubleInput(scanner, "Enter the deposit amount: ");

        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposit: +" + amount);
            System.out.println("Deposit successful. Current Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    public void withdraw(Scanner scanner) {
        double amount = getDoubleInput(scanner, "Enter the withdrawal amount: ");

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
            System.out.println("Withdrawal successful. Current Balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a valid amount.");
        }
    }

    public void displayBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Details:");
        System.out.println("Full Name: " + fullName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
    }

    private double getDoubleInput(Scanner scanner, String message) {
        double input = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(message);
                input = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!validInput);

        return input;
    }

    private String getNumericInput(Scanner scanner, String message) {
        String input = "";
        boolean validInput = false;

        do {
            try {
                System.out.print(message);
                input = scanner.nextLine();
                if (input.matches("\\d+")) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter numeric values only.");
                    System.out.print("Enter your phone number (+961): ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values only.");
                System.out.print("Enter your phone number (+961): ");
            }
        } while (!validInput);

        return input;
    }
}
