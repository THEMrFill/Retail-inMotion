package com.retailinmotion.philip.arnold

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.retailinmotion.philip.arnold.consts.Constants
import com.retailinmotion.philip.arnold.di.StartMockedKoin.startMockedKoin
import com.retailinmotion.philip.arnold.ui.main.MainFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@LargeTest
class MorningTester {
    fun before() {
        if (GlobalContext.getOrNull() != null) {
            stopKoin()
        }
        startMockedKoin(0)
    }
    fun after() {
        stopKoin()
    }

    @Test
    fun checkMorningDisplay() {
        before()
        launchFragmentInContainer<MainFragment>()
        Thread.sleep(500)
        onView(withId(R.id.stop))
            .check(matches(withText(Constants.MORNING_STATION)))
        onView(withId(R.id.directionText))
            .check(matches(withText(Constants.OUTBOUND)))
        onView(withId(R.id.recycler)).check(RecyclerViewItemCountAssertion(3));

        after()
    }
}