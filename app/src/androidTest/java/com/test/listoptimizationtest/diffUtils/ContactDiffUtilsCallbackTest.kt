package com.test.listoptimizationtest.diffUtils

import android.os.Bundle
import com.test.listoptimizationtest.*
import com.test.listoptimizationtest.model.Contact
import junit.framework.Assert.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ContactDiffUtilsCallbackTest {

	lateinit var contactDiffUtilsCallback: ContactDiffUtilsCallback
	val contact1Name = Contact(2, "test", "surname1",true)
	val contact1Surname = Contact(2, "testName1", "sur",true)
	val contact1Pic = Contact(2, "testName1", "surname1",false)
	val list = listOf(contact, contact1, contactClone)
	val contactList= listOf(contact, contact1,contact1Name,contact1Surname,contact1Pic)
	@Before
	fun setUp() {
		contactDiffUtilsCallback = ContactDiffUtilsCallback(list,contactList)
	}

	@Test
	fun areItemsTheSame() {
		assertTrue(contactDiffUtilsCallback.areItemsTheSame(2,0))
	}
	@Test
	fun areDifferentItems() {
		assertFalse(contactDiffUtilsCallback.areItemsTheSame(2,1))
	}

	@Test
	fun getOldListSize() {
		assertEquals(list.size,contactDiffUtilsCallback.oldList.size)
	}

	@Test
	fun getNewListSize() {
		assertEquals(contactList.size,contactDiffUtilsCallback.newList.size)
	}

	@Test
	fun areContentsTheSame() {
		assertTrue(contactDiffUtilsCallback.areContentsTheSame(2,0))
	}
	@Test
	fun areContentsNotTheSame() {
		assertFalse(contactDiffUtilsCallback.areContentsTheSame(2,1))
	}
	
	@Test
	fun getChangePayloadName() {
		
		val bundle = contactDiffUtilsCallback.getChangePayload(1,2) as Bundle
		Assert.assertNotNull(bundle.getString(NAME))
	}
	
	@Test
	fun getChangePayloadSurname() {
		
		val bundle = contactDiffUtilsCallback.getChangePayload(1,3)as Bundle
		Assert.assertNotNull(bundle.getString(SURNAME))
	}
	
	@Test
	fun getChangePayloadPic() {
		
		val bundle = contactDiffUtilsCallback.getChangePayload(1,4) as Bundle
		Assert.assertNotNull(bundle.getBoolean(PICTURE))
	}
}