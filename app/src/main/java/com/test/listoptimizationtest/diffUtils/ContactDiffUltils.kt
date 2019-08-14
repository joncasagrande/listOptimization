package com.test.listoptimizationtest.diffUtils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import android.util.Log
import com.test.listoptimizationtest.NAME
import com.test.listoptimizationtest.PICTURE
import com.test.listoptimizationtest.SURNAME
import com.test.listoptimizationtest.model.Contact

class ContactDiffUltils : DiffUtil.ItemCallback<Contact>(){
	
	override fun areItemsTheSame(oldContact: Contact, newContact: Contact): Boolean {
		return oldContact.id == newContact.id
	}
	
	override fun areContentsTheSame(oldContact: Contact, newContact: Contact): Boolean {
		return oldContact.equals(newContact)
	}
	
	override fun getChangePayload(oldContact: Contact, newContact: Contact): Any? {
		val bundle = Bundle()
		Log.d("ContactDiffUltils", "something change")
		
		if(newContact.name === oldContact.name){
			bundle.putString(NAME, newContact.name)
		}
		
		if(newContact.surename === oldContact.surename){
			bundle.putString(SURNAME, newContact.surename)
		}
		
		if(newContact.hasPhoto && !oldContact.hasPhoto){
			bundle.putBoolean(PICTURE, newContact.hasPhoto)
		}
		
		return bundle
	}
}