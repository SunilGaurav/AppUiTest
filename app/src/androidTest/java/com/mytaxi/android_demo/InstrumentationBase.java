package com.mytaxi.android_demo;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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
    public static ViewInteraction searchEditText = onView(withId(R.id.textSearch));

    static int crossHairFab = R.id.fab;
    static int searchScreenFab = R.id.fab;

    public static void fillLoginDetails(String name, String pwd) {

        nameEditText.perform(ViewActions.typeText(name));
        passwdEditText.perform(ViewActions.typeText(pwd));
        closeSoftKeyboard();
        loginButton.perform(ViewActions.click());


    }

    public static boolean doesResourceIdExist(int id) {
        try {
            onView(withId(id)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public static void logout() {

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        onView(withId(R.id.design_menu_item_text)).perform(ViewActions.click());

    }
    public static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static boolean isLoggedIn() {
        if (InstrumentationBase.doesResourceIdExist(crossHairFab)) {
            return true;
        } else {
            return false;
        }
    }
}
