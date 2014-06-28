package uk.lobsterdoodle.edinburghwolves.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeZone;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;

public class StandingsFragment extends Fragment {

    TextView mTvData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getActivity();

        if (isNetworkAvailable()) {
            GetDivisionData getDivisionData = new GetDivisionData();
            getDivisionData.execute();
        } else {
            Toast.makeText(context, "Internet connection unavailable. Standings data has not been updated", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wolves, container, false);
    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    private class GetDivisionData extends AsyncTask<Void, Void, ArrayList<Team>> {
        String mostRecentScore = "0 - 0";
        String mostRecentCompetitor = "Glasgow Tigers";
        Boolean areWolvesHome = true;

        @Override
        protected ArrayList<Team> doInBackground(Void... arg0) {
            final String url = "http://www.bafanl.co.uk/team/43";
            ArrayList<Team> standings = new ArrayList<Team>();

            try {
                Document doc = Jsoup.connect(url).timeout(30 * 1000).get();
                getStandingsTable(standings, doc);
                getMostRecentGameResults(doc);
            } catch (IOException e) {
                mTvData.setText("IOException: " + e);
                e.printStackTrace();
            }
            return standings;
        }

        @Override
        protected void onPostExecute(ArrayList<Team> standings) {

            setScoreboard();
            setStandings(standings);
            saveStandings(standings);
        }

        private void getTwitterPost() {
            try {
                URL twitterFeedUrl = new URL("https://api.twitter.com/1/statuses/user_timeline.json?id=EdinburghWolves&count=1");
            } catch (MalformedURLException ex) {
                Log.e(MainActivity.TAG, "Malformed URL Exception: " + ex);
            }
        }

        private void getStandingsTable(ArrayList<Team> teams, Document doc) {
            // Pulls standings table data
            Element standingsTable = doc.select("table[class=footable]").first();
            Elements rows = standingsTable.select("tr");

            // For each row (except header), create a new 'Team' object
            for (Element row : rows.subList(1, rows.size())) {
                Elements tds = row.select("td");
                teams.add(new Team(tds.get(0).text(), tds.get(1).text(),
                        tds.get(2).text(), tds.get(3).text(), tds.get(4)
                        .text(), tds.get(5).text(), tds.get(6)
                        .text()));
            }
        }

        private void setScoreboard() {
            HashMap<String, String> teamCodes = new HashMap<String, String>();
            teamCodes.put("Clyde Valley Blackhawks", "CLY");
            teamCodes.put("Aberdeen Roughnecks", "ABD");
            teamCodes.put("Dundee Hurricanes", "DUN");
            teamCodes.put("Edinburgh Wolves", "EDI");
            teamCodes.put("West Coast Trojans", "WCT");
            teamCodes.put("Glasgow Tigers", "GLA");

            Pattern p = Pattern.compile(" - ");
            String[] items = p.split(mostRecentScore);
            String leftScore = items[0];
            String rightScore = items[1];

            TextView tvLeftTeam = (TextView)getView().findViewById(R.id.textView_home_team);
            TextView tvRightTeam = (TextView)getView().findViewById(R.id.textView_away_team);
            TextView tvLeftScore = (TextView)getView().findViewById(R.id.textView_left_score);
            TextView tvRightScore = (TextView)getView().findViewById(R.id.textView_right_score);

            tvLeftTeam.setText(teamCodes.get("Edinburgh Wolves"));
            tvRightTeam.setText(teamCodes.get(mostRecentCompetitor));

            if (!areWolvesHome) {
                rightScore = items[0];
                leftScore = items[1];
                TextView tvVs = (TextView)getView().findViewById(R.id.textView_vs);
                tvVs.setText("@");
            }

            tvLeftScore.setText(leftScore);
            tvRightScore.setText(rightScore);
        }

        private void setStandings(ArrayList<Team> teams) {
            for (int i = 0; i < 6; i++) {
                String nameTarget = ("textView_standingsName_" + (i + 1));
                String wldTarget = ("textView_standingsWins_" + (i + 1));
                String pctTarget = ("textView_standingsPct_" + (i + 1));
                String foraggTarget = ("textView_standingsForagg_" + (i + 1));
                String packagePath = "uk.lobsterdoodle.edinburghwolves.app";
                Team team = teams.get(i);

                int nameId = getResources().getIdentifier(nameTarget, "id", packagePath);
                TextView tvName = (TextView)getView().findViewById(nameId);

                int wldId = getResources().getIdentifier(wldTarget, "id", packagePath);
                TextView tvWld = (TextView)getView().findViewById(wldId);

                int pctId = getResources().getIdentifier(pctTarget, "id", packagePath);
                TextView tvPct = (TextView)getView().findViewById(pctId);

                int foraggId = getResources().getIdentifier(foraggTarget, "id", packagePath);
                TextView tvForagg = (TextView)getView().findViewById(foraggId);

                tvName.setText(team.getName());
                tvWld.setText(team.getWinsLosesTies());
                tvPct.setText(team.getPct());
                tvForagg.setText(team.getGoalsForAndAgainst());
            }
        }

        private void getMostRecentGameResults(Document doc) {
            // Pulls most recent game result

            Element fixturesDiv = doc.select("div#fixtures").first();
            Elements listItems = fixturesDiv.select("li");


            for (Element listItem : listItems) {
                String dateString = listItem.select("span").first().text() + "/2013";
                String score;
                try {
                    score = ((TextNode) listItem.childNode(4)).text();
                } catch(IndexOutOfBoundsException ex) {
                    score = ((TextNode) listItem.childNode(3)).text();
                }
                try {
                    Date pulledDate = new SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH).parse(dateString);
                    DateTime pulledDateTime = new DateTime(pulledDate, DateTimeZone.getDefault());
                    DateTime todaysDateTime = DateTime.now();
                    DateTimeComparator comparator = DateTimeComparator.getDateOnlyInstance();
                    int comparatorResult = comparator.compare(pulledDateTime, todaysDateTime);
                    if (comparatorResult == -1 && !score.equals(" ")) { //If date is in the past...
                        mostRecentScore = score.substring(5, (score.length() - 1));
                        mostRecentCompetitor = listItem.select("a").first().text();
                        areWolvesHome = !listItem.text().contains("@");
                    }
                } catch (ParseException e) {
                    Log.e(MainActivity.TAG, "Parse Exception: " + e);
                    e.printStackTrace();
                }
            }
        }

        private void saveStandings(ArrayList<Team> teams) {
            //Context context = getActivity();
            //SharedPreferences sharedPref = context.getSharedPreferences(
            //        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        }
    }
}
