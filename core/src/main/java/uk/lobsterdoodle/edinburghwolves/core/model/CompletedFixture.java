package uk.lobsterdoodle.edinburghwolves.core.model;

/** Created by Scott Laing
 *  on 29/09/2014 @ 00:55 */

public class CompletedFixture implements Game {
    private OldFixture mFixture;
    private int mHomeTeamScore;
    private int mAwayTeamScore;

    public CompletedFixture(OldFixture fixture, int homeTeamScore, int awayTeamScore) {
        this.mFixture = fixture;
        this.mHomeTeamScore = homeTeamScore;
        this.mAwayTeamScore = awayTeamScore;
    }

    public int getHomeTeamScore() {
        return mHomeTeamScore;
    }

    public int getAwayTeamScore() {
        return mAwayTeamScore;
    }

    @Override
    public Team getHomeTeam() {
        return mFixture.getHomeTeam();
    }

    @Override
    public Team getAwayTeam() {
        return mFixture.getAwayTeam();
    }

    @Override
    public String getDate() {
        return mFixture.getDate();
    }
}
