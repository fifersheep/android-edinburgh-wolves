package uk.lobsterdoodle.edinburghwolves.app.di;

import javax.inject.Singleton;

import dagger.Component;
import uk.lobsterdoodle.edinburghwolves.core.presenter.RosterListFragmentPresenter;

@Singleton
@Component(modules = { WolvesModule.class })
public interface WolvesComponent {
    RosterListFragmentPresenter provideRosterListFragmentPresenter();
}
