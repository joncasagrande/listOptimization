package com.test.listoptimizationtest.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	fun bind(item: Contact, clickListener : View.OnClickListener ) = with(itemView) {
		tx_name.text = item.name
		tx_sureName.text = item.surename
		
		/*Glide.with(itemView.context)
			.load(item.photoUri)
			.centerCrop()
			.placeholder(R.drawable.ic_account_circle_black_24dp)
			.crossFade()
			.into(iv_contact)
		*/
		ibv_favorite.setOnClickListener(clickListener)
		if(!item.isFavorite){
			ibv_favorite.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_star_no_favorite))
		}else{
			ibv_favorite.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_star))
		}
		setOnClickListener {
			// TODO: Handle on click
			
		}
	}
}