package uk.lobsterdoodle.edinburghwolves.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

data class Fixture(
        val conference: String = "",
        val week: String = "",
        val date: String = "",
        val status: FixtureStatus = FixtureStatus.unknown,
        val home: FixtureTeam = FixtureTeam(),
        val away: FixtureTeam = FixtureTeam()
) {
    class Deserializer : ResponseDeserializable<Fixture> {
        override fun deserialize(content: String): Fixture = Gson().fromJson(content, Fixture::class.java)
    }

    class ListDeserializer : ResponseDeserializable<List<Fixture>> {
        override fun deserialize(reader: Reader): List<Fixture> {
            return Gson().fromJson(reader, object : TypeToken<List<Fixture>>(){}.type)
        }
    }
}

data class FixtureTeam(
        val score: String = "",
        val team: Team = Team()
)

enum class FixtureStatus { unknown, scheduled, started, finished }
