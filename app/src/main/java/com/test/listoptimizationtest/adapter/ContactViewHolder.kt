package com.test.listoptimizationtest.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	fun bind(item: Contact) = with(itemView) {
		tx_name.text = item.name
		tx_sureName.text = item.surename
		Glide.with(itemView.context)
			.load(item.photoUri)
			.centerCrop()
			.placeholder(R.drawable.ic_account_circle_black_24dp)
			.crossFade()
			.into(iv_contact)
		
		
		setOnClickListener {
			// TODO: Handle on click
		}
	}
}