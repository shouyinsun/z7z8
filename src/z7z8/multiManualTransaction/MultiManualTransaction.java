package z7z8.multiManualTransaction;

import java.util.Date;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author cash
 * @description 跨库事务
 * @date 2022/5/8 9:13 PM
 */
public class MultiManualTransaction {

    /**
     * db1事务模板
     */
    private TransactionTemplate transactionTemplate;

    private DataSourceTransactionManager dataSourceTransactionManager;

    public MultiManualTransaction(TransactionTemplate transactionTemplate,
        DataSourceTransactionManager dataSourceTransactionManager) {
        this.transactionTemplate = transactionTemplate;
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

    public void process() {
        //手动事务
        ManualTransactionManager manualTransactionManager = new ManualTransactionManager(dataSourceTransactionManager);
        transactionTemplate.execute(status -> {
            processOnDB1();
            //手动事务,放在最后一步,失败抛异常,transactionTemplate事务也回滚
            manualTransactionManager.manualTransaction(null, null, null,
                (a, b, c) -> processOnDB2(null, null, null));
            return true;
        });
    }

    /**
     * db1操作
     */
    private void processOnDB1() {
        //do something
    }

    /**
     * db2操作
     * @param l
     * @param s
     * @param d
     */
    private void processOnDB2(Long l, String s, Date d) {
        //do something
    }

}
