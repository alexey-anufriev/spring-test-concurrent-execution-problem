package test;

import business_logic.SomeBean;
import business_logic.SomeOtherBean;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import test_utils.ConcurrentSpringMethodRule;
import test_utils.ConcurrentTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        SomeBean.class,
        SomeOtherBean.class
})
public class ConcurrentSampleTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final ConcurrentSpringMethodRule springMethodRule = new ConcurrentSpringMethodRule();

    @MockBean
    private SomeBean someBean;

    // NOTE: test works only where there are two mocks, after commenting out this one the test will pass
    @MockBean
    private SomeOtherBean someOtherBean;

    @ConcurrentTest
    @Test
    public void concurrentTest() {
        when(this.someBean.getData()).thenReturn("some other data");
        assertEquals(this.someBean.getData(), "some other data");
    }

}
