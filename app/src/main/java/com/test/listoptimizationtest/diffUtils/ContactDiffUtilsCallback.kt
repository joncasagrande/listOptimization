package com.test.listoptimizationtest.diffUtils

import android.support.v7.util.DiffUtil
import com.test.listoptimizationtest.model.Contact

class ContactDiffUtilsCallback(val oldList: List<Contact>, val newList: List<Contact>): DiffUtil.Callback(){
	
	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition] == newList[newItemPosition]
	}
	
	override fun getOldListSize(): Int {
		return oldList.size
	}
	
	override fun getNewListSize(): Int {
		return newList.size
	}
	
	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val newContact = newList[newItemPosition]
		val oldContact = oldList[oldItemPosition]
		
		return newContact.name === oldContact.name && newContact.surename === oldContact.surename
	}
	
}