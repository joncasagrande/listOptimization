package com.test.listoptimizationtest.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.listoptimizationtest.NAME
import com.test.listoptimizationtest.PICTURE
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.SURNAME
import com.test.listoptimizationtest.diffUtils.ContactDiffUtilsCallback
import com.test.listoptimizationtest.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*


class ContactRecyclerViewAdapter(val data: MutableList<Contact> ) : RecyclerView.Adapter<ContactViewHolder>() {

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
            
        }
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