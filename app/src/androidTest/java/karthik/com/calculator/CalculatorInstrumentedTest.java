package karthik.com.calculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import android.support.test.rule.ActivityTestRule;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorInstrumentedTest {
    @Rule
    public final ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void defaultValue() {
        checkResult("Ready");
    }

    @Test
    public void addDigits() {
        press(R.id.button1);
        press(R.id.button2);
        press(R.id.button3);
        press(R.id.button4);
        press(R.id.button5);
        press(R.id.button6);
        press(R.id.button7);
        press(R.id.button8);
        press(R.id.button9);
        press(R.id.button0);
        press(R.id.button1);
        press(R.id.button2);
        checkResult("1234567890");
    }

    @Test
    public void additionTest() {
        press(R.id.button2);
        press(R.id.button5);
        press(R.id.buttonAdd);
        press(R.id.button6);
        press(R.id.buttonEqual);
        checkResult("31");
        press(R.id.buttonEqual);
        checkResult("31");
    }

    @Test
    public void subtractionTest() {
        press(R.id.button7);
        press(R.id.button8);
        press(R.id.buttonSub);
        press(R.id.button3);
        press(R.id.buttonEqual);
        checkResult("75");
    }

    @Test
    public void multiplyTest() {
        press(R.id.button2);
        press(R.id.buttonMul);
        press(R.id.button4);
        press(R.id.buttonEqual);
        checkResult("8");
    }

    @Test
    public void divisionTest() {
        press(R.id.button1);
        press(R.id.button0);
        press(R.id.buttonDiv);
        press(R.id.button4);
        press(R.id.buttonEqual);
        checkResult("2.5");
    }

    @Test
    public void divisionByZeroTest() {
        press(R.id.button8);
        press(R.id.buttonDiv);
        press(R.id.button0);
        press(R.id.buttonEqual);
        checkResult("0");
    }
    
    @Test
    public void clearTest() {
        press(R.id.button2);
        press(R.id.button5);
        press(R.id.button7);
        checkResult("257");
        press(R.id.buttonClear);
        checkResult("Ready");
    }

    @Test
    public void clearLongTest() {
        press(R.id.button2);
        press(R.id.buttonAdd);
        press(R.id.button5);
        press(R.id.buttonEqual);
        longPress((R.id.buttonClear));
        press(R.id.buttonAdd);
        press(R.id.button2);
        press(R.id.buttonEqual);
        checkResult("2");
    }

    @Test
    public void complexTest() {
        press(R.id.button2);
        press(R.id.buttonAdd);
        press(R.id.button5);
        press(R.id.buttonSub);
        checkResult("2+5-");
        press(R.id.button3);
        press(R.id.buttonMul);
        checkResult("2+5-3X");

        press(R.id.button1);
        press(R.id.button0);
        press(R.id.buttonDiv);
        checkResult("2+5-3X10/");

        press(R.id.button5);
        press(R.id.buttonEqual);
        checkResult("1");

        press(R.id.buttonClear);
        checkResult("Ready");
    }

    private void press(int id) {
        onView(withId(id)).perform(click());
    }

    private void longPress(int id) {
        onView(withId(id)).perform(longClick());
    }

    private void checkResult(String desired) {
        onView(withId(R.id.text_view_result)).check(matches(withText(desired)));
    }

}
