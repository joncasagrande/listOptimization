package com.test.listoptimizationtest.loader

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.test.listoptimizationtest.MainActivity
import com.test.listoptimizationtest.model.Contact
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ContactLoaderManagerTest {
	
	@Rule
	var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
	lateinit var contactLoader : ContactLoaderManager
	@Before
	fun setUp() {
		val context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext()
		contactLoader = ContactLoaderManager(context, object :
			ContactLoaderManager.ContactLoaderCallback {
			override fun loadFinished(contacts: List<Contact>) {
				Log.d("TAG","Size:${contacts.size}")
			}
		})
	}
	
	@Test
	fun testCreateLoader(){
		val cursoLoader = contactLoader.onCreateLoader(0,null)
		assertNotNull(cursoLoader)
	}
	
	@Test
	fun testLoaderFinish(){
		//when(contactLoader.onLoadFinished(contactLoader.onCreateLoader(0,null),null))
		
	}
}