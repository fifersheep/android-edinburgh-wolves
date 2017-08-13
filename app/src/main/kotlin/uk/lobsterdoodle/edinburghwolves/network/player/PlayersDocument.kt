package uk.lobsterdoodle.edinburghwolves.network.player

import uk.lobsterdoodle.edinburghwolves.model.Player

data class PlayersDocument(val payload: Map<String, Player>)
