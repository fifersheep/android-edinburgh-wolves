package uk.lobsterdoodle.edinburghwolves.home

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import uk.lobsterdoodle.edinburghwolves.OverviewFragment
import uk.lobsterdoodle.edinburghwolves.app.R
import uk.lobsterdoodle.edinburghwolves.app.overview.DummyFragment
import uk.lobsterdoodle.edinburghwolves.app.overview.DummyFragment.OnFragmentInteractionListener
import uk.lobsterdoodle.edinburghwolves.model.Fixture
import uk.lobsterdoodle.edinburghwolves.roster.RosterListFragment
import uk.lobsterdoodle.edinburghwolves.roster.RosterListFragment.OnListFragmentInteractionListener
import uk.lobsterdoodle.edinburghwolves.model.Player


class HomeActivity : AppCompatActivity(), OnFragmentInteractionListener, OnListFragmentInteractionListener, FixturesFragment.OnListFragmentInteractionListener {

    @BindView(R.id.home_fragment_view_pager) lateinit var viewPager: ViewPager
    @BindView(R.id.navigation) lateinit var navigation: BottomNavigationView

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_tab_item_home -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tab_item_fixtures -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tab_item_roster -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wolves_home)
        ButterKnife.bind(this)

        viewPager.adapter = HomeFragmentPagerAdapter(supportFragmentManager)
        navigation.setOnNavigationItemSelectedListener(listener)
    }

    private inner class HomeFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return OverviewFragment()
                1 -> return FixturesFragment()
                2 -> return RosterListFragment.newInstance()
                else -> { return DummyFragment.newInstance() }
            }
        }

        override fun getCount(): Int = 3
    }

    override fun onListFragmentInteraction(player: Player) {}

    override fun onListFragmentInteraction(fixture: Fixture) {}

    override fun onFragmentInteraction(uri: Uri?) {}
}
