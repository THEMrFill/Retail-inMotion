package com.retailinmotion.philip.arnold

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.retailinmotion.philip.arnold.consts.Constants
import com.retailinmotion.philip.arnold.di.StartMockedKoin
import com.retailinmotion.philip.arnold.ui.main.MainFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@LargeTest
class AfternoonTester {
    fun before() {
        if (GlobalContext.getOrNull() != null) {
            stopKoin()
        }
        StartMockedKoin.startMockedKoin(1)
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
            .check(ViewAssertions.matches(withText(Constants.AFTERNOON_STATION)))
        onView(withId(R.id.directionText))
            .check(ViewAssertions.matches(withText(Constants.INBOUND)))
        onView(withId(R.id.recycler)).check(RecyclerViewItemCountAssertion(2));

        after()
    }

}