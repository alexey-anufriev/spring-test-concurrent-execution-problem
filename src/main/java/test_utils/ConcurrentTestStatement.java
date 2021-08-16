package test_utils;

import org.junit.runners.model.Statement;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ice.bricks.exceptions.ExceptionUtils.runSafe;

public class ConcurrentTestStatement extends Statement {

    private final Statement statement;
    private final Method testMethod;

    public ConcurrentTestStatement(Statement statement, Method testMethod) {
        this.statement = statement;
        this.testMethod = testMethod;
    }

    @Override
    public void evaluate() throws Throwable {
        if (this.testMethod.isAnnotationPresent(ConcurrentTest.class)) {
            ExecutorService executorService = Executors.newFixedThreadPool(3);

            List<Future<?>> results = IntStream.rangeClosed(1, 3)
                    .mapToObj(index -> executorService.submit(() -> runSafe(this.statement::evaluate)))
                    .collect(Collectors.toList());

            results.forEach(future -> runSafe(() -> future.get()));

            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        }
        else {
            this.statement.evaluate();
        }
    }

}
