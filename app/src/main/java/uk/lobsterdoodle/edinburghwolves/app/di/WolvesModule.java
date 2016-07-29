package uk.lobsterdoodle.edinburghwolves.app.di;

import dagger.Module;
import dagger.Provides;
import uk.lobsterdoodle.edinburghwolves.core.presenter.RosterListFragmentPresenter;

@Module
public class WolvesModule {

    @Provides
    RosterListFragmentPresenter providesRosterListFragmentPresenter() {
        return new RosterListFragmentPresenter();
    }
}
