package com.test.listoptimizationtest.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.diffUtils.ContactDiffUltils
import com.test.listoptimizationtest.model.Contact

class ContactListAdapter() : ListAdapter<Contact, ContactViewHolder>(ContactDiffUltils()) {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
		return ContactViewHolder(
			LayoutInflater.from(parent.context)
				.inflate(R.layout.item_contact, parent, false)
		)
	}
	
	
	override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(getItem(position))
	
	
}