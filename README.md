# spring-test-concurrent-execution-problem

### Issue reproducer

1. Run `mvn clean test`. Or run `ConcurrentSampleTest` from IDEA diretly (100% reproducible).

2. Observe in results the following exception:

```
org.springframework.test.context.TestContextManager: Caught exception while allowing TestExecutionListener [org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@70ed52de] to prepare test instance [test.ConcurrentSampleTest@f42957]
org.springframework.beans.factory.BeanCreationException: Could not inject field: private business_logic.SomeOtherBean test.ConcurrentSampleTest.someOtherBean; nested exception is java.lang.IllegalStateException: The field private business_logic.SomeOtherBean test.ConcurrentSampleTest.someOtherBean cannot have an existing value
	at org.springframework.boot.test.mock.mockito.MockitoPostProcessor.inject(MockitoPostProcessor.java:364)
	at org.springframework.boot.test.mock.mockito.MockitoPostProcessor.inject(MockitoPostProcessor.java:352)
	at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.lambda$injectFields$0(MockitoTestExecutionListener.java:94)
	at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.postProcessFields(MockitoTestExecutionListener.java:115)
	at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.injectFields(MockitoTestExecutionListener.java:94)
	at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.prepareTestInstance(MockitoTestExecutionListener.java:61)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:244)
	at org.springframework.test.context.junit4.statements.RunPrepareTestInstanceCallbacks.evaluate(RunPrepareTestInstanceCallbacks.java:63)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
	at org.springframework.test.context.junit4.statements.SpringFailOnTimeout.evaluate(SpringFailOnTimeout.java:87)
	at org.springframework.test.context.junit4.statements.ProfileValueChecker.evaluate(ProfileValueChecker.java:103)
	at ice.bricks.exceptions.ExceptionUtils.runSafe(ExceptionUtils.java:34)
	at test_utils.ConcurrentTestStatement.lambda$evaluate$0(ConcurrentTestStatement.java:32)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: java.lang.IllegalStateException: The field private business_logic.SomeOtherBean test.ConcurrentSampleTest.someOtherBean cannot have an existing value
	at org.springframework.util.Assert.state(Assert.java:97)
	at org.springframework.boot.test.mock.mockito.MockitoPostProcessor.inject(MockitoPostProcessor.java:358)
	... 17 more
```

### Notes

Please consider the note left in `ConcurrentSampleTest`.
