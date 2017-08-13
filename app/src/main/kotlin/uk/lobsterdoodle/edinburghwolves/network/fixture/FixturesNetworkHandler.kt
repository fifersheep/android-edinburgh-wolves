package uk.lobsterdoodle.edinburghwolves.network.fixture

import com.eightbitlab.rxbus.Bus
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import uk.lobsterdoodle.edinburghwolves.model.Fixture
import uk.lobsterdoodle.edinburghwolves.network.ValueEventListenerAdapter

class FixturesNetworkHandler {

    val database = FirebaseDatabase.getInstance()

    init {
        Bus.observe<FetchFixturesDocument>().subscribe { handleFetch(it) }
    }

    private fun handleFetch(fetch: FetchFixturesDocument) {
        database.getReference(fetch.resource).addValueEventListener(object : ValueEventListenerAdapter() {
            override fun onDataChange(snapshot: DataSnapshot?) {
                if (snapshot != null) {
                    val fixtures = snapshot.getValue(object : GenericTypeIndicator<HashMap<String, Fixture>>() {})
                    Bus.send(FixturesDocument(fixtures))
                }
            }
        })
    }
}