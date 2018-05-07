package karthik.com.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by karthik on 5/8/18.
 */
public class CalculatorBrainTest {
    String keysEntered = "6+4/2X3-1";

    @Test
    public void isOperationAppendValid_emptyString_returnFalse() throws Exception {
        boolean result= CalculatorBrain.isOperationAppendValid("", "+");
        assertFalse(result);
    }

    @Test
    public void isOperationAppendValid_allowNegativeNumber() throws Exception {
        boolean result= CalculatorBrain.isOperationAppendValid("", "-");
        assertTrue(result);
    }

    @Test
    public void isOperationAppendValid_ReadyText_returnFalse() throws Exception {
        boolean result= CalculatorBrain.isOperationAppendValid("ready", "+");
        assertFalse(result);
    }

    @Test
    public void isOperationAppendValid_afterNumber_returnTrue() throws Exception {
        boolean result= CalculatorBrain.isOperationAppendValid(keysEntered, "/");
        assertTrue(result);
    }

    @Test
    public void performCalculation() throws Exception {
        String result = CalculatorBrain.performCalculation(keysEntered);
        assertEquals(String.valueOf(11), result);
    }

}