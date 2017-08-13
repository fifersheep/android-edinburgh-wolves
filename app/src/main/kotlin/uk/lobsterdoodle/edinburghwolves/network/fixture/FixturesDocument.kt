package uk.lobsterdoodle.edinburghwolves.network.fixture

import uk.lobsterdoodle.edinburghwolves.model.Fixture

data class FixturesDocument(val payload: Map<String, Fixture>)
