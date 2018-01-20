package uk.lobsterdoodle.edinburghwolves.fixture

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import uk.lobsterdoodle.edinburghwolves.app.R
import uk.lobsterdoodle.edinburghwolves.home.FixturesFragment
import uk.lobsterdoodle.edinburghwolves.model.Fixture
import uk.lobsterdoodle.edinburghwolves.model.FixtureStatus

class FixtureListItemRecyclerViewAdapter (private val mListener: FixturesFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<FixtureListItemRecyclerViewAdapter.ViewHolder>() {

    val fixtures = mutableListOf<Fixture>()

    fun newData(fixtures: List<Fixture>) {
        this.fixtures.clear()
        this.fixtures.addAll(fixtures)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_fixture, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fixture = fixtures[position]
        holder.fixture = fixture

        holder.homeName.text = "${fixture.home.team.location} ${fixture.home.team.name}"
        holder.homeScore.text = fixture.home.score
        holder.homeColor.setBackgroundColor(Color.parseColor(fixture.home.team.primaryColor))

        holder.awayName.text = "${fixture.away.team.location} ${fixture.away.team.name}"
        holder.awayScore.text = fixture.away.score
        holder.awayColor.setBackgroundColor(Color.parseColor(fixture.away.team.primaryColor))

        holder.state.text = when(fixture.status) {
            FixtureStatus.scheduled -> fixture.date
            FixtureStatus.started -> "In Progress"
            FixtureStatus.finished -> "Final"
            else -> ""
        }

        holder.view.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.fixture!!)
        }
    }

    override fun getItemCount(): Int = fixtures.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        init { ButterKnife.bind(this, view) }

        @BindView(R.id.fixture_home_team_name) lateinit var homeName: TextView
        @BindView(R.id.fixture_home_team_score) lateinit var homeScore: TextView
        @BindView(R.id.fixture_home_color) lateinit var homeColor: View
        @BindView(R.id.fixture_away_team_name) lateinit var awayName: TextView
        @BindView(R.id.fixture_away_team_score) lateinit var awayScore: TextView
        @BindView(R.id.fixture_away_color) lateinit var awayColor: View
        @BindView(R.id.fixture_state) lateinit var state: TextView

        var fixture: Fixture? = null
    }
}