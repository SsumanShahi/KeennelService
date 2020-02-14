package com.suman.kennelservice;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.suman.kennelservice.activity.AppointmentActivity;
import com.suman.kennelservice.activity.LoginActivity;
import com.suman.kennelservice.activity.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AppointmentRegisterTesting {

    @Rule
    public ActivityTestRule<LoginActivity> testRule=new ActivityTestRule<>(LoginActivity.class);



    @Test
    public void AppointmentTest()
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

        onView(withId(R.id.mobile_navigation))
                .perform(click());

        onView(withId(R.id.nav_share))
                .perform(click());

        onView(withId(R.id.btn_appointment))
                .perform(click());



        onView(withId(R.id.et_ownername))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.et_petname))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.et_breed))
                .perform(typeText("Test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.et_age))
                .perform(typeText("984569745"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.et_gender))
                .perform(typeText("test@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_app))
                .perform(typeText("test123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.tvusername))
                .perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_app))
                .perform(click());

        onView(withId(R.id.display))
                .check(ViewAssertions.matches(isDisplayed()));


    }
}
