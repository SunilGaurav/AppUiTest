package com.mytaxi.android_demo.test;

import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.InstrumentationBase;
import com.mytaxi.android_demo.conditions.ViewVisibilityCondition;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mytaxi.android_demo.InstrumentationBase.INVALID_USER_NAME;
import static com.mytaxi.android_demo.InstrumentationBase.VALID_PASSWORD;
import static com.mytaxi.android_demo.InstrumentationBase.VALID_USER_NAME;
import static com.mytaxi.android_demo.InstrumentationBase.fill_login_details;
import static com.mytaxi.android_demo.InstrumentationBase.floatingActionButtonLocation;
import static com.mytaxi.android_demo.InstrumentationBase.logout;


@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule =
            GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    @Before
    public void setup() {
        InstrumentationBase.initialize();
    }

    @Test
    public void test_username_password_valid_then_login() throws Exception {
        fill_login_details(VALID_USER_NAME, VALID_PASSWORD);
        ViewVisibilityCondition.waitForElementIsDisplayed(floatingActionButtonLocation, 0);
        logout();
    }

    @Test
    public void test_username_invalid_then_login() throws Exception {
        fill_login_details(INVALID_USER_NAME, VALID_PASSWORD);
        ViewVisibilityCondition.waitForElementIsDisplayed(onView(withText("Login failed")), 0);
    }

    @Test
    public void test_password_invalid_then_login() throws Exception {
        fill_login_details(VALID_USER_NAME, InstrumentationBase.INVALID_PASSWORD);
        ViewVisibilityCondition.waitForElementIsDisplayed(onView(withText("Login failed")), 0);
        InstrumentationBase.loginButton.check(matches(isDisplayed()));
    }

    @Test
    public void test_password_empty_then_login() throws Exception {
        fill_login_details(VALID_USER_NAME, "");
        ViewVisibilityCondition.waitForElementIsDisplayed(onView(withText("Login failed")), 0);
    }

    @Test
    public void test_username_empty_then_login() throws Exception {
        fill_login_details("", VALID_PASSWORD);
        ViewVisibilityCondition.waitForElementIsDisplayed(onView(withText("Login failed")), 0);
    }
}

