package org.claytantor.service;

import org.claytantor.model.IntpickerModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Created by claytongraham on 5/28/18.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
public class IntegerPickerServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Configuration
    static class Config {

        // this bean will be injected into the HashMakerService class,
        // we can inject mocks etc if we wish
        @Bean
        public IntegerPickerService integerPickerService() {
            IntegerPickerService service = new IntegerPickerService();
            // set properties, etc.
            return service;
        }
    }

    @Autowired
    private IntegerPickerService integerPickerService;

    /**
     * this is the happy path for evaluating hashing
     */
    @Test
    public void evaluatesHashing() {
        Integer[] intArray = { -5, 1, 5, 6, 10, 21 };
        Map result = integerPickerService.makeIntegerHash(Arrays.asList(intArray));
        assertEquals(0, result.get(-5));
        assertEquals(5, result.get(21));
    }

    /**
     * this is the weird negative tests for hashing
     */
    @Test
    public void evaluatesHashingNegative() {
        Integer[] intArray = { -22, 10, 5, 6, 1, 21 };
        Map result = integerPickerService.makeIntegerHash(Arrays.asList(intArray));
        assertNotEquals(1, result.get(-5));
        assertEquals(null, result.get(-5));
        assertNotEquals(3, result.get(-22));
    }

    @Test
    public void evaluatesPicker() {
        Integer[] intArray = { -5, 1, 5, 6, 10, 21 };
        try {
            IntpickerModel result = integerPickerService.pickIntegers(Arrays.asList(intArray), 2, 15);

            assertEquals(2, result.getItems().get(0).longValue());
            assertEquals(4, result.getItems().get(1).longValue());


        } catch (ServiceExcpetion serviceExcpetion) {
            logger.error("problem getting result from pickIntegers", serviceExcpetion);
            fail(String.format("there was a service exception for pickIntegers: %s", serviceExcpetion.getMessage()));
        } catch (Exception e) {
            logger.error("unknown error during service call to pickIntegers", e);
            fail(String.format("there was an exception pickIntegers: %s", e.getMessage()));
        }


    }


}
