package uk.lobsterdoodle.edinburghwolves.network.player

import com.eightbitlab.rxbus.Bus
import com.google.firebase.firestore.FirebaseFirestore
import uk.lobsterdoodle.edinburghwolves.model.Player

class PlayersNetworkHandler {

    val store = FirebaseFirestore.getInstance()

    init {
        Bus.observe<FetchPlayersDocument>().subscribe { handleFetch(it) }
    }

    private fun handleFetch(fetch: FetchPlayersDocument) {
        store.collection(fetch.resource).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val ps: List<Player> = task.result.map { docSnapshot -> docSnapshot.toObject(Player::class.java) }
                Bus.send(PlayersCollection(ps))
            }
        }
    }
}