package uk.lobsterdoodle.edinburghwolves.network

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

open class ValueEventListenerAdapter : ValueEventListener {
    override fun onCancelled(p0: DatabaseError?) { }
    override fun onDataChange(p0: DataSnapshot?) { }
}