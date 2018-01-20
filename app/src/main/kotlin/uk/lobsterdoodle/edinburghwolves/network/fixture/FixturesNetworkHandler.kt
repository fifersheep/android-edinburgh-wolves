package uk.lobsterdoodle.edinburghwolves.network.fixture

import com.eightbitlab.rxbus.Bus
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import uk.lobsterdoodle.edinburghwolves.model.Fixture

class FixturesNetworkHandler {

    init {
        Bus.observe<FetchFixturesDocument>().subscribe { handleFetch(it) }
        FuelManager.instance.basePath = "https://us-central1-bafanl-d5f55.cloudfunctions.net"
    }

    private fun handleFetch(fetch: FetchFixturesDocument) {

        "/fixtures".httpGet().responseObject(Fixture.ListDeserializer()) { request, response, result ->
            val (fixtures, error) = result
            if (error == null && fixtures != null) {
                Bus.send(FixturesCollection(fixtures))
            } else {
                //error handling
            }
        }
    }
}

data class Doc(val fixtures: List<Fixture>) {
    class ListDeserializer : ResponseDeserializable<Doc> {
        override fun deserialize(content: String) = Gson().fromJson(content, Doc::class.java)!!
    }
}