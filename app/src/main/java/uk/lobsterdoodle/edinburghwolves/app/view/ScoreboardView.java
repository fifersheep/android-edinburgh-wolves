package uk.lobsterdoodle.edinburghwolves.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uk.lobsterdoodle.edinburghwolves.app.R;

public class ScoreboardView extends RelativeLayout {

    private TextView homeTeamName;
    private TextView homeTeamScore;
    private TextView awayTeamName;
    private TextView awayTeamScore;

    public ScoreboardView(Context context) {
        super(context);
        init(context);
    }

    public ScoreboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScoreboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.scoreboard_view, this, true);
        homeTeamName = (TextView) findViewById(R.id.textView_home_team);
        awayTeamName = (TextView) findViewById(R.id.textView_away_team);
        homeTeamScore = (TextView) findViewById(R.id.textView_home_score);
        awayTeamScore = (TextView) findViewById(R.id.textView_away_score);

        homeTeamName.setText(R.string.scoreboard_home_name_default);
        awayTeamName.setText(R.string.scoreboard_away_name_default);
        homeTeamScore.setText(R.string.scoreboard_score_default);
        awayTeamScore.setText(R.string.scoreboard_score_default);
    }

    public ScoreboardView setTeams(String homeTeamName, String awayTeamName) {
        this.homeTeamName.setText(homeTeamName);
        this.awayTeamName.setText(awayTeamName);
        return this;
    }

    public ScoreboardView updateScores(String homeTeamScore, String awayTeamScore) {
        this.homeTeamScore.setText(homeTeamScore);
        this.awayTeamScore.setText(awayTeamScore);
        return this;
    }
}
