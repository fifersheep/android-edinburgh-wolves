package uk.lobsterdoodle.edinburghwolves.roster

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

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
        val fullname: TextView = view.findViewById(R.id.fullname) as TextView
        val position: TextView = view.findViewById(R.id.position) as TextView
        val number: TextView = view.findViewById(R.id.number) as TextView
        val location: TextView = view.findViewById(R.id.location) as TextView
        var player: Player? = null
    }
}
