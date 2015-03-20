package uk.lobsterdoodle.edinburghwolves.core.api.presenter;

import uk.lobsterdoodle.edinburghwolves.core.api.data.SharedPrefsDataRetriever;
import uk.lobsterdoodle.edinburghwolves.core.api.listener.StandingsListener;
import uk.lobsterdoodle.edinburghwolves.core.api.model.CompletedFixture;
import uk.lobsterdoodle.edinburghwolves.core.api.model.Team;

/**
 * Created by Scott Laing on 05-Jan-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public class WolvesStandingsPresenter implements StandingsPresenter {

    private final StandingsListener listener;
    private final SharedPrefsDataRetriever dataRetriever;

    public WolvesStandingsPresenter(StandingsListener listener, SharedPrefsDataRetriever dataRetriever) {
        this.listener = listener;
        this.dataRetriever = dataRetriever;
    }

    @Override
    public void displaySavedMostRecentGame() {
        CompletedFixture game = dataRetriever.mostRecentGame();
        if (game != null) {
            displayMostRecentGame(game);
        }
    }

    @Override
    public void displaySavedStandings() {
        Team[] teams = dataRetriever.standings();
        if (teams != null && teams.length > 0) {
            displayStandings(teams);
        }
    }

    @Override
    public void displayStandings(Team[] teams) {
        listener.clearStandingsTable();
        for (Team team : teams) {
            listener.addTeam(team);
        }
    }

    @Override
    public void displayMostRecentGame(CompletedFixture game) {
        final String homeTeam = lastWordOf(game.getHomeTeam().currentName());
        final String awayTeam = lastWordOf(game.getAwayTeam().currentName());

        listener.setRecentGameHomeTeam(homeTeam);
        listener.setRecentGameAwayTeam(awayTeam);

        listener.setRecentGameHomeScore(String.valueOf(game.getHomeTeamScore()));
        listener.setRecentGameAwayScore(String.valueOf(game.getAwayTeamScore()));
    }

    private String lastWordOf(String original) {
        final int beginIndex = original.lastIndexOf(" ") + 1;
        return original.substring(beginIndex);
    }
}
