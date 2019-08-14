package com.test.listoptimizationtest

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.listoptimizationtest.adapter.ContactViewHolder
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
	
	@get:Rule
	var activityScenarioRule = activityScenarioRule<MainActivity>()
	
	@Before
	fun setUp() {
		val scenario = activityScenarioRule.scenario
		scenario.moveToState(Lifecycle.State.RESUMED)
	}
	
	@Test
	fun testRcyclerView(){
		// Last item should not exist if the list wasn't scrolled down.
		onView(withText("Vinicius")).check(doesNotExist())
	
	}
	
	@Test
	fun testValidateItem(){
		// Last item should not exist if the list wasn't scrolled down.
		onView(withId(R.id.rvContact)).check(matches(hasItem(hasDescendant(withText("Joana (jojo)")))))
	}
	
	@Test
	fun testInValidadItem(){
		// Last item should not exist if the list wasn't scrolled down.
		onView(withId(R.id.rvContact)).check(matches(hasItem((not(hasDescendant(withText("------")))))))
	}
	
	@Test
	fun testSizeAdapter(){
		onView(withId (R.id.rvContact)).check(matches(checkSize(4)))
	}
	
	
	@Test
	fun testItemClicked(){
		onView(withId(R.id.rvContact)).perform(
			RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click())
		)
	
	}
	
	
	
	private fun hasItem(textMatcher: Matcher<View>): Matcher<Any> =
		object : BoundedMatcher<Any, RecyclerView>(RecyclerView::class.java) {
			override fun describeTo(description: Description?) {
				description!!.appendText("has item: ")
				textMatcher.describeTo(description)
			}
			
			override fun matchesSafely(recyclerView: RecyclerView): Boolean {
				val adapter = recyclerView.adapter
				for (position in 0 until adapter!!.getItemCount()) {
					val type = adapter.getItemViewType(position)
					val holder : ContactViewHolder = adapter.createViewHolder(recyclerView, type) as ContactViewHolder
					adapter.onBindViewHolder(holder, position)
					if (textMatcher.matches(holder.itemView)) {
						return true
					}
				}
				return false
			}
		}
	
	private fun checkSize(size: Int): Matcher<Any> =
		object : BoundedMatcher<Any, RecyclerView>(RecyclerView::class.java) {
			
			override fun matchesSafely(recyclerView: RecyclerView): Boolean {
				val adapter = recyclerView.adapter
				return adapter!!.itemCount == size
			}
			
			
			override fun describeTo(description: Description?) {
				description!!.appendText ("Adapter should have " + size + " items");
			}
		}
}