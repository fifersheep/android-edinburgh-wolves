package uk.lobsterdoodle.edinburghwolves.app.core;

/** Created by Scott Laing
 *  on 11/04/2014 @ 22:34 */

  public class Team {
    private String name;
    private String wins;
    private String loses;
    private String ties;
    private String pct;
    private String goalsFor;
    private String goalsAgainst;

    public Team(String name) {
        this(name, "0", "0", "0", "0", "0", "0");
    }

    public Team(String n, String w, String l, String t, String p, String f, String a) {
        this.name = n;
        this.wins = w;
        this.loses = l;
        this.ties = t;
        this.pct = p;
        this.goalsFor = f;
        this.goalsAgainst = a;
    }

    public String getName() {
        return this.name;
    }

    public String getWins() {
        return this.wins;
    }

    public String getLoses() {
        return this.loses;
    }

    public String getTies() {
        return this.ties;
    }

    public String getWinsLosesTies() {
        return this.wins + "-" + this.loses + "-" + this.ties;
    }

    public String getPct() {
        return this.pct;
    }

    public String getGoalsFor() {
        return this.goalsFor;
    }

    public String getGoalsAgainst() {
        return this.goalsAgainst;
    }

    public String getGoalsForAndAgainst() {
        return this.goalsFor + " / " + this.goalsAgainst;
    }

    public String[] getStandings() {
        return new String[] { this.wins, this.loses, this.ties, this.pct, this.goalsFor, this.goalsAgainst };
    }
}