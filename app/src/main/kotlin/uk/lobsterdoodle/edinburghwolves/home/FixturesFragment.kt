package uk.lobsterdoodle.edinburghwolves.home

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
import rx.schedulers.Schedulers
import uk.lobsterdoodle.edinburghwolves.app.R
import uk.lobsterdoodle.edinburghwolves.app.base.App
import uk.lobsterdoodle.edinburghwolves.fixture.FixtureListItemRecyclerViewAdapter
import uk.lobsterdoodle.edinburghwolves.model.Fixture
import uk.lobsterdoodle.edinburghwolves.network.fixture.FetchFixturesDocument
import uk.lobsterdoodle.edinburghwolves.network.fixture.FixturesDocument


class FixturesFragment : Fragment() {
    private var mListener: FixturesFragment.OnListFragmentInteractionListener? = null

    private val adapter = FixtureListItemRecyclerViewAdapter(mListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get(activity).component().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_fixtures, container, false)

        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(activity, R.drawable.vertical_list_divider_8dp_transparent))
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }

        Bus.observe<FixturesDocument>().observeOn(Schedulers.newThread()).subscribe { fixturesDoc(it) }
        Bus.send(FetchFixturesDocument("fixtures"))

        return view
    }

    private fun fixturesDoc(doc: FixturesDocument) {
        activity.runOnUiThread {
            adapter.newData(doc.payload.values.sortedWith(compareBy { it.date }).toMutableList())
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
        fun onListFragmentInteraction(fixture: Fixture)
    }
}