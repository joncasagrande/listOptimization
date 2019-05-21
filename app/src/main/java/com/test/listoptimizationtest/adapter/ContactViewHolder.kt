package com.test.listoptimizationtest.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	fun bind(item: Contact, clickListener : View.OnClickListener ) = with(itemView) {
		tx_name.text = item.name
		tx_sureName.text = item.surename
		
		if(item.isFavorite){
			ibv_favorite.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_star_no_favorite))
		}else{
			ibv_favorite.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_star))
		}
		/*Glide.with(itemView.context)
			.load(item.photoUri)
			.centerCrop()
			.placeholder(R.drawable.ic_account_circle_black_24dp)
			.crossFade()
			.into(iv_contact)
		*/
		ibv_favorite.setOnClickListener(clickListener)
		setOnClickListener {
			// TODO: Handle on click
			
		}
	}
}