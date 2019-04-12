package com.mytaxi.android_demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mytaxi.android_demo.IdlingResource.ButtonVisibleConditionInstruction;
import com.mytaxi.android_demo.IdlingResource.ConditionWatcher;
import com.mytaxi.android_demo.IdlingResource.VisibilityIdlingResource;
import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.getIdlingResources;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.view.View.VISIBLE;
import static com.mytaxi.android_demo.InstrumentationBase.VALID_PASSWORD;
import static com.mytaxi.android_demo.InstrumentationBase.VALID_USER_NAME;
import static com.mytaxi.android_demo.InstrumentationBase.fillLoginDetails;
import static com.mytaxi.android_demo.InstrumentationBase.floatingActionButton;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);



    @Before
    public void setup() {
        mMainActivityRule.launchActivity(new Intent(mMainActivityRule.getActivity().getApplicationContext(), MainActivity.class));
    }

    @Test
    public void test_Username_Password_Valid_Then_Login() throws Exception {


        fillLoginDetails(InstrumentationBase.VALID_USER_NAME, InstrumentationBase.VALID_PASSWORD);

        ButtonVisibleConditionInstruction.waitForElementIsDisplayed(floatingActionButton, 0);

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

    @After
    public void clean() {
        mMainActivityRule.finishActivity();
    }


}

