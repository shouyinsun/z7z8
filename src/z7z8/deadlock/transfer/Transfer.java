package z7z8.deadlock.transfer;

/**
 * 账户转账
 * author cash
 * create 2019-01-17-21:13
 **/
public class Transfer {
    public static void main(String[] args) {
        Account a=new Account();
        Account b=new Account();
        //两个账户同时互相转钱,锁定账户,就可能死锁
        new Thread(() -> {
            try {
                transferMoney(a, b, 10.0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ).start();

        new Thread(() -> {
            try {
                transferMoney(b, a, 20.0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ).start();

    }


    public static void transferMoney(Account fromAccount,
                                     Account toAccount,
                                     double amount)
            throws Exception {

        // 锁定汇账账户
        synchronized (fromAccount) {
            // 锁定来账账户
            Thread.sleep(1000);
            synchronized (toAccount) {
                // 判余额是否大于0
                if (fromAccount.getBalance()-amount < 0) {
                    throw new RuntimeException();
                } else {
                    System.out.println("done!");
                    // 汇账账户减钱
                    fromAccount.debit(amount);
                    // 来账账户增钱
                    toAccount.credit(amount);
                }
            }
        }
    }
}
class Account {
    private double balance=100.0;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void credit(double d){
        this.balance+=d;
    }

    public void debit(double d){
        this.balance-=d;
    }
}