package com.test.listoptimizationtest.adapter

import android.os.Bundle
import android.support.v7.recyclerview.extensions.ListAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.listoptimizationtest.NAME
import com.test.listoptimizationtest.PICTURE
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.SURNAME
import com.test.listoptimizationtest.diffUtils.ContactDiffUltils
import com.test.listoptimizationtest.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactListAdapter() : ListAdapter<Contact, ContactViewHolder>(ContactDiffUltils()) {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
		return ContactViewHolder(
			LayoutInflater.from(parent.context)
				.inflate(R.layout.item_contact, parent, false)
		)
	}
	
	
	override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(getItem(position))
	
	override fun onBindViewHolder(holder: ContactViewHolder, position: Int, payloads: MutableList<Any>) {
		if(payloads.isEmpty()){
			onBindViewHolder(holder,position)
		}else{
			val bundle: Bundle = payloads[0] as Bundle
			Log.d("ContactListAdapter", "has bundle ${bundle.size()}")
			
			if(bundle[NAME] != null){
				holder.itemView.tx_name.text = bundle.getString(NAME)
			}
			if(bundle[SURNAME] != null){
				holder.itemView.tx_name.text = bundle.getString(SURNAME)
			}
			if(bundle[PICTURE] != null){
				//TODO set photo
			}
			
		}
	}
	
}