package com.mytaxi.android_demo;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.MainActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mytaxi.android_demo.InstrumentationBase.fillLoginDetails;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class SearchTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void test_Search_Then_Call_Button() throws InterruptedException {
        try {
            Thread.sleep(2000);
            if (InstrumentationBase.isLoggedIn()) {
                InstrumentationBase.searchEditText.perform(click());
                onView(isAssignableFrom(AutoCompleteTextView.class)).perform(typeText(InstrumentationBase.NAME_TO_BE_SEARCHED));
                onView(withText(InstrumentationBase.NAME_TO_BE_SELECTED)).inRoot(withDecorView(not(mMainActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());

                Thread.sleep(5000);
            } else {
                fillLoginDetails(InstrumentationBase.VALID_USER_NAME, InstrumentationBase.VALID_PASSWORD);
                InstrumentationBase.searchEditText.perform(click());
                onView(isAssignableFrom(EditText.class)).perform(typeText(InstrumentationBase.NAME_TO_BE_SEARCHED));
                onView(withText(InstrumentationBase.NAME_TO_BE_SELECTED)).inRoot(withDecorView(not(mMainActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());
            }
        } catch (NoMatchingViewException exception) {
            Assert.fail();
        }

    }
}
