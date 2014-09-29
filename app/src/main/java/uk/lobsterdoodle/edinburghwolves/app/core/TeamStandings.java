package uk.lobsterdoodle.edinburghwolves.app.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Created by Scott Laing
 *  on 29/09/2014 @ 00:40 */

public class TeamStandings {
    private List<Team> mTeams = new ArrayList<Team>();

    public void setStandings(List<Team> teams) {
        mTeams.clear();
        mTeams.addAll(teams);
    }

    public List<Team> getStandings() {
        return Collections.unmodifiableList(mTeams);
    }
}
