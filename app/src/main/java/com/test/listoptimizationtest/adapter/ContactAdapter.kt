package com.test.listoptimizationtest.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.listoptimizationtest.R
import com.test.listoptimizationtest.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(val data: List<Contact> ) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(data[position])

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Contact) = with(itemView) {
            tx_name.text = item.name
            tx_sureName.text = item.surename
            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}   