package uk.lobsterdoodle.edinburghwolves.model

data class Fixture(
        val conference: String,
        val week: String,
        val date: String,
        val status: FixtureStatus,
        val home: FixtureTeam,
        val away: FixtureTeam
)

data class FixtureTeam(
        val score: String,
        val team: String
)

enum class FixtureStatus { scheduled, started, finished }