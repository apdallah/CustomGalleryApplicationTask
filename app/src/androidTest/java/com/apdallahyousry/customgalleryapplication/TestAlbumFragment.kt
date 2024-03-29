package com.apdallahyousry.customgalleryapplication

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.apdallahyousry.customgalleryapplication.ui.MainActivity
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestAlbumFragment {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testSwitchFromGridToLinear() {
        onView(withId(R.id.albumsRv)).check() { _, _ ->
            activityRule.scenario.onActivity {
                val count =
                    (it.findViewById<RecyclerView>(R.id.albumsRv).layoutManager as GridLayoutManager).spanCount
                assertTrue(count != 1 )
            }
        }
        onView(withId(R.id.actionToggleGridLinear)).perform(click())
        onView(withId(R.id.albumsRv)).check() { _, _ ->
            activityRule.scenario.onActivity {
                val count =
                    (it.findViewById<RecyclerView>(R.id.albumsRv).layoutManager as GridLayoutManager).spanCount
                assertTrue(count == 1)
            }

        }
    }
}