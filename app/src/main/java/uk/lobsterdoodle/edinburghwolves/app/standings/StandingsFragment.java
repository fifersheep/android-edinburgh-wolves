package uk.lobsterdoodle.edinburghwolves.app.standings;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import uk.lobsterdoodle.edinburghwolves.app.R;
import uk.lobsterdoodle.edinburghwolves.app.network.DivisionDataDownloadTask;
import uk.lobsterdoodle.edinburghwolves.core.api.model.Team;
import uk.lobsterdoodle.edinburghwolves.core.api.listener.StandingsListener;
import uk.lobsterdoodle.edinburghwolves.core.api.presenter.StandingsPresenter;
import uk.lobsterdoodle.edinburghwolves.core.api.presenter.WolvesStandingsPresenter;

public class StandingsFragment extends Fragment implements StandingsListener {

    private static String TAG = StandingsFragment.class.getSimpleName();
    private StandingsPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getActivity();

        presenter = new WolvesStandingsPresenter(this);

        if (networkIsAvailable()) {
            //ExecutorService executor = Executors.newSingleThreadExecutor();
            new DivisionDataDownloadTask(presenter).execute();
            //executor.execute(divisionDataRunner);
        } else {
            Toast.makeText(context, "Internet connection unavailable. Standings data has not been updated", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.standings, container, false);
    }

    protected boolean networkIsAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    @Override
    public void addTeam(Team team) {
        final LinearLayout standingsTable = (LinearLayout) getActivity().findViewById(R.id.standings_table);
        final StandingsRow row = new StandingsRow(getActivity());
        row.createWith(team);
        standingsTable.addView(row);
    }

    @Override
    public void clearStandingsTable() {
        final LinearLayout standingsTable = (LinearLayout) getActivity().findViewById(R.id.standings_table);
        standingsTable.removeAllViews();
    }

    @Override
    public void setRecentGameHomeTeam(String teamName) {
        TextView homeTeam = (TextView) getActivity().findViewById(R.id.textView_home_team);
        homeTeam.setText(teamName);
    }

    @Override
    public void setRecentGameAwayTeam(String teamName) {
        TextView awayTeam = (TextView) getActivity().findViewById(R.id.textView_away_team);
        awayTeam.setText(teamName);
    }

    @Override
    public void setRecentGameHomeScore(String score) {
        TextView homeScore = (TextView) getActivity().findViewById(R.id.textView_home_score);
        homeScore.setText(score);
    }

    @Override
    public void setRecentGameAwayScore(String score) {
        TextView awayScore = (TextView) getActivity().findViewById(R.id.textView_away_score);
        awayScore.setText(score);
    }
}
