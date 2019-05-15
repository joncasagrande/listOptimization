package com.test.listoptimizationtest.model

import android.location.Address

data class Contact(val id: Long, val name: String, val surename : String, val hasPhoto : Boolean){
    var addressList= ArrayList<Address>()
}