package z7z8.multiManualTransaction;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author cash
 * @description 手动事务
 * @date 2022/5/8 8:52 PM
 */
@Slf4j
public class ManualTransactionManager {

    private DataSourceTransactionManager dataSourceTransactionManager;

    public ManualTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

    @FunctionalInterface
    public interface TripleConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    public <A, B, C>  void manualTransaction(A a, B b, C c, TripleConsumer<A, B, C> tripleConsumer) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        //传播行为
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //隔离级别
        definition.setIsolationLevel(Isolation.DEFAULT.value());

        TransactionStatus status = dataSourceTransactionManager.getTransaction(definition);

        try {
            tripleConsumer.accept(a, b, c);
            dataSourceTransactionManager.commit(status);

        } catch (Throwable t) {
            log.error("manualTransaction exception", t);
            dataSourceTransactionManager.rollback(status);
            throw t;
        }
    }

    public <A, B> void manualTransaction(A a, B b, BiConsumer<A, B> biConsumer) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        definition.setIsolationLevel(Isolation.DEFAULT.value());

        TransactionStatus status = dataSourceTransactionManager.getTransaction(definition);

        try {
            biConsumer.accept(a, b);
            dataSourceTransactionManager.commit(status);

        } catch (Throwable t) {
            log.error("manualTransaction exception", t);
            dataSourceTransactionManager.rollback(status);
            throw t;
        }
    }

    public <A, B> void manualTransaction(A a, List<B> b, BiConsumer<A, List<B>> biConsumer) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        definition.setIsolationLevel(Isolation.DEFAULT.value());

        TransactionStatus status = dataSourceTransactionManager.getTransaction(definition);

        try {
            biConsumer.accept(a, b);
            dataSourceTransactionManager.commit(status);

        } catch (Throwable t) {
            log.error("manualTransaction exception", t);
            dataSourceTransactionManager.rollback(status);
            throw t;
        }
    }

    /**
     * 通用方法-无返回值
     *
     * @param a
     * @param consumer
     * @param <A>
     */
    public <A> void manualTransaction(A a, Consumer<A> consumer) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        definition.setIsolationLevel(Isolation.DEFAULT.value());

        TransactionStatus status = dataSourceTransactionManager.getTransaction(definition);

        try {
            consumer.accept(a);
            dataSourceTransactionManager.commit(status);

        } catch (Throwable t) {
            log.error("manualTransaction exception", t);
            dataSourceTransactionManager.rollback(status);
            throw t;
        }
    }

    /**
     * 通用方法-有返回值
     *
     * @param t
     * @param function
     * @return
     */
    public <T, R> R commonManualTransaction(T t, Function<T, R> function) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        definition.setIsolationLevel(Isolation.DEFAULT.value());

        TransactionStatus status = dataSourceTransactionManager.getTransaction(definition);

        try {
            R r = function.apply(t);
            dataSourceTransactionManager.commit(status);
            return r;
        } catch (Throwable e) {
            log.error("manualTransaction exception:", e);
            dataSourceTransactionManager.rollback(status);
            throw e;
        }
    }
}
