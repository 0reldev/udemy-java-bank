import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        branches = new ArrayList<>();
    }

    public boolean addBranch(String branchName) {
        Branch branch = findBranch(branchName);
        if (branch == null) {
            branches.add(new Branch(branchName));
            return true;
        } else return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initialTransaction) {
        Branch branch = findBranch(branchName);
        if (null != branch) {
            return branch.newCustomer(customerName, initialTransaction);
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
        Branch branch = findBranch(branchName);
        if (null != branch) {
            return branch.addCustomerTransaction(customerName, transaction);
        }
        return false;
    }

    private Branch findBranch(String branchName) {
        for (Branch branch : branches) {
            if (branch.getName().equalsIgnoreCase(branchName)) return branch;
        }
        return null;
    }

    public boolean listCustomers(String branchName, boolean printTransactions) {
        Branch branch = findBranch(branchName);
        if (null != branch) {
                System.out.println("Customer details for branch " + branchName);
                int customerIndex = 1;
                for (Customer customer : branch.getCustomers()) {
                    System.out.printf("Customer: %s[%d]%n", customer.getName(), customerIndex++);
                    if (printTransactions) {
                        System.out.println("Transactions");
                        int transactionIndex = 1;
                        for (double transaction : customer.getTransactions()) {
                            System.out.printf("[%d]  Amount %.2f%n", transactionIndex++, transaction);
                        }
                    }
                }
            return true;
        }
        return false;
    }
}
