package com.test.listoptimizationtest.loader

import android.content.Context
import android.database.Cursor
import android.location.Address
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import com.test.listoptimizationtest.SEARCH
import com.test.listoptimizationtest.model.Contact
import java.util.*

class ContactLoaderManager(val context: Context, val callback : ContactLoaderCallback): LoaderManager.LoaderCallbacks<Cursor> {
    internal val NAME_TEMPLATE = "%s %s"
    var search = ""
    interface ContactLoaderCallback{
        fun loadFinished(contacts: List<Contact> )
    }

    override fun onCreateLoader(p0: Int, bundle: Bundle?): Loader<Cursor> {
        if(bundle?.getString(SEARCH) != null){
            search =  bundle.getString(SEARCH)!!
        }
        
        val selection = ContactsContract.Data.MIMETYPE + " in (?, ?, ?) "
        val selectionArgs = arrayOf(
            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,
            ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE,
            ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE
        )

        val projection = arrayOf(
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.Data.MIMETYPE,
            ContactsContract.CommonDataKinds.StructuredName.PHOTO_ID,
            ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,
            ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME,
            ContactsContract.CommonDataKinds.Organization.COMPANY,
            ContactsContract.CommonDataKinds.StructuredPostal.DATA
        )

        val uri = ContactsContract.Data.CONTENT_URI
        val sortOrder = ContactsContract.Contacts.SORT_KEY_ALTERNATIVE
        return CursorLoader(context,uri,projection,selection,selectionArgs,sortOrder)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor?) {
        val linkedList = LinkedList<Contact>()
        if (cursor != null && cursor.getCount() > 0) {
            val idIdx = cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID)
            val mimeTypeIdx = cursor.getColumnIndex(ContactsContract.Data.MIMETYPE)
            val dataIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.DATA)
            val givenNameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME)
            val familyNameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME)
            //val companyNameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY)
            val photoIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHOTO_ID)

            while (cursor.moveToNext()) {

                val contactId = cursor.getLong(idIdx)
                val photoId = cursor.getLong(photoIdIdx)
                val givenName: String? = cursor.getString(givenNameIdx)
                val familyName: String? = cursor.getString(familyNameIdx)
                val data = cursor.getString(dataIdx)
                val mimeType = cursor.getString(mimeTypeIdx)
                

                if(!givenName.isNullOrEmpty() && !familyName.isNullOrEmpty() ) {
                    val item = Contact(contactId, givenName, familyName, photoId != null)
                    linkedList.add(item)
    
                    if (mimeType == ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE) {
                        val address = Address(Locale.getDefault())
                        address.setAddressLine(0, data)
                        address.featureName = String.format(Locale.US, NAME_TEMPLATE, givenName, familyName)
                        item.addressList.add(address)
                    }
                }
            }
        }
        callback.loadFinished(linkedList)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}