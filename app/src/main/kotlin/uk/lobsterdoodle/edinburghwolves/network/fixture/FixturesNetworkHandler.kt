package uk.lobsterdoodle.edinburghwolves.network.fixture

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import uk.lobsterdoodle.edinburghwolves.model.Fixture

class FixturesNetworkHandler {

    private var store = FirebaseFirestore.getInstance()

    fun getFixtures() : Observable<FixturesCollection> {
        val subject = PublishSubject.create<FixturesCollection>()
        store.collection("fixtures").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                subject.onNext(FixturesCollection(task.result
                        .map { docSnapshot -> docSnapshot.toObject(Fixture::class.java) }))
            }
        }
        return subject
    }
}