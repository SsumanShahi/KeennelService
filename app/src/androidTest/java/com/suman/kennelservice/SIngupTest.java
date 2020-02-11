package com.suman.kennelservice;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import com.suman.kennelservice.activity.LoginActivity;
import com.suman.kennelservice.activity.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SIngupTest {


    @Rule
    public ActivityTestRule<RegisterActivity> testRule=new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void signTest()
    {
        onView(withId(R.id.etfname))
                .perform(typeText("Suman"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etlname))
                .perform(typeText("Shahi"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etaddress))
                .perform(typeText("Nakhu"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etphone))
                .perform(typeText("98756986321"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etemail))
                .perform(typeText("o@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ettusername))
                .perform(typeText("ssuman123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etpassword))
                .perform(typeText("suman"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etcmpassword))
                .perform(typeText("suman"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnreg))
                .perform(click());

        isDisplayed();


    }
}
