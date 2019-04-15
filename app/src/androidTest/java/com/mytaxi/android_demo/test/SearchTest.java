package com.mytaxi.android_demo.test;

import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.InstrumentationBase;
import com.mytaxi.android_demo.conditions.ViewVisibilityCondition;
import com.mytaxi.android_demo.activities.MainActivity;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mytaxi.android_demo.InstrumentationBase.fill_login_details;
import static com.mytaxi.android_demo.InstrumentationBase.searchEditText;
import static com.mytaxi.android_demo.Utils.doesResourceIdExist;
import static com.mytaxi.android_demo.Utils.isLoggedIn;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class SearchTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        InstrumentationBase.initialize();
        if (!isLoggedIn(InstrumentationBase.callfab)) {
            fill_login_details(InstrumentationBase.VALID_USER_NAME, InstrumentationBase.VALID_PASSWORD);
            ViewVisibilityCondition.waitForElementIsDisplayed(searchEditText, 0);
        }
    }

    @Test
    public void test_search_then_call_button() {
        try {
         //Putting sleep to handle the code issue where search was failing due to some internal operation
            Thread.sleep(2000);
            InstrumentationBase.searchEditText.perform(click());
            searchEditText.perform(typeText(InstrumentationBase.NAME_TO_BE_SEARCHED));
            onView(withText(InstrumentationBase.NAME_TO_BE_SELECTED)).inRoot(withDecorView(not(mMainActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());
            if (doesResourceIdExist(InstrumentationBase.callfab)) {
                onView(withId(InstrumentationBase.callfab)).perform(click());
            } else {
                Assert.fail("Test failed");
            }
        } catch (NoMatchingViewException exception) {
            Assert.fail();
        } catch (InterruptedException e) {
            Assert.fail();
            e.printStackTrace();
        } catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @After
    public void clear() {
        mMainActivityRule.launchActivity(new Intent(mMainActivityRule.getActivity().getApplicationContext(), MainActivity.class));
        InstrumentationBase.logout();
    }
}
