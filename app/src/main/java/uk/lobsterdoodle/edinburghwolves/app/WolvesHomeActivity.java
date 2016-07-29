package uk.lobsterdoodle.edinburghwolves.app;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import uk.lobsterdoodle.edinburghwolves.app.RosterListFragment.OnListFragmentInteractionListener;
import uk.lobsterdoodle.edinburghwolves.app.WolvesHomeFragment.OnFragmentInteractionListener;
import uk.lobsterdoodle.edinburghwolves.app.dummy.DummyContent;

public class WolvesHomeActivity extends AppCompatActivity implements OnFragmentInteractionListener, OnListFragmentInteractionListener {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wolves_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.home_fragment_view_pager);
        viewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager()));

        BottomNavigation bottomNavigation = (BottomNavigation) findViewById(R.id.nav_tabs);
        bottomNavigation.setOnMenuItemClickListener(new TabSelectedListener());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    private class TabSelectedListener implements BottomNavigation.OnMenuItemSelectionListener {

        @Override
        public void onMenuItemSelect(@IdRes int itemId, int position) {
            viewPager.setCurrentItem(position);
        }

        @Override
        public void onMenuItemReselect(@IdRes int itemId, int position) {
            viewPager.setCurrentItem(position);
        }
    }

    private class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

        public HomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return position == 3
                        ? RosterListFragment.newInstance()
                        : WolvesHomeFragment.newInstance();
        }
        @Override
        public int getCount() {
            return 5;
        }
    }
}
