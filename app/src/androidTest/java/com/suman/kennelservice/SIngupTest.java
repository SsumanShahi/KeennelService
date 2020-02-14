package com.suman.kennelservice;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;

import com.suman.kennelservice.activity.LoginActivity;
import com.suman.kennelservice.activity.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SIngupTest {


    @Rule
    public ActivityTestRule<RegisterActivity> testRule=new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void signupTest()
    {

        onView(withId(R.id.profileimg))
                .perform(click());
        onView(withId(R.id.etfname))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etlname))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etaddress))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etphone))
                .perform(typeText("9845697445"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etemail))
                .perform(typeText("test1@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etusername))
                .perform(typeText("test12345"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etpassword))
                .perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etcmpassword))
                .perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnreg))
                .perform(click());

//        onView(withId(R.id.logint))
//                .check(ViewAssertions.matches(isDisplayed()));


    }

    @Test
    public void signupTestfailed()
    {

        onView(withId(R.id.profileimg))
                .perform(click());
        onView(withId(R.id.etfname))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etlname))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etaddress))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etphone))
                .perform(typeText("984569745"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etemail))
                .perform(typeText("test1@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etusername))
                .perform(typeText("test123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etpassword))
                .perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etcmpassword))
                .perform(typeText("test3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnreg))
                .perform(click());

        onView(withId(R.id.logint))
                .check(ViewAssertions.matches(isDisplayed()));


    }
}
