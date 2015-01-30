package uk.lobsterdoodle.edinburghwolves.core.api.model;

/** Created by Scott Laing
 *  on 29/09/2014 @ 01:02 */

public interface Game {
    public Team getHomeTeam();

    public Team getAwayTeam();

    public String getDate();
}
