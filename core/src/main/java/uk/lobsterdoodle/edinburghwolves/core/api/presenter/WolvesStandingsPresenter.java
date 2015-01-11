package uk.lobsterdoodle.edinburghwolves.core.api.presenter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeZone;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;

import uk.lobsterdoodle.edinburghwolves.core.api.CompletedFixture;
import uk.lobsterdoodle.edinburghwolves.core.api.Fixture;
import uk.lobsterdoodle.edinburghwolves.core.api.Team;
import uk.lobsterdoodle.edinburghwolves.core.api.listener.StandingsListener;

/**
 * Created by Scott Laing on 05-Jan-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public class WolvesStandingsPresenter implements StandingsPresenter {

    final private int DATE_IN_PAST = -1;

    private final StandingsListener listener;

    public WolvesStandingsPresenter(StandingsListener listener) {
        this.listener = listener;
    }

    @Override
    public void displayStandings(Document document) {
        Element standingsTable = document.select("table[class=footable]").first();
        Elements rows;
        if (standingsTable != null) {
            rows = standingsTable.select("tr");
            for (Element row : rows.subList(1, rows.size())) {
                Elements tds = row.select("td");

                Team team = new Team(tds.get(0).text(), 2);
                listener.addTeam(team);
            }
        }
    }

    @Override
    public void displayMostRecentGame(Document document) {
        HashMap<String, String> teamCodes = new HashMap<String, String>();
        teamCodes.put("Clyde Valley Blackhawks", "CLY");
        teamCodes.put("Aberdeen Roughnecks", "ABD");
        teamCodes.put("Dundee Hurricanes", "DUN");
        teamCodes.put("Edinburgh Wolves", "EDI");
        teamCodes.put("West Coast Trojans", "WCT");
        teamCodes.put("Glasgow Tigers", "GLA");

        CompletedFixture game = getMostRecentGameResults(document);
        final String homeTeam = teamCodes.get(game.getHomeTeam().getName());
        final String awayTeam = teamCodes.get(game.getAwayTeam().getName());


        listener.setRecentGameHomeTeam(homeTeam);
        listener.setRecentGameAwayTeam(awayTeam);

        listener.setRecentGameHomeScore(String.valueOf(game.getHomeTeamScore()));
        listener.setRecentGameAwayScore(String.valueOf(game.getAwayTeamScore()));
    }

    private CompletedFixture getMostRecentGameResults(Document doc) {

        Fixture fixture;
        CompletedFixture completedFixture = null;

        // Pulls most recent game result

        Element fixturesDiv = doc.select("div#fixtures").first();
        Elements listItems = fixturesDiv.select("li");

        for (Element listItem : listItems) {
            String dateString = listItem.select("span").first().text() + "/2013";
            String competitor = listItem.select("a").first().text();
            boolean wolvesAreAtHome = !listItem.text().contains("@");
            String mostRecentScore = null;
            Team homeTeam;
            Team awayTeam;

            if (wolvesAreAtHome) {
                homeTeam = new Team("Edinburgh Wolves", 0);
                awayTeam = new Team(competitor, 0);
            } else {
                homeTeam = new Team(competitor, 0);
                awayTeam = new Team("Edinburgh Wolves", 0);
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
}
