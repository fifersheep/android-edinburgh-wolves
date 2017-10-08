package uk.lobsterdoodle.edinburghwolves.network.fixture

import com.eightbitlab.rxbus.Bus
import com.google.firebase.firestore.FirebaseFirestore
import uk.lobsterdoodle.edinburghwolves.model.Fixture

class FixturesNetworkHandler {

    var store = FirebaseFirestore.getInstance()

    init {
        Bus.observe<FetchFixturesDocument>().subscribe { handleFetch(it) }
    }

    private fun handleFetch(fetch: FetchFixturesDocument) {
        store.collection(fetch.resource).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val fs : List<Fixture> = task.result.map { docSnapshot -> docSnapshot.toObject(Fixture::class.java) }
                Bus.send(FixturesCollection(fs))
            }
        }
    }
}