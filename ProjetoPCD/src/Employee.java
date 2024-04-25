public class Employee extends Thread {
    private final String name;
    private final Account salaryAccount;
    private final Account investmentAccount;
    private static final double SALARY = 1400;

    public Employee(String name, Account salaryAccount, Account investmentAccount) {
        this.name = name;
        this.salaryAccount = salaryAccount;
        this.investmentAccount = investmentAccount;
    }

    @Override
    public void run() {
        synchronized (salaryAccount) {
            salaryAccount.deposit(SALARY);
            double investmentAmount = SALARY * 0.2;
            investmentAccount.deposit(investmentAmount);
            System.out.println(name + " received a salary of $" + SALARY + ". Invested $" + investmentAmount + " in the investment account.");
        }
    }

    public static double getSalary() {
        return SALARY;
    }
}
