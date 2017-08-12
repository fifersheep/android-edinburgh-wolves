package uk.lobsterdoodle.edinburghwolves.roster

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable
import rx.schedulers.Schedulers
import uk.lobsterdoodle.edinburghwolves.app.R
import uk.lobsterdoodle.edinburghwolves.app.base.App
import uk.lobsterdoodle.edinburghwolves.core.presenter.RosterListFragmentPresenter
import uk.lobsterdoodle.edinburghwolves.core.view.RosterListFragmentView
import uk.lobsterdoodle.edinburghwolves.model.Player
import javax.inject.Inject

class RosterListFragment : Fragment(), RosterListFragmentView {
    private var mListener: OnListFragmentInteractionListener? = null

    private val adapter = RosterListItemRecyclerViewAdapter(mListener)

    @Inject lateinit var presenter: RosterListFragmentPresenter

    @Inject lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get(activity).component().inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_roster_list, container, false)

        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(activity, R.drawable.vertical_list_divider))
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }

        presenter.onCreateView(this)
        Bus.observe<RetrievePlayersEvent>().observeOn(Schedulers.newThread()).subscribe { onRetrievedPlayers() }.registerInBus(this)
        Bus.send(RetrievePlayersEvent())
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    fun onRetrievedPlayers() {
        retrofit.create(PlayersService::class.java).getPlayers().subscribe { players ->
            activity.runOnUiThread {
                adapter.newData(players.values.sortedWith(compareBy { it.number }).toMutableList())
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context as OnListFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(player: Player)
    }

    interface PlayersService {
        @GET("players.json")
        fun getPlayers(): Observable<HashMap<String, Player>>
    }

    companion object {
        fun newInstance(): RosterListFragment {
            return RosterListFragment()
        }
    }
}
