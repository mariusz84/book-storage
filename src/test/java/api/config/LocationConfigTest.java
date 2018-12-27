package api.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LocationTestConfig.class)
public class LocationConfigTest {

    @Autowired
    private LocationConfig locationConfig;

    @Test
    public void testLocationConfigGetter() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(LocationConfig.class);
    }

    @Test
    public void testReadLocationValueFromYml() {
        assertThat(locationConfig.getLocation(), equalTo("location.result-page"));
    }
}