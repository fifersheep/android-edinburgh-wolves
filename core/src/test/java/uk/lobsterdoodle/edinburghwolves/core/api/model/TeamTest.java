package uk.lobsterdoodle.edinburghwolves.core.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class TeamTest {

    private final String anyPosition = "1";
    private final String anyWins = "8";
    private final String anyLoses = "2";
    private final String anyTies = "0";
    private final String anyPercentage = "0.735";
    private final String anyGoalsFor = "142";
    private final String anyGoalsAgainst = "78";

    @Test
    public void creating_a_team_creates_it_with_the_correct_info() {
        Team team = new Team("Edinburgh Wolves", anyTeamStanding());

        assertEquals("Edinburgh Wolves", team.currentName());
        assertEquals(anyPosition, team.currentPosition());
        assertEquals(anyWins, team.currentWins());
        assertEquals(anyLoses, team.currentLoses());
        assertEquals(anyTies, team.currentTies());
        assertEquals(anyPercentage, team.currentWinPercentage());
        assertEquals(anyGoalsFor, team.currentGoalsFor());
        assertEquals(anyGoalsAgainst, team.currentGoalsAgainst());
    }

    @Test
    public void set_standings_method_sets_the_correct_standings() {
        TeamStanding standing = mock(TeamStanding.class);

        Team team = new Team(null, standing);
        team.changeStanding(anyPosition, anyWins, anyLoses, anyTies, anyPercentage, anyGoalsFor, anyGoalsAgainst);

        verify(standing).setStandings(anyPosition, anyWins, anyLoses, anyTies, anyPercentage, anyGoalsFor, anyGoalsAgainst);
    }

    private TeamStanding anyTeamStanding() {
        TeamStanding standings = mock(TeamStanding.class);
        stub(standings.getPosition()).toReturn(anyPosition);
        stub(standings.getWins()).toReturn(anyWins);
        stub(standings.getLoses()).toReturn(anyLoses);
        stub(standings.getTies()).toReturn(anyTies);
        stub(standings.getPercentage()).toReturn(anyPercentage);
        stub(standings.getGoalsFor()).toReturn(anyGoalsFor);
        stub(standings.getGoalsAgainst()).toReturn(anyGoalsAgainst);
        return standings;
    }

}