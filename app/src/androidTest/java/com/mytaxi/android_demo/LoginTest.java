package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.ConditionWatcher.ButtonVisibleConditionInstruction;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mytaxi.android_demo.InstrumentationBase.VALID_PASSWORD;
import static com.mytaxi.android_demo.InstrumentationBase.VALID_USER_NAME;
import static com.mytaxi.android_demo.InstrumentationBase.fillLoginDetails;
import static com.mytaxi.android_demo.InstrumentationBase.floatingActionButton;
import static com.mytaxi.android_demo.InstrumentationBase.logout;


@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);
    //granting location permission
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);



    @Test
    public void test_Username_Password_Valid_Then_Login() throws Exception {


        fillLoginDetails(InstrumentationBase.VALID_USER_NAME, InstrumentationBase.VALID_PASSWORD);
        Thread.sleep(2000);
        ButtonVisibleConditionInstruction.waitForElementIsDisplayed(floatingActionButton, 0);
        logout();

    }

    @Test
    public void test_Username_Invalid_Then_Login() throws Exception {

        fillLoginDetails(InstrumentationBase.INVALID_USER_NAME, VALID_PASSWORD);
        ButtonVisibleConditionInstruction.waitForElementIsDisplayed(onView(withText("Login failed")), 0);

    }

    @Test
    public void test_Password_Invalid_Then_Login() throws Exception {

        fillLoginDetails(VALID_USER_NAME, InstrumentationBase.INVALID_PASSWORD);
        ButtonVisibleConditionInstruction.waitForElementIsDisplayed(onView(withText("Login failed")), 0);
        InstrumentationBase.loginButton.check(matches(isDisplayed()));
    }

    @Test
    public void test_Empty_Password_Then_Login() throws Exception {

        fillLoginDetails(VALID_USER_NAME, "");
        ButtonVisibleConditionInstruction.waitForElementIsDisplayed(onView(withText("Login failed")), 0);
    }

    @Test
    public void test_Empty_Username_Then_Login() throws Exception {

        fillLoginDetails("", VALID_PASSWORD);
        ButtonVisibleConditionInstruction.waitForElementIsDisplayed(onView(withText("Login failed")), 0);

    }

}

