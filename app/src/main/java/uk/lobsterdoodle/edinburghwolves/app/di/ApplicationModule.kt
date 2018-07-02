package uk.lobsterdoodle.edinburghwolves.app.di

import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import uk.lobsterdoodle.edinburghwolves.network.fixture.FixturesCollection
import uk.lobsterdoodle.edinburghwolves.network.fixture.FixturesNetworkHandler
import uk.lobsterdoodle.edinburghwolves.network.player.PlayersCollection
import uk.lobsterdoodle.edinburghwolves.network.player.PlayersNetworkHandler

@Module
class ApplicationModule {

    @Provides
    internal fun providesNetworkHandler(): FixturesNetworkHandler {
        return FixturesNetworkHandler()
    }

    @Provides
    internal fun providesPlayersNetworkHandler(): PlayersNetworkHandler {
        return PlayersNetworkHandler()
    }

    @Provides
    internal fun providesFixtures(fixturesNetworkHandler: FixturesNetworkHandler): Observable<FixturesCollection> {
        return fixturesNetworkHandler.getFixtures()
    }

    @Provides
    internal fun providesPlayers(playersNetworkHandler: PlayersNetworkHandler): Observable<PlayersCollection> {
        return playersNetworkHandler.getPlayers()
    }
}
