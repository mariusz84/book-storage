package api.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LocationConfigTest {

    @Test
    public void testLocationConfigGetter() {
        new BeanTester().testBean(LocationConfig.class);
    }
}