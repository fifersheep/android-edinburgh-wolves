package uk.lobsterdoodle.edinburghwolves.fixture

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fixture = fixtures[position]
        holder.fixture = fixture
        holder.homeName.text = fixture.home.team
        holder.homeScore.text = fixture.home.score
//        holder.homeColor.setBackgroundColor(Color.parseColor("#222222")) // Retrieve this async
        holder.awayName.text = fixture.away.team
        holder.awayScore.text = fixture.away.score
//        holder.awayColor.setBackgroundColor(Color.parseColor("#222222")) // Retrieve this async
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
        val homeName = view.findViewById(R.id.fixture_home_team_name) as TextView
        val homeScore = view.findViewById(R.id.fixture_home_team_score) as TextView
        val homeColor = view.findViewById(R.id.fixture_home_color)
        val awayName = view.findViewById(R.id.fixture_away_team_name) as TextView
        val awayScore = view.findViewById(R.id.fixture_away_team_score) as TextView
        val awayColor = view.findViewById(R.id.fixture_away_color)
        val state = view.findViewById(R.id.fixture_state) as TextView
        var fixture: Fixture? = null
    }
}