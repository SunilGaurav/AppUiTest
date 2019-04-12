package com.mytaxi.android_demo;

import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.mytaxi.android_demo.IdlingResource.ButtonVisibleConditionInstruction;
import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class InstrumentationBase {

    public static final String VALID_USER_NAME = "crazydog335";
    public static final String VALID_PASSWORD = "venture";
    public static final String INVALID_USER_NAME = "InvalidUName";
    public static final String INVALID_PASSWORD = "InvalidPwd";
    public static final String NAME_TO_BE_SEARCHED = "sa";
    public static final String NAME_TO_BE_SELECTED= "Sarah Scott";

    public static ViewInteraction nameEditText = onView(withId(R.id.edt_username));
    public static ViewInteraction passwdEditText = onView(withId(R.id.edt_password));
    public static ViewInteraction loginButton = onView(withId(R.id.btn_login));
    public static ViewInteraction floatingActionButton = onView(withId(R.id.fab));
    public static ViewInteraction searchEditText = onView(withId(R.id.searchContainer));


    public static void fillLoginDetails(String name, String pwd) {

        nameEditText.perform(ViewActions.typeText(name));
        passwdEditText.perform(ViewActions.typeText(pwd));
        //closeSoftKeyboard();
        loginButton.perform(ViewActions.click());


    }

    public static boolean doesViewExist(int id) {
        try {
            onView(withId(id)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public static boolean isLoggedIn() {
        if (InstrumentationBase.doesViewExist(R.id.fab)) {
            return true;
        } else {
            return false;
        }
    }
}
