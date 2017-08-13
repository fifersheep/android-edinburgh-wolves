package uk.lobsterdoodle.edinburghwolves.app.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import uk.lobsterdoodle.edinburghwolves.core.presenter.RosterListFragmentPresenter
import uk.lobsterdoodle.edinburghwolves.network.fixture.FixturesNetworkHandler
import uk.lobsterdoodle.edinburghwolves.network.player.PlayersNetworkHandler

@Module
class ApplicationModule {

    @Provides
    internal fun providesRosterListFragmentPresenter(): RosterListFragmentPresenter {
        return RosterListFragmentPresenter()
    }

    @Provides
    internal fun providesRemoteDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    internal fun providesNetworkHandler(): FixturesNetworkHandler {
        return FixturesNetworkHandler()
    }

    @Provides
    internal fun providesPlayersNetworkHandler(): PlayersNetworkHandler {
        return PlayersNetworkHandler()
    }
}
