package uk.lobsterdoodle.edinburghwolves.core.model;

/** Created by Scott Laing
 *  on 29/09/2014 @ 00:40 */

public class TeamStanding {

    private String position;
    private String wins;
    private String loses;
    private String ties;
    private String percentage;
    private String goalsFor;
    private String goalsAgainst;

    public TeamStanding(String position, String wins, String loses, String ties,
                        String percentage, String goalsFor, String goalsAgainst) {

        setStandings(position, wins, loses, ties, percentage, goalsFor, goalsAgainst);
    }

    public void setStandings(String position, String wins, String loses, String ties,
                             String percentage, String goalsFor, String goalsAgainst) {

        this.position = position;
        this.wins = wins;
        this.loses = loses;
        this.ties = ties;
        this.percentage = percentage;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public String getPosition() {
        return position;
    }

    public String getWins() {
        return wins;
    }

    public String getLoses() {
        return loses;
    }

    public String getTies() {
        return ties;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getGoalsFor() {
        return goalsFor;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamStanding)) return false;

        TeamStanding that = (TeamStanding) o;

        if (goalsAgainst != null ? !goalsAgainst.equals(that.goalsAgainst) : that.goalsAgainst != null) return false;
        if (goalsFor != null ? !goalsFor.equals(that.goalsFor) : that.goalsFor != null) return false;
        if (loses != null ? !loses.equals(that.loses) : that.loses != null) return false;
        if (percentage != null ? !percentage.equals(that.percentage) : that.percentage != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (ties != null ? !ties.equals(that.ties) : that.ties != null) return false;
        if (wins != null ? !wins.equals(that.wins) : that.wins != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (wins != null ? wins.hashCode() : 0);
        result = 31 * result + (loses != null ? loses.hashCode() : 0);
        result = 31 * result + (ties != null ? ties.hashCode() : 0);
        result = 31 * result + (percentage != null ? percentage.hashCode() : 0);
        result = 31 * result + (goalsFor != null ? goalsFor.hashCode() : 0);
        result = 31 * result + (goalsAgainst != null ? goalsAgainst.hashCode() : 0);
        return result;
    }
}
