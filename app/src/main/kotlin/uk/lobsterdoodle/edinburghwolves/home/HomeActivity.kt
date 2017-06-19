package uk.lobsterdoodle.edinburghwolves.home

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import uk.lobsterdoodle.edinburghwolves.app.R
import uk.lobsterdoodle.edinburghwolves.app.dummy.DummyContent
import uk.lobsterdoodle.edinburghwolves.app.overview.OverviewFragment
import uk.lobsterdoodle.edinburghwolves.app.overview.OverviewFragment.OnFragmentInteractionListener
import uk.lobsterdoodle.edinburghwolves.app.roster.RosterListFragment
import uk.lobsterdoodle.edinburghwolves.app.roster.RosterListFragment.OnListFragmentInteractionListener

class HomeActivity : AppCompatActivity(), OnFragmentInteractionListener, OnListFragmentInteractionListener {

    lateinit var viewPager: ViewPager

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
            R.id.nav_tab_item_news -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tab_item_roster -> {
                viewPager.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tab_item_how_to -> {
                viewPager.currentItem = 4
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wolves_home)

        viewPager = findViewById(R.id.home_fragment_view_pager) as ViewPager
        viewPager.adapter = HomeFragmentPagerAdapter(supportFragmentManager)

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(listener)
    }

    private inner class HomeFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            when (position) {
                3 -> return RosterListFragment.newInstance()
                else -> { return OverviewFragment.newInstance() }
            }
        }

        override fun getCount(): Int = 5
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {}

    override fun onFragmentInteraction(uri: Uri?) {}
}
