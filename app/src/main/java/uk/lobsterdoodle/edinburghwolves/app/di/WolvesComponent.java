package uk.lobsterdoodle.edinburghwolves.app.di;

import javax.inject.Singleton;

import dagger.Component;
import uk.lobsterdoodle.edinburghwolves.app.RosterListFragment;
import uk.lobsterdoodle.edinburghwolves.app.base.App;
import uk.lobsterdoodle.edinburghwolves.core.presenter.RosterListFragmentPresenter;

@Singleton
@Component(modules = { WolvesModule.class })
public interface WolvesComponent {

    void inject(App app);

    void inject(RosterListFragment rosterListFragment);

    RosterListFragmentPresenter provideRosterListFragmentPresenter();
}
