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
}
