package com.test.listoptimizationtest

import android.Manifest
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.LoaderManager
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker.PERMISSION_GRANTED
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.test.listoptimizationtest.loader.ContactLoaderManager
import com.test.listoptimizationtest.model.Contact
import java.util.*

class MainActivity : AppCompatActivity(), ContactLoaderManager.ContactLoaderCallback {

    private val mPermissionList = arrayOf(
        Manifest.permission.READ_CONTACTS
    )
    override fun loadFinished(contacts: List<Contact>) {
        Log.d("MainActivity ", "contacts Size: ${contacts.size}")
    }

    lateinit var contactLoaderManager: ContactLoaderManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validatePermissions()
        contactLoaderManager = ContactLoaderManager(this@MainActivity,this@MainActivity)


    }
    private fun validatePermissions(){
        val permissions = ArrayList<String>()
        for (permission in mPermissionList) {
            if (ContextCompat.checkSelfPermission(this@MainActivity, permission) !== PERMISSION_GRANTED) {
                permissions.add(permission)
            }
        }
        if (permissions.size > 0) {
            ActivityCompat.requestPermissions(this@MainActivity, permissions.toTypedArray(), REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_CONTACTS) === PERMISSION_GRANTED) {
            LoaderManager.getInstance(this).initLoader(INSTANCES_LIST_LOADER, null, contactLoaderManager)
        } else {
            Log.e("MainActivity", "Unable to get appointment, read calendar permission not granted")
        }
    }

    companion object {
        const val INSTANCES_LIST_LOADER = 1025
        const val REQUEST_CODE = 1
    }
}
