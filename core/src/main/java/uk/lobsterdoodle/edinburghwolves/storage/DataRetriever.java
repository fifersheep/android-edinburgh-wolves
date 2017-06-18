package uk.lobsterdoodle.edinburghwolves.storage;

import uk.lobsterdoodle.edinburghwolves.core.model.CompletedFixture;
import uk.lobsterdoodle.edinburghwolves.core.model.Team;

public interface DataRetriever {
    Team[] standings();
    CompletedFixture mostRecentGame();
}
