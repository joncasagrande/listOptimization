package com.test.listoptimizationtest.diffUtils

import android.os.Bundle
import com.test.listoptimizationtest.*
import com.test.listoptimizationtest.model.Contact
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ContactDiffUtilAndroidTest {
	
	
	lateinit var contactDiffUtils: ContactDiffUltils
	@Before
	fun setUp() {
		contactDiffUtils = ContactDiffUltils()
	}
	
	@Test
	fun areItemsTheSame() {
		Assert.assertTrue("Itens should be the same", contactDiffUtils.areItemsTheSame(contact,contactClone))
	}
	@Test
	fun areItemsShouldBeDifferent() {
		Assert.assertFalse("Itens should be the same", contactDiffUtils.areItemsTheSame(contact,contact1))
	}
	
	@Test
	fun areContentsTheSame() {
		Assert.assertTrue("Contents should be same", contactDiffUtils.areItemsTheSame(contact,contactClone))
	}
	
	@Test
	fun areContentsNotTheSame() {
		Assert.assertFalse("Contents should be different", contactDiffUtils.areItemsTheSame(contact,contact1))
	}
	
	@Test
	fun getChangePayloadName() {
		val contact1Name = Contact(2, "test", "surname1",true)
		val bundle = contactDiffUtils.getChangePayload(contact1,contact1Name) as Bundle
		Assert.assertNotNull(bundle.getString(NAME))
	}
	
	@Test
	fun getChangePayloadSurname() {
		val contact1Surname = Contact(2, "testName1", "sur",true)
		val bundle = contactDiffUtils.getChangePayload(contact1,contact1Surname)as Bundle
		Assert.assertNotNull(bundle.getString(SURNAME))
	}
	
	@Test
	fun getChangePayloadPic() {
		val contact1Pic = Contact(2, "testName1", "surname1",false)
		val bundle = contactDiffUtils.getChangePayload(contact1,contact1Pic) as Bundle
		Assert.assertNotNull(bundle.getBoolean(PICTURE))
	}
	
}