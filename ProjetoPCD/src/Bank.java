import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final Lock lock = new ReentrantLock();
    private double totalTransferred = 0;

    public void transfer(Account source, Account destination, double amount) {
        lock.lock();
        try {
            if (source.getBalance() >= amount) {
                source.withdraw(amount);
                destination.deposit(amount);
                totalTransferred += amount;
                System.out.println("Transfer of $" + amount + " from the account of " + source.getAccountHolder() +
                        " to the account of " + destination.getAccountHolder() + " completed successfully.");
            } else {
                System.out.println("Insufficient balance to transfer $" + amount + " from the account of " +
                        source.getAccountHolder() + " to the account of " + destination.getAccountHolder() + ".");
            }
        } finally {
            lock.unlock();
        }
    }

    public double getTotalTransferred() {
        return totalTransferred;
    }

    public void displayFinalBalances(Account[] accounts) {
        System.out.println("\nFinal Account Balances:");
        for (Account account : accounts) {
            System.out.println("Account of " + account.getAccountHolder() + ": $" + account.getBalance());
        }
    }
}
