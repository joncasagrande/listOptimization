package com.test.listoptimizationtest.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.model.Contact


class SimpleContactRecyclerViewAdapter(val data: MutableList<Contact>) : androidx.recyclerview.widget.RecyclerView.Adapter<ContactViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)
        )
    }
    
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(data[position], View.OnClickListener {
        data[position].isFavorite = !data[position].isFavorite
        notifyItemChanged(position)
    })
    
    fun swapItems(contacts: List<Contact>) {
        // clear contacts and add
        data.clear()
        data.addAll(contacts)
        
       notifyDataSetChanged()
        
    }
    
    
}   