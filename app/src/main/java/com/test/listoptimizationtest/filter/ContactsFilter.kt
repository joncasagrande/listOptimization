package com.test.listoptimizationtest.filter

import android.widget.Filter
import com.test.listoptimizationtest.model.Contact

class ContactsFilter(val contact : MutableList<Contact>, val contactsFilterListener: ContactsFilterListener ) : Filter(){

	interface ContactsFilterListener{
		fun onpublisResults(filterResults: MutableList<Contact>)
	}
	
	override fun performFiltering(constraint: CharSequence?): FilterResults {
		val filterResults = FilterResults()
		if(constraint.isNullOrEmpty()){
			filterResults.values = contact
			return filterResults
		}else{
			val constraintLowerCase = constraint.toString().toLowerCase()
			
			filterResults.values = contact.filter { it.name.toLowerCase().contains(constraintLowerCase)
					|| it.surename.toLowerCase().contains(constraintLowerCase)}
			return filterResults
		}
	}
	
	override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
		if (results!=null && results.values is List<*>){
			contactsFilterListener.onpublisResults(results.values as MutableList<Contact>)
		}
	}
	
}