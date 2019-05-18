package com.test.listoptimizationtest.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.diffUtils.ContactDiffUtilsCallback
import com.test.listoptimizationtest.model.Contact


class ContactRecyclerViewAdapter(val data: MutableList<Contact> ) : RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(data[position])
    
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