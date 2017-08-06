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
import com.eightbitlab.rxbus.registerInBus
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.schedulers.Schedulers
import uk.lobsterdoodle.edinburghwolves.app.R
import uk.lobsterdoodle.edinburghwolves.app.base.App
import uk.lobsterdoodle.edinburghwolves.fixture.FixtureListItemRecyclerViewAdapter
import uk.lobsterdoodle.edinburghwolves.fixtures.RetrieveFixturesEvent
import uk.lobsterdoodle.edinburghwolves.model.Fixture

object FixturesFragment : Fragment() {
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

        Bus.observe<RetrieveFixturesEvent>().observeOn(Schedulers.newThread()).subscribe { onRetrievedFixtures() }.registerInBus(this)
        Bus.send(RetrieveFixturesEvent())

        return view
    }

    fun onRetrievedFixtures() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://bafanl-d5f55.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(FixturesService::class.java)
        val call = service.getFixtures()
        val fixtures = call.execute().body().orEmpty().values

        activity.runOnUiThread {
            adapter.newData(fixtures.sortedWith(compareBy { it.date }).toMutableList())
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

    interface FixturesService {
        @GET("fixtures.json")
        fun getFixtures(): Call<HashMap<String, Fixture>>
    }
}