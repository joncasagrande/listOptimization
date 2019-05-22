package com.test.listoptimizationtest

import android.Manifest
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.LoaderManager
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker.PERMISSION_GRANTED
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.test.listoptimizationtest.adapter.ContactListAdapter
import com.test.listoptimizationtest.adapter.ContactRecyclerViewAdapter
import com.test.listoptimizationtest.adapter.SimpleContactRecyclerViewAdapter
import com.test.listoptimizationtest.filter.ContactsFilter
import com.test.listoptimizationtest.loader.ContactLoaderManager
import com.test.listoptimizationtest.model.Contact
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*



class MainActivity : AppCompatActivity(), ContactLoaderManager.ContactLoaderCallback {

    val contacts: MutableList<Contact> = arrayListOf()
    var contactListAdapter = ContactListAdapter()
	var contactRecyclerViewAdapter : ContactRecyclerViewAdapter = ContactRecyclerViewAdapter(contacts.toMutableList())
    var simpleRecyclerViewAdapter : SimpleContactRecyclerViewAdapter = SimpleContactRecyclerViewAdapter(contacts.toMutableList())
    private val mPermissionList = arrayOf(
        Manifest.permission.READ_CONTACTS
    )
    
    override fun loadFinished(contacts: List<Contact>) {
        Log.d("MainActivity ", "contacts Size: ${contacts.size}")
        this.contacts.clear()
        this.contacts.addAll(contacts.toMutableList())
        val start = System.currentTimeMillis()
        
        //setSimpleRecyclerViewAdapter(contacts)
        
        //setContactListAdapter(contacts)
        
        setContactRecyclerViewAdapter(contacts)
        
		
        val runTime : Long = System.currentTimeMillis() - start
        Log.d("MAINACTIVITY", "Load Time : $runTime")

    }
    
    fun setContactListAdapter(contacts: List<Contact>){
        contactListAdapter.submitList(contacts)
        rvContact.adapter = contactListAdapter
    }
    
    fun setContactRecyclerViewAdapter(contacts: List<Contact>){
        contactRecyclerViewAdapter.swapItems(contacts.toMutableList())
        rvContact.adapter = contactRecyclerViewAdapter
    }
    fun setSimpleRecyclerViewAdapter(contacts: List<Contact>){
        simpleRecyclerViewAdapter.swapItems(contacts.toMutableList())
        rvContact.adapter = simpleRecyclerViewAdapter
    }

    lateinit var contactLoaderManager: ContactLoaderManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validatePermissions()
        contactLoaderManager = ContactLoaderManager(this@MainActivity,this@MainActivity)
        rvContact.layoutManager = LinearLayoutManager(this@MainActivity)
    
    }
    private fun validatePermissions(){
        val permissions = ArrayList<String>()
        for (permission in mPermissionList) {
            if (ContextCompat.checkSelfPermission(this@MainActivity, permission) == PERMISSION_GRANTED) {
                permissions.add(permission)
            }
        }
        if (permissions.size > 0) {
            ActivityCompat.requestPermissions(this@MainActivity, permissions.toTypedArray(), REQUEST_CODE)
        }
    }
    
    override fun onStart() {
        super.onStart()
        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_CONTACTS) == PERMISSION_GRANTED) {
            LoaderManager.getInstance(this).initLoader(INSTANCES_LIST_LOADER, null, contactLoaderManager)
        } else {
            Log.e("MainActivity", "Unable to get appointment, read calendar permission not granted")
        }
    }
    
    
    companion object {
        const val INSTANCES_LIST_LOADER = 1025
        const val REQUEST_CODE = 1
    }
    
    fun doSearch(view: View){
        val bundle = Bundle()
        bundle.putString(SEARCH,editText.text.toString())
	    ContactsFilter(contacts, this).filter(editText.text.toString())
        //LoaderManager.getInstance(this).restartLoader(INSTANCES_LIST_LOADER, bundle,contactLoaderManager)
    }
}

