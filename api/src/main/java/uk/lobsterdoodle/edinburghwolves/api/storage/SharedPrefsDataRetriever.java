package uk.lobsterdoodle.edinburghwolves.api.storage;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import uk.lobsterdoodle.edinburghwolves.core.model.CompletedFixture;
import uk.lobsterdoodle.edinburghwolves.core.model.Team;
import uk.lobsterdoodle.edinburghwolves.core.model.TeamStanding;

public class SharedPrefsDataRetriever {
    public static final String KEY_TEAM_NAMES = "KEY_TEAM_NAMES";

    private final SharedPreferences sharedPreferences;

    public SharedPrefsDataRetriever(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public CompletedFixture mostRecentGame() {
        return null;
    }

    public Team[] standings() {
        final Set<String> teamKeys = sharedPreferences.getStringSet(KEY_TEAM_NAMES, Collections.<String>emptySet());

        List<Team> teams = new ArrayList<>();
        for (String teamKey : teamKeys) {
            Team team = getTeam(teamKey);
            if (team.standingsDataComplete()) {
                teams.add(team);
            }
        }
        return teams.toArray(new Team[teams.size()]);
    }

    private Team getTeam(String teamName) {
        String positionKey = String.format("%s_position", teamName);
        String winsKey = String.format("%s_wins", teamName);
        String losesKey = String.format("%s_loses", teamName);
        String tiesKey = String.format("%s_ties", teamName);
        String percentageKey = String.format("%s_percentage", teamName);
        String goalsForKey = String.format("%s_goals_for", teamName);
        String goalsAgainstKey = String.format("%s_goals_against", teamName);

        String position = sharedPreferences.getString(positionKey, null);
        String wins = sharedPreferences.getString(winsKey, null);
        String loses = sharedPreferences.getString(losesKey, null);
        String ties = sharedPreferences.getString(tiesKey, null);
        String percentage = sharedPreferences.getString(percentageKey, null);
        String goalsFor = sharedPreferences.getString(goalsForKey, null);
        String goalsAgainst = sharedPreferences.getString(goalsAgainstKey, null);

        return new Team(teamName, new TeamStanding(position, wins, loses, ties, percentage, goalsFor, goalsAgainst));
    }
}
