package uk.lobsterdoodle.edinburghwolves.di

import dagger.Component
import uk.lobsterdoodle.edinburghwolves.OverviewFragment
import uk.lobsterdoodle.edinburghwolves.app.base.App
import uk.lobsterdoodle.edinburghwolves.app.di.ApplicationModule
import uk.lobsterdoodle.edinburghwolves.home.FixturesFragment
import uk.lobsterdoodle.edinburghwolves.network.fixture.FixturesNetworkHandler
import uk.lobsterdoodle.edinburghwolves.network.player.PlayersNetworkHandler
import uk.lobsterdoodle.edinburghwolves.roster.RosterListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(app: App)

    fun inject(rosterListFragment: RosterListFragment)

    fun inject(overviewFragment: OverviewFragment)

    fun inject(fixturesFragment: FixturesFragment)

    fun providesNetworkHandler(): FixturesNetworkHandler

    fun providesPlayersNetworkHandler(): PlayersNetworkHandler
}
