package com.test.listoptimizationtest.diffUtils

import android.support.v7.util.DiffUtil
import com.test.listoptimizationtest.model.Contact

class ContactDiffUltils(): DiffUtil.ItemCallback<Contact>(){
	
	override fun areItemsTheSame(oldUser: Contact, newUser: Contact): Boolean {
		return oldUser.id == newUser.id
	}
	
	override fun areContentsTheSame(oldUser: Contact, newUser: Contact): Boolean {
		return oldUser.equals(newUser)
	}

}