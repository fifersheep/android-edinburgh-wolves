package uk.lobsterdoodle.edinburghwolves.core.data;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeZone;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import uk.lobsterdoodle.edinburghwolves.core.model.CompletedFixture;
import uk.lobsterdoodle.edinburghwolves.core.model.Fixture;
import uk.lobsterdoodle.edinburghwolves.core.model.Team;
import uk.lobsterdoodle.edinburghwolves.core.model.TeamStanding;

public class StandingsDataExtractor {
    private final Document document;

    public StandingsDataExtractor(Document document) {
        this.document = document;
    }

    public Team[] getTeams() {
        List<Team> teams = new ArrayList<>();
        final Elements rows = getStandingsRowsFrom(document);

        if (rows != null) {
            for (Element row : rows.subList(1, rows.size())) {
                final Team team = extractTeamFrom(rows, row);
                teams.add(team);
            }
        }

        return (Team[]) teams.toArray();
    }

    public CompletedFixture mostRecentGame() {
        Fixture fixture;
        CompletedFixture completedFixture = null;

        Element fixturesDiv = document.select("div#fixtures").first();
        Elements listItems = fixturesDiv.select("li");

        for (Element listItem : listItems) {
            String dateString = listItem.select("span").first().text() + "/2013";
            String competitor = listItem.select("a").first().text();
            boolean wolvesAreAtHome = !listItem.text().contains("@");
            String mostRecentScore = null;
            Team homeTeam;
            Team awayTeam;

            if (wolvesAreAtHome) {
                homeTeam = new Team("Edinburgh Wolves", null);
                awayTeam = new Team(competitor, null);
            } else {
                homeTeam = new Team(competitor, null);
                awayTeam = new Team("Edinburgh Wolves", null);
            }

            fixture = new Fixture(dateString, homeTeam, awayTeam);

            String scoreData;

            try {
                scoreData = ((TextNode) listItem.childNode(4)).text();
            } catch(IndexOutOfBoundsException ex) {
                scoreData = ((TextNode) listItem.childNode(3)).text();
            }

            mostRecentScore = getMostRecentScore(scoreData, dateString);

            Pattern p = Pattern.compile(" - ");
            String[] items = p.split(mostRecentScore);
            String homeScore = items[0];
            String awayScore = items[1];

            completedFixture = new CompletedFixture(fixture, Integer.parseInt(homeScore), Integer.parseInt(awayScore));
        }

        return completedFixture;
    }

    private String getMostRecentScore(String scoreData, String dateString) {
        int DATE_IN_PAST = -1;
        String result = null;
        Date pulledDate = null;
        try {
            pulledDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateTime matchDate = new DateTime(pulledDate, DateTimeZone.UTC);
        DateTime currentDate = DateTime.now();

        DateTimeComparator comparator = DateTimeComparator.getDateOnlyInstance();
        int comparatorResult = comparator.compare(matchDate, currentDate);
        if (comparatorResult == DATE_IN_PAST && !scoreData.equals(" ")) {
            result = scoreData.substring(5, (scoreData.length() - 1));
        }
        return result;
    }

    private Team extractTeamFrom(Elements rows, Element row) {
        String position = String.valueOf(rows.indexOf(row));
        Elements tds = row.select("td");

        return new Team(tds.get(0).text(), new TeamStanding(
                position,
                tds.get(1).text(),
                tds.get(2).text(),
                tds.get(3).text(),
                tds.get(4).text(),
                tds.get(5).text(),
                tds.get(6).text()));
    }

    private Elements getStandingsRowsFrom(Document document) {
        Element standingsTable = document.select("table[class=footable]").first();
        Elements rows = null;
        if (standingsTable != null) {
            rows = standingsTable.select("tr");
        }
        return rows;
    }
}
