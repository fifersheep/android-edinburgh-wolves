package uk.lobsterdoodle.edinburghwolves.network.player

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import uk.lobsterdoodle.edinburghwolves.model.Player

class PlayersNetworkHandler {

    private val store = FirebaseFirestore.getInstance()

    fun getPlayers(): Observable<PlayersCollection> {
        val subject = PublishSubject.create<PlayersCollection>()
        store.collection("players").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                subject.onNext(PlayersCollection(task.result
                        .map { docSnapshot -> docSnapshot.toObject(Player::class.java) }))
            }
        }
        return subject
    }
}