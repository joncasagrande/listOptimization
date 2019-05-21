package com.test.listoptimizationtest.adapter

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.listoptimizationtest.*
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
	
	
	override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(getItem(position), View.OnClickListener {
		val bundle: Bundle = Bundle()
		bundle.putString(FAVORITE,FAVORITE)
		getItem(position).isFavorite = !getItem(position).isFavorite
		notifyItemChanged(position, bundle)
	})
	
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
			if(bundle[FAVORITE] != null){
				
				if(getItem(position).isFavorite){
					holder.itemView.ibv_favorite.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context,R.drawable.ic_star_no_favorite))
				}else{
					holder.itemView.ibv_favorite.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context,R.drawable.ic_star))
				}
			}
			
		}
	}
	
}