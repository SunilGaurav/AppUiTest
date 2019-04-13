package com.mytaxi.android_demo;

import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.ConditionWatcher.ButtonVisibleConditionInstruction;
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
import static com.mytaxi.android_demo.InstrumentationBase.fillLoginDetails;
import static com.mytaxi.android_demo.InstrumentationBase.isLoggedIn;
import static com.mytaxi.android_demo.InstrumentationBase.searchEditText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class SearchTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        if (!InstrumentationBase.isLoggedIn()){
            fillLoginDetails(InstrumentationBase.VALID_USER_NAME, InstrumentationBase.VALID_PASSWORD);
            ButtonVisibleConditionInstruction.waitForElementIsDisplayed(searchEditText,0);
        }
    }

    @Test
    public void test_Search_Then_Call_Button() throws NoMatchingViewException {
        try {

            Thread.sleep(2000);
            InstrumentationBase.searchEditText.perform(click());
            searchEditText.perform(typeText(InstrumentationBase.NAME_TO_BE_SEARCHED));
            onView(withText(InstrumentationBase.NAME_TO_BE_SELECTED)).inRoot(withDecorView(not(mMainActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());
            if (InstrumentationBase.doesResourceIdExist(InstrumentationBase.searchScreenFab)){
                onView(withId(InstrumentationBase.searchScreenFab)).perform(click());
            } else {
                Assert.fail("Test failed");
            }
        } catch (NoMatchingViewException exception) {
            Assert.fail();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        mMainActivityRule.launchActivity(new Intent(mMainActivityRule.getActivity().getApplicationContext(), MainActivity.class));

        InstrumentationBase.logout();
    }
}
