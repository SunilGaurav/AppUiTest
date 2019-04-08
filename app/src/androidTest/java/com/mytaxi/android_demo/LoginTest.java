package com.mytaxi.android_demo;

import android.os.Build;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import android.util.Log;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.AuthenticationActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LoginTest {
    @Rule
    public ActivityTestRule<AuthenticationActivity> mActivityRule =
            new ActivityTestRule<>(AuthenticationActivity.class);

   // @Before
   // private void allowPermissionsIfNeeded() throws UiObjectNotFoundException {
        //onView(withId(R.id.permission_allow_button));
      //  UiDevice obj = UiDevice.getInstance(getInstrumentation());
      //  UiObject allowPermissions = obj.findObject(new UiSelector().text("Allow"));
      //  if (allowPermissions.exists()) {

      //      allowPermissions.click();
       // }
      //  else{

       // }

    //}


    @Test
    public void test_username_password_valid_then_login() {

        onView((withId(R.id.edt_username)))
                .perform(ViewActions.typeText("crazydog335"));

        onView(withId(R.id.edt_password))
                .perform(ViewActions.typeText("venture"));

        onView(withId(R.id.btn_login))
                .perform(ViewActions.click());

        onView(withId(R.id.fab))
                .check(matches(isDisplayed()));
    }
}

