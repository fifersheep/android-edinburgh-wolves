package uk.lobsterdoodle.edinburghwolves.api.storage;

import android.content.SharedPreferences;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import uk.lobsterdoodle.edinburghwolves.core.model.Team;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SharedPrefsDataRetrieverTest {

    @Test
    public void test_that_the_standings_are_returned_if_present() throws Exception {
        SharedPreferences sharedPreferences = mock(SharedPreferences.class);
        Set<String> teamNames = teamNames();
        when(sharedPreferences.getStringSet(SharedPrefsDataRetriever.KEY_TEAM_NAMES, Collections.<String>emptySet())).thenReturn(teamNames);
        for (String teamName : teamNames) {
            mockResultsFor(sharedPreferences, teamName);
        }

        SharedPrefsDataRetriever retriever = new SharedPrefsDataRetriever(sharedPreferences);
        final Team[] standings = retriever.standings();

        final boolean result = containsNames(standings, teamNames);
        assertTrue(result);
    }

    private void mockResultsFor(SharedPreferences sharedPreferences, String teamName) {
        String positionKey = String.format("%s_position", teamName);
        String winsKey = String.format("%s_wins", teamName);
        String losesKey = String.format("%s_loses", teamName);
        String tiesKey = String.format("%s_ties", teamName);
        String percentageKey = String.format("%s_percentage", teamName);
        String goalsForKey = String.format("%s_goals_for", teamName);
        String goalsAgainstKey = String.format("%s_goals_against", teamName);

        when(sharedPreferences.getString(positionKey, null)).thenReturn("1");
        when(sharedPreferences.getString(winsKey, null)).thenReturn("8");
        when(sharedPreferences.getString(losesKey, null)).thenReturn("2");
        when(sharedPreferences.getString(tiesKey, null)).thenReturn("0");
        when(sharedPreferences.getString(percentageKey, null)).thenReturn(".784");
        when(sharedPreferences.getString(goalsForKey, null)).thenReturn("178");
        when(sharedPreferences.getString(goalsAgainstKey, null)).thenReturn("93");
    }

    @Test
    public void test_that_null_is_returned_if_standings_are_not_present() throws Exception {

    }

    @Test
    public void test_that_the_most_recent_game_is_returned_if_present() throws Exception {

    }

    @Test
    public void test_that_null_is_returned_if_most_recent_game_is_not_present() throws Exception {

    }

    private Set<String> teamNames() {
        return Sets.newSet(
                "Edinburgh Wolves",
                "Glasgow Tigers",
                "Clyde Valley Blackhawks");
    }

    private boolean containsNames(Team[] standings, Set<String> teamNames) {
        List<Team> teamList = Arrays.asList(standings);
        List<String> extractedTeamNames = new ArrayList<>();

        for (Team team : teamList) {
            extractedTeamNames.add(team.currentName());
        }

        return extractedTeamNames.containsAll(teamNames);
    }
}