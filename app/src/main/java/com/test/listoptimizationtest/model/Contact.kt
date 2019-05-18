package com.test.listoptimizationtest.model

import android.location.Address

data class Contact(val id: Long, val name: String, val surename : String,val hasPhoto : Boolean){
    var addressList= ArrayList<Address>()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as Contact
        
        if (id != other.id) return false
        if (name != other.name) return false
        if (surename != other.surename) return false
        if (hasPhoto != other.hasPhoto) return false
        if (addressList != other.addressList) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + surename.hashCode()
        result = 31 * result + hasPhoto.hashCode()
        result = 31 * result + addressList.hashCode()
        return result
    }
    
    
}