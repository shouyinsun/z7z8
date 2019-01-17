package z7z8.deadlock.transfer;

/**
 * 账户转账 修正
 * 根据hash值,确定锁的先后顺序
 * author cash
 * create 2019-01-17-21:46
 **/
public class Amend {

    // 额外的锁、避免两个对象hash值相等的情况(即使很少)
    private static final Object tieLock = new Object();

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

    public static void transferMoney(final Account fromAcct,
                              final Account toAcct,
                              final double amount)
            throws Exception {

        class Helper {
            public void transfer() throws Exception {
                if (fromAcct.getBalance()-amount < 0)
                    throw new RuntimeException();
                else {
                    System.out.println("done");
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }
        // 得到锁的hash值
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        // 根据hash值来上锁
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                Thread.sleep(1000);
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }

        } else if (fromHash > toHash) {// 根据hash值来上锁
            synchronized (toAcct) {
                Thread.sleep(1000);
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {// 额外的锁、避免两个对象hash值相等的情况(即使很少)
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

}

