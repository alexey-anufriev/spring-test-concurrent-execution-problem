package test_utils;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

public class ConcurrentSpringMethodRule extends SpringMethodRule {

    @Override
    public Statement apply(Statement base, FrameworkMethod frameworkMethod, Object testInstance) {
        Statement statement = super.apply(base, frameworkMethod, testInstance);
        statement = new ConcurrentTestStatement(statement, frameworkMethod.getMethod());
        return statement;
    }

}
