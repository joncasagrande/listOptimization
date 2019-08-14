package com.test.listoptimizationtest.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.test.listoptimizationtest.*
import com.test.listoptimizationtest.diffUtils.ContactDiffUtilsCallback
import com.test.listoptimizationtest.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*


class ContactRecyclerViewAdapter(val data: MutableList<Contact>) : androidx.recyclerview.widget.RecyclerView.Adapter<ContactViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)
        )
    }
    
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int, payloads: MutableList<Any>) {
        if(payloads.isEmpty()){
            onBindViewHolder(holder,position)
        }else{
            val bundle: Bundle = payloads[0] as Bundle
            Log.d("ContactRecycler", "has bundle ${bundle.size()}")
    
            if(bundle[NAME] != null){
                holder.itemView.tx_name.text = data[position].name
            }
            if(bundle[SURNAME] != null){
                holder.itemView.tx_name.text = data[position].surename
            }
            if(bundle[PICTURE] != null){
                //TODO set photo
            }
	        if(bundle[FAVORITE] != null){
		
		        if(data[position].isFavorite){
			        holder.itemView.ibv_favorite.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context,R.drawable.ic_star_no_favorite))
		        }else{
			        holder.itemView.ibv_favorite.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context,R.drawable.ic_star))
		        }
	        }
            
        }
    }
    
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(data[position], View.OnClickListener {
        data[position].isFavorite =!data[position].isFavorite
	    val bundle: Bundle = Bundle()
        bundle.putString(FAVORITE, FAVORITE)
        notifyItemChanged(position, bundle)
    })
    
    fun swapItems(contacts: List<Contact>) {
        // compute diffs
        val diffCallback = ContactDiffUtilsCallback(this.data, contacts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        
        // clear contacts and add
        data.clear()
        data.addAll(contacts)
        
        diffResult.dispatchUpdatesTo(this) // calls adapter's notify methods after diff is computed
        
    }
    
    
}   