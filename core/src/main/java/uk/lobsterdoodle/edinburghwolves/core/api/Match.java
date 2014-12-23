package uk.lobsterdoodle.edinburghwolves.core.api;

/** Created by Scott Laing
 *  on 29/09/2014 @ 00:55 */

public class Match implements Game {
    private Fixture mFixture;
    private int mHomeTeamScore;
    private int mAwayTeamScore;

    public Match(Fixture fixture, int homeTeamScore, int awayTeamScore) {
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
