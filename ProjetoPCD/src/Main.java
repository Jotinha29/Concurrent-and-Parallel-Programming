public class Main {
    public static void main(String[] args) {
        // Creating the bank
        Bank bank = new Bank();

        // Creating shop accounts
        Account shopAccount1 = new Account("BackEnd - Tabacaria", 0);
        Account shopAccount2 = new Account("BateriX - Onde sua bateria ganha uma segunda vida", 0);

        // Creating shop employees
        Employee employee1Shop1 = new Employee("Jotinha", shopAccount1, null);
        Employee employee2Shop1 = new Employee("Litle Isis", shopAccount1, null);
        Employee employee1Shop2 = new Employee("Duda", shopAccount2, null);
        Employee employee2Shop2 = new Employee("Victor", shopAccount2, null);

        // Passing employees to shops
        Shop shop1 = new Shop("BackEnd - Tabacaria", shopAccount1, new Employee[]{employee1Shop1, employee2Shop1});
        Shop shop2 = new Shop("BateriX - Onde sua bateria ganha uma segunda vida", shopAccount2, new Employee[]{employee1Shop2, employee2Shop2});

        // Creating employee accounts
        Account employeeAccount1 = new Account("Jotinha - Salary", 0);
        Account employeeInvestmentAccount1 = new Account("Jotinha - Investment", 0);

        Account employeeAccount2 = new Account("Litle Isis - Salary", 0);
        Account employeeInvestmentAccount2 = new Account("Litle Isis - Investment", 0);

        Account employeeAccount3 = new Account("Duda - Salary", 0);
        Account employeeInvestmentAccount3 = new Account("Duda - Investment", 0);

        Account employeeAccount4 = new Account("Victor - Salary", 0);
        Account employeeInvestmentAccount4 = new Account("Victor - Investment", 0);

        // Creating customer accounts
        Account customerAccount1 = new Account("Simone", 1000);
        Customer customer1 = new Customer("Simone", customerAccount1, new Shop[]{shop1, shop2}, bank);

        Account customerAccount2 = new Account("Fabricio", 1000);
        Customer customer2 = new Customer("Fabricio", customerAccount2, new Shop[]{shop1, shop2}, bank);

        Account customerAccount3 = new Account("Eline", 1000);
        Customer customer3 = new Customer("Eline", customerAccount3, new Shop[]{shop1, shop2}, bank);

        Account customerAccount4 = new Account("Helena", 1000);
        Customer customer4 = new Customer("Helena", customerAccount4, new Shop[]{shop1, shop2}, bank);

        Account customerAccount5 = new Account("Tiago", 1000);
        Customer customer5 = new Customer("Tiago", customerAccount5, new Shop[]{shop1, shop2}, bank);

        // Starting customer threads
        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
        customer5.start();

        // Waiting for customer threads to finish
        try {
            customer1.join();
            customer2.join();
            customer3.join();
            customer4.join();
            customer5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Paying employees
        shop1.payEmployees();
        shop2.payEmployees();

        // Displaying final account balances
        Account[] accounts = {customerAccount1, customerAccount2, customerAccount3, customerAccount4, customerAccount5,
                employeeAccount1, employeeAccount2, employeeAccount3, employeeAccount4,
                employeeInvestmentAccount1, employeeInvestmentAccount2, employeeInvestmentAccount3,
                employeeInvestmentAccount4, shopAccount1, shopAccount2};
        bank.displayFinalBalances(accounts);
    }
}
