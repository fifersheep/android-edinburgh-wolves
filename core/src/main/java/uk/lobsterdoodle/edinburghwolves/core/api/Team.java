package uk.lobsterdoodle.edinburghwolves.core.api;

/** Created by Scott Laing
 *  on 11/04/2014 @ 22:34 */

  public class Team {
    private final String name;
    private TeamStanding standing;

    public Team(String name, int position) {
        this.name = name;
        this.standing = new TeamStanding(String.valueOf(position), "0", "0", "0", "0", "0", "0");
    }

    public void setStanding(String position, String wins, String loses, String ties,
                            String percentage, String goalsFor, String goalsAgainst) {

        this.standing = new TeamStanding(position, wins, loses, ties, percentage, goalsFor, goalsAgainst);
    }

    public String getPosition() {
        return standing.getPosition();
    }

    public String getName() {
        return this.name;
    }

    public String getWins() {
        return standing.getWins();
    }

    public String getLoses() {
        return standing.getLoses();
    }

    public String getTies() {
        return standing.getTies();
    }

    public String getWinPercentage() {
        return standing.getPercentage();
    }

    public String getGoalsFor() {
        return standing.getGoalsFor();
    }

    public String getGoalsAgainst() {
        return standing.getGoalsAgainst();
    }
}