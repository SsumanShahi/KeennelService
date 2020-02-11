package com.suman.kennelservice;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import com.suman.kennelservice.activity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> testRule=new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginTest()
    {
        onView(withId(R.id.ettusername))
                .perform(typeText("suman123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ettpassword))
                .perform(typeText("suman"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnlogin))
                .perform(click());

        onView(withId(R.id.display))
                .check(ViewAssertions.matches(isDisplayed()));
    }


    @Test
    public void loginTestfailed()
    {
        onView(withId(R.id.ettusername))
                .perform(typeText("suman1234"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ettpassword))
                .perform(typeText("suman"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnlogin))
                .perform(click());

        onView(withId(R.id.display))
                .check(ViewAssertions.matches(isDisplayed()));
    }
}
