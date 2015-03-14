package uk.lobsterdoodle.edinburghwolves.core.api.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@SuppressWarnings("SuspiciousMethodCalls")
public class DivisionTest {

    @Test
    public void get_teams_method_returns_all_teams() {
        List<Team> teams = new ArrayList<>();
        Team team1 = anyTeam();
        Team team2 = anyTeam();
        Team team3 = anyTeam();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        Division division = new Division();
        for (Team team : teams) {
            division.addTeam(team);
        }

        final List<Team> actual = division.getTeams();

        for(Team team : teams) {
            assertTrue(actual.contains(team));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void modifying_the_returned_list_from_get_teams_does_not_modify_the_division_teams() {
        List<Team> teams = new ArrayList<>();
        Team team1 = anyTeam();
        Team team2 = anyTeam();
        Team team3 = anyTeam();
        Team team4 = anyTeam();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        Division division = new Division();
        for (Team team : teams) {
            division.addTeam(team);
        }

        final List<Team> returnedTeams = division.getTeams();
        returnedTeams.add(team4);
        returnedTeams.remove(team3);

        final List<Team> actual = division.getTeams();

        assertTrue(actual.contains(team1));
        assertTrue(actual.contains(team2));
        assertTrue(actual.contains(team3));
        assertFalse(actual.contains(team4));
    }

    @Test
    public void get_fixtures_method_returns_only_incomplete_fixtures() {
        List<Game> games = new ArrayList<>();
        Fixture game1 = anyFixture();
        CompletedFixture game2 = anyCompletedFixture();
        Fixture game3 = anyFixture();
        games.add(game1);
        games.add(game2);
        games.add(game3);

        Division division = new Division();
        for (Game game : games) {
            division.addGame(game);
        }

        final List<Fixture> actual = division.getFixtures();

        assertTrue(actual.contains(game1));
        assertFalse(actual.contains(game2));
        assertTrue(actual.contains(game3));
    }

    @Test
    public void get_completed_fixtures_method_returns_only_completed_fixtures() {
        List<Game> games = new ArrayList<>();
        CompletedFixture game1 = anyCompletedFixture();
        CompletedFixture game2 = anyCompletedFixture();
        Fixture game3 = anyFixture();
        games.add(game1);
        games.add(game2);
        games.add(game3);

        Division division = new Division();
        for (Game game : games) {
            division.addGame(game);
        }

        final List<CompletedFixture> actual = division.getCompletedFixtures();

        assertTrue(actual.contains(game1));
        assertTrue(actual.contains(game2));
        assertFalse(actual.contains(game3));
    }

    private Team anyTeam() {
        return new Team("", mock(TeamStanding.class));
    }

    private Fixture anyFixture() {
        return new Fixture("", anyTeam(), anyTeam());
    }

    private CompletedFixture anyCompletedFixture() {
        return new CompletedFixture(anyFixture(), 0, 0);
    }

}