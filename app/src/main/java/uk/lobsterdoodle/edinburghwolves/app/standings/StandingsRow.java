package uk.lobsterdoodle.edinburghwolves.app.standings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uk.lobsterdoodle.edinburghwolves.app.R;
import uk.lobsterdoodle.edinburghwolves.core.api.StandingsView;
import uk.lobsterdoodle.edinburghwolves.core.api.Team;

/**
 * Created by Scott Laing on 06-Jan-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public class StandingsRow extends RelativeLayout implements StandingsView {

    private Context context;

    public StandingsRow(Context context) {
        super(context);
        this.context = context;
    }

    public void createWith(Team team) {
        final View layout = LayoutInflater.from(context).inflate(R.layout.standings_table_item, this, true);

        TextView positionTextView = (TextView) layout.findViewById(R.id.standings_table_position);
        positionTextView.setText(team.getPosition());

        TextView teamNameTextView = (TextView) layout.findViewById(R.id.standings_table_team);
        teamNameTextView.setText(team.getName());

        TextView winsTextView = (TextView) layout.findViewById(R.id.standings_table_wins);
        String wlt = team.getWins() + "-" + team.getLoses() + "-" + team.getTies();
        winsTextView.setText(wlt);

        TextView percentageTextView = (TextView) layout.findViewById(R.id.standings_table_percentage);
        percentageTextView.setText(team.getWinPercentage());

        TextView pointsTextView = (TextView) layout.findViewById(R.id.standings_table_points);
        String points = team.getGoalsFor() + " / " + team.getGoalsAgainst();
        pointsTextView.setText(points);
    }
}
