package uk.lobsterdoodle.edinburghwolves.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Created by Scott Laing
 *  on 29/09/2014 @ 01:38
 */

public class Division {
    private List<Team> teams = new ArrayList<>();
    private List<Game> games = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public List<OldFixture> getFixtures() {
        List<OldFixture> results = new ArrayList<>();
        for (Game game : games) {
            if (game.getClass() == OldFixture.class) {
                results.add((OldFixture) game);
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

    public List<Team> getTeams() {
        return Collections.unmodifiableList(teams);
    }
}
