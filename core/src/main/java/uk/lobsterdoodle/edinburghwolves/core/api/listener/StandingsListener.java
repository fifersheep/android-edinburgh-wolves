package uk.lobsterdoodle.edinburghwolves.core.api.listener;

import uk.lobsterdoodle.edinburghwolves.core.api.Team;

/**
 * Created by Scott Laing on 06-Jan-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public interface StandingsListener {
    public void addTeam(Team team);
    public void clearStandingsTable();
    public void setRecentGameHomeTeam(String teamName);
    public void setRecentGameAwayTeam(String teamName);
    public void setRecentGameHomeScore(String score);
    public void setRecentGameAwayScore(String score);
}
