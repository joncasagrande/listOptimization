package com.test.listoptimizationtest.diffUtils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.test.listoptimizationtest.NAME
import com.test.listoptimizationtest.PICTURE
import com.test.listoptimizationtest.SURNAME
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
	
	override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
		val bundle = Bundle()
		val newContact = newList[newItemPosition]
		val oldContact = oldList[oldItemPosition]
		
		if(newContact.name != oldContact.name){
			bundle.putString(NAME, newContact.name)
		}
		
		if(newContact.surename != oldContact.surename){
			bundle.putString(SURNAME, newContact.surename)
		}
		
		if((newContact.hasPhoto && !oldContact.hasPhoto) ||  (!newContact.hasPhoto && oldContact.hasPhoto)){
			bundle.putBoolean(PICTURE, newContact.hasPhoto)
		}
		
		return bundle
	}
}