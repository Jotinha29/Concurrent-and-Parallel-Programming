public class Shop {
    private final String name;
    private final Account account;
    private final Employee[] employees;
    private double cash;

    public Shop(String name, Account account, Employee[] employees) {
        this.name = name;
        this.account = account;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    void payEmployees() {
        System.out.println("Paying employees of shop " + name);
        synchronized (account) {
            for (Employee employee : employees) {
                if (account.getBalance() >= Employee.getSalary()) {
                    account.withdraw(Employee.getSalary());
                    System.out.println("Employee " + employee.getName() + " of shop " + name +
                            " received a salary of $" + Employee.getSalary());
                } else {
                    System.out.println("Insufficient balance to pay employee " + employee.getName() +
                            " of shop " + name);
                }
            }
            cash = 0; // Resetting the cash after paying employees
        }
    }
}
