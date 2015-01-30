package uk.lobsterdoodle.edinburghwolves.core.api.model;

import java.util.ArrayList;
import java.util.List;

/** Created by Scott Laing
 *  on 29/09/2014 @ 01:38
 */

public class Division {
    private List<Team> teams = new ArrayList<Team>();
    private List<Game> games = new ArrayList<Game>();

    public List<Fixture> getFixtures() {
        List<Fixture> results = new ArrayList<>();
        for (Game game : games) {
            if (game.getClass() == Fixture.class) {
                results.add((Fixture) game);
            }
        }
        return results;
    }

    public List<CompletedFixture> getCompletedFixtures() {
        List<CompletedFixture> results = new ArrayList<>();
        for (Game game : games) {
            if (game.getClass() == CompletedFixture.class) {
                results.add((CompletedFixture) game);
            }
        }
        return results;
    }
}
