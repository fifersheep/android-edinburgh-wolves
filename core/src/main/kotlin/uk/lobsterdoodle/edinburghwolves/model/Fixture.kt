package uk.lobsterdoodle.edinburghwolves.model

data class Fixture(
        val conference: String = "",
        val week: String = "",
        val date: String = "",
        val status: FixtureStatus = FixtureStatus.unknown,
        val home: FixtureTeam = FixtureTeam(),
        val away: FixtureTeam = FixtureTeam()
)

data class FixtureTeam(
        val score: String = "",
        val team: Team = Team()
)

enum class FixtureStatus { unknown, scheduled, started, finished }
