package uk.lobsterdoodle.edinburghwolves.core.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeamStandingTest {

    private final String anyPosition = "1";
    private final String anyWins = "8";
    private final String anyLoses = "2";
    private final String anyTies = "0";
    private final String anyPercentage = "0.735";
    private final String anyGoalsFor = "142";
    private final String anyGoalsAgainst = "78";

    @Test
    public void creating_new_team_standings_sets_the_correct_standings() {
        TeamStanding standing = new TeamStanding(anyPosition, anyWins, anyLoses, anyTies, anyPercentage, anyGoalsFor, anyGoalsAgainst);

        assertEquals(anyPosition, standing.getPosition());
        assertEquals(anyWins, standing.getWins());
        assertEquals(anyLoses, standing.getLoses());
        assertEquals(anyTies, standing.getTies());
        assertEquals(anyPercentage, standing.getPercentage());
        assertEquals(anyGoalsFor, standing.getGoalsFor());
        assertEquals(anyGoalsAgainst, standing.getGoalsAgainst());
    }

    @Test
    public void set_standings_method_sets_the_correct_standings() {
        TeamStanding standing = new TeamStanding(null, null, null, null, null, null, null);
        standing.setStandings(anyPosition, anyWins, anyLoses, anyTies, anyPercentage, anyGoalsFor, anyGoalsAgainst);

        assertEquals(anyPosition, standing.getPosition());
        assertEquals(anyWins, standing.getWins());
        assertEquals(anyLoses, standing.getLoses());
        assertEquals(anyTies, standing.getTies());
        assertEquals(anyPercentage, standing.getPercentage());
        assertEquals(anyGoalsFor, standing.getGoalsFor());
        assertEquals(anyGoalsAgainst, standing.getGoalsAgainst());
    }

}