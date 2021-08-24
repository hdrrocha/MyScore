package com.example.myscore

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.myscore.ui.ScoreHomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScoreHomeActivityTest {
    @get:Rule
    val rule = ActivityTestRule(ScoreHomeActivity::class.java, true, true)

    @Test
    fun  when_create_activity_it_should_display_loadingScore() {
        try {
            Thread.sleep(2000)
            onView(withId(R.id.loadingScore)).isVisible()
            onView(withId(R.id.loadingScore)).isGone()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun  when_end_loadingScoreactivity_it_should_display_progressScore() {
        try {
            Thread.sleep(2000)
            onView(withId(R.id.loadingScore)).isVisible()
            onView(withId(R.id.loadingScore)).isGone()
            onView(withId(R.id.progressScore)).isVisible()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun ViewInteraction.isGone() = getViewAssertion(ViewMatchers.Visibility.GONE)

    fun ViewInteraction.isVisible() = getViewAssertion(ViewMatchers.Visibility.VISIBLE)

    fun ViewInteraction.isInvisible() = getViewAssertion(ViewMatchers.Visibility.INVISIBLE)

    private fun getViewAssertion(visibility: ViewMatchers.Visibility): ViewAssertion? {
        return ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
    }
}