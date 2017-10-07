package uk.lobsterdoodle.edinburghwolves.roster

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

import uk.lobsterdoodle.edinburghwolves.app.R
import uk.lobsterdoodle.edinburghwolves.model.Player

class RosterListItemRecyclerViewAdapter(private val mListener: RosterListFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<RosterListItemRecyclerViewAdapter.ViewHolder>() {

    val players = mutableListOf<Player>()

    fun newData(players: List<Player>) {
        this.players.clear()
        this.players.addAll(players)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_roster_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]
        holder.player = player
        holder.fullname.text = "${player.firstname} ${player.lastname}"
        holder.position.text = player.position
        holder.number.text = "#${player.number}"
        holder.location.text = player.birthplace

        holder.view.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.player!!)
        }
    }

    override fun getItemCount(): Int = players.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        init { ButterKnife.bind(this, view) }

        @BindView(R.id.fullname) lateinit var fullname: TextView
        @BindView(R.id.position) lateinit var position: TextView
        @BindView(R.id.number) lateinit var number: TextView
        @BindView(R.id.location) lateinit var location: TextView

        var player: Player? = null
    }
}
