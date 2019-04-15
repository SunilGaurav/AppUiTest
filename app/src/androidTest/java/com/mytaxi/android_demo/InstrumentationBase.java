package com.mytaxi.android_demo;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mytaxi.android_demo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.mytaxi.android_demo.Utils.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class InstrumentationBase {

    public static final String VALID_USER_NAME = "crazydog335";
    public static final String VALID_PASSWORD = "venture";
    public static final String INVALID_USER_NAME = "InvalidUName";
    public static final String INVALID_PASSWORD = "InvalidPwd";
    public static final String NAME_TO_BE_SEARCHED = "sa";
    public static final String NAME_TO_BE_SELECTED = "Sarah Scott";

    public static int callfab;
    public static ViewInteraction nameEditText;
    public static ViewInteraction pwdEditText;
    public static ViewInteraction loginButton;

    public static int locationFab;
    public static ViewInteraction floatingActionButtonLocation;
    public static ViewInteraction floatingActionButtonCall;
    public static ViewInteraction searchEditText;

    /*
      Method to Initialise the ui Elements
     */
    public static void initialize() {
        nameEditText = onView(ViewMatchers.withId(com.mytaxi.android_demo.R.id.edt_username));
        pwdEditText = onView(ViewMatchers.withId(com.mytaxi.android_demo.R.id.edt_password));
        loginButton = onView(ViewMatchers.withId(com.mytaxi.android_demo.R.id.btn_login));
        floatingActionButtonLocation = onView(ViewMatchers.withId(com.mytaxi.android_demo.R.id.location_fab));
        floatingActionButtonCall = onView(ViewMatchers.withId(R.id.call_fab));
        searchEditText = onView(ViewMatchers.withId(com.mytaxi.android_demo.R.id.textSearch));
        callfab = R.id.call_fab;
        locationFab = R.id.location_fab;
    }

    public static void fill_login_details(String name, String pwd) {
        nameEditText.perform(ViewActions.typeText(name));
        pwdEditText.perform(ViewActions.typeText(pwd));
        closeSoftKeyboard();
        loginButton.perform(ViewActions.click());
    }

    public static void logout() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(ViewMatchers.withId(com.mytaxi.android_demo.R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        onView(ViewMatchers.withId(R.id.design_menu_item_text)).perform(ViewActions.click());
    }
}
