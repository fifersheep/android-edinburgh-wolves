package uk.lobsterdoodle.edinburghwolves.api.presenter;

import uk.lobsterdoodle.edinburghwolves.core.model.CompletedFixture;
import uk.lobsterdoodle.edinburghwolves.core.model.Team;

/**
 * Created by Scott Laing on 05-Jan-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public interface StandingsPresenter {
    public void displaySavedMostRecentGame();
    public void displaySavedStandings();
    public void displayStandings(Team[] teams);
    public void displayMostRecentGame(CompletedFixture completedFixture);
}