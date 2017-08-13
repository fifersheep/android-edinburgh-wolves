package uk.lobsterdoodle.edinburghwolves.network.player

import com.eightbitlab.rxbus.Bus
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import uk.lobsterdoodle.edinburghwolves.model.Player
import uk.lobsterdoodle.edinburghwolves.network.ValueEventListenerAdapter

class PlayersNetworkHandler {

    val database = FirebaseDatabase.getInstance()

    init {
        Bus.observe<FetchPlayersDocument>().subscribe { handleFetch(it) }
    }

    private fun handleFetch(fetch: FetchPlayersDocument) {
        database.getReference(fetch.resource).addValueEventListener(object : ValueEventListenerAdapter() {
            override fun onDataChange(snapshot: DataSnapshot?) {
                if (snapshot != null) {
                    val players = snapshot.getValue(object : GenericTypeIndicator<HashMap<String, Player>>() {})
                    Bus.send(PlayersDocument(players))
                }
            }
        })
    }
}