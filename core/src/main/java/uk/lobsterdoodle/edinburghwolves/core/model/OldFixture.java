package uk.lobsterdoodle.edinburghwolves.core.model;

/** Created by Scott Laing
 *  on 29/09/2014 @ 00:48 */

public class OldFixture implements Game {
    private final Team mHomeTeam;
    private final Team mAwayTeam;
    private final String mDate;

    public OldFixture(String date, Team homeTeam, Team awayTeam) {
        this.mDate = date;
        this.mHomeTeam = homeTeam;
        this.mAwayTeam = awayTeam;
    }

    @Override
    public Team getHomeTeam() {
        return mHomeTeam;
    }

    @Override
    public Team getAwayTeam() {
        return mAwayTeam;
    }

    @Override
    public String getDate() {
        return mDate;
    }
}
