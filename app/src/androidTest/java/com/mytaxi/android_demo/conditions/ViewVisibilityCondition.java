package com.mytaxi.android_demo.conditions;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;

import com.mytaxi.android_demo.ConditionWatcher.ConditionWatcher;
import com.mytaxi.android_demo.ConditionWatcher.Instruction;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

/*
Class to check if the visibility condition for particular view are met
 */
public class ViewVisibilityCondition {
    public static ViewInteraction waitForElementIsDisplayed(final ViewInteraction interaction,
                                                            final int timeout) throws Exception {

        ConditionWatcher.setTimeoutLimit(timeout);
        ConditionWatcher.waitForCondition(new Instruction() {
            @Override
            public String getDescription() {
                return "waitForElementIsDisplayed";
            }

            @Override
            public boolean checkCondition() {
                try {
                    interaction.check(matches(isDisplayed()));
                    return true;
                } catch (NoMatchingViewException ex) {
                    return false;
                }


            }
        });
        return interaction;
    }
}
