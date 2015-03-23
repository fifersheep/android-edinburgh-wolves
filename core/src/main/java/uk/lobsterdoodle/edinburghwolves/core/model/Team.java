package uk.lobsterdoodle.edinburghwolves.core.model;

/** Created by Scott Laing
 *  on 11/04/2014 @ 22:34 */

  public class Team {
    private final String name;
    private TeamStanding standing;

    public Team(String name, TeamStanding standing) {
        this.name = name;
        this.standing = standing;
    }

    public void changeStanding(String position, String wins, String loses, String ties,
                               String percentage, String goalsFor, String goalsAgainst) {

        standing.setStandings(position, wins, loses, ties, percentage, goalsFor, goalsAgainst);
    }

    public String currentPosition() {
        return standing.getPosition();
    }

    public String currentName() {
        return this.name;
    }

    public String currentWins() {
        return standing.getWins();
    }

    public String currentLoses() {
        return standing.getLoses();
    }

    public String currentTies() {
        return standing.getTies();
    }

    public String currentWinPercentage() {
        return standing.getPercentage();
    }

    public String currentGoalsFor() {
        return standing.getGoalsFor();
    }

    public String currentGoalsAgainst() {
        return standing.getGoalsAgainst();
    }

    public boolean standingsDataComplete() {
        return (standing.getPosition() != null &&
                standing.getWins() != null &&
                standing.getLoses() != null &&
                standing.getTies() != null &&
                standing.getPercentage() != null &&
                standing.getGoalsFor() != null &&
                standing.getGoalsAgainst() != null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        return !(name != null ? !name.equals(team.name) : team.name != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (standing != null ? standing.hashCode() : 0);
        return result;
    }
}