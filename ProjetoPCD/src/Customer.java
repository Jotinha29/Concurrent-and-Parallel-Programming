public class Customer extends Thread {
    private final String name;
    private final Account account;
    private final Shop[] shops;
    private final Bank bank;

    public Customer(String name, Account account, Shop[] shops, Bank bank) {
        this.name = name;
        this.account = account;
        this.shops = shops;
        this.bank = bank;
    }

    @Override
    public void run() {
        // Shopping until the account balance is empty
        while (account.getBalance() > 0) {
            // Choosing a shop randomly
            Shop shop = shops[(int) (Math.random() * shops.length)];
            // Choosing the purchase amount randomly
            double purchaseAmount = Math.random() < 0.5 ? 100 : 200;

            // Making the purchase at the chosen shop
            synchronized (shop) {
                if (account.getBalance() >= purchaseAmount) {
                    bank.transfer(account, shop.getAccount(), purchaseAmount); // Transferring funds from customer's account to shop's account
                    System.out.println(name + " made a purchase of $" + purchaseAmount + " at " + shop.getName() +
                            ". Remaining balance: $" + account.getBalance());
                } else {
                    System.out.println("Insufficient balance for " + name + " to make a purchase of $" + purchaseAmount);
                    break;
                }
            }

            // Switching between shops
            try {
                Thread.sleep(100); // Waiting for some time before moving to the next shop
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Account of " + name + " ran out of balance. Ending shopping.");
    }
}
