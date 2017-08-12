package uk.lobsterdoodle.edinburghwolves.di

import javax.inject.Singleton

import dagger.Component
import retrofit2.Retrofit
import uk.lobsterdoodle.edinburghwolves.OverviewFragment
import uk.lobsterdoodle.edinburghwolves.roster.RosterListFragment
import uk.lobsterdoodle.edinburghwolves.app.base.App
import uk.lobsterdoodle.edinburghwolves.app.di.ApplicationModule
import uk.lobsterdoodle.edinburghwolves.core.presenter.RosterListFragmentPresenter
import uk.lobsterdoodle.edinburghwolves.home.FixturesFragment

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(app: App)

    fun inject(rosterListFragment: RosterListFragment)

    fun inject(overviewFragment: OverviewFragment)

    fun inject(fixturesFragment: FixturesFragment)

    fun provideRosterListFragmentPresenter(): RosterListFragmentPresenter

    fun providesRetrofit(): Retrofit
}
