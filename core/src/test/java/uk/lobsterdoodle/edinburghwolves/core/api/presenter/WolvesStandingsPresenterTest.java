package uk.lobsterdoodle.edinburghwolves.core.api.presenter;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import uk.lobsterdoodle.edinburghwolves.core.api.data.HtmlDocumentDataExtractor;
import uk.lobsterdoodle.edinburghwolves.core.api.data.SharedPrefsDataRetriever;
import uk.lobsterdoodle.edinburghwolves.core.api.listener.StandingsListener;
import uk.lobsterdoodle.edinburghwolves.core.api.model.CompletedFixture;
import uk.lobsterdoodle.edinburghwolves.core.api.model.Team;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Scott Laing on 14-Mar-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public class WolvesStandingsPresenterTest {

    @Test
    public void the_add_team_method_is_called_the_correct_number_of_times() {
        StandingsListener listener = mock(StandingsListener.class);
        HtmlDocumentDataExtractor extractor = mock(HtmlDocumentDataExtractor.class);
        when(extractor.teams()).thenReturn(anyFourTeams());

        WolvesStandingsPresenter presenter = new WolvesStandingsPresenter(listener, anyRetriever());
        presenter.displayStandings(extractor);

        verify(listener, times(4)).addTeam(anyTeam());
    }

    @Test
    public void the_add_team_method_clears_existing_teams_first() {
        StandingsListener listener = mock(StandingsListener.class);
        Mockito.doNothing().when(listener).clearStandingsTable();
        HtmlDocumentDataExtractor extractor = mock(HtmlDocumentDataExtractor.class);
        when(extractor.teams()).thenReturn(anyFourTeams());

        WolvesStandingsPresenter presenter = new WolvesStandingsPresenter(listener, anyRetriever());
        presenter.displayStandings(extractor);

        InOrder inOrder = inOrder(listener);
        inOrder.verify(listener).clearStandingsTable();
        inOrder.verify(listener, atLeastOnce()).addTeam(anyTeam());
    }

    @Test
    public void the_most_recent_game_is_displayed_correctly_when_one_exists() {
        StandingsListener listener = mock(StandingsListener.class);
        HtmlDocumentDataExtractor extractor = mock(HtmlDocumentDataExtractor.class);
        CompletedFixture game = anyCompletedFixture();
        when(extractor.mostRecentGame()).thenReturn(game);

        SharedPrefsDataRetriever dataRetriever = mock(SharedPrefsDataRetriever.class);
        WolvesStandingsPresenter presenter = new WolvesStandingsPresenter(listener, dataRetriever);
        presenter.displayMostRecentGame(extractor);

        verify(listener).setRecentGameHomeTeam("Team");
        verify(listener).setRecentGameHomeScore("14");
        verify(listener).setRecentGameAwayTeam("Team");
        verify(listener).setRecentGameAwayScore("7");
    }

    @Test
    public void the_most_recent_saved_game_is_not_displayed_when_one_does_not_exists() {
        StandingsListener listener = mock(StandingsListener.class);
        HtmlDocumentDataExtractor extractor = mock(HtmlDocumentDataExtractor.class);
        when(extractor.mostRecentGame()).thenReturn(null);
        SharedPrefsDataRetriever dataRetriever = mock(SharedPrefsDataRetriever.class);
        CompletedFixture game = anyCompletedFixture();
        when(dataRetriever.mostRecentGame()).thenReturn(game);

        WolvesStandingsPresenter presenter = new WolvesStandingsPresenter(listener, dataRetriever);
        presenter.displayMostRecentGame(extractor);

        verify(dataRetriever).mostRecentGame();
        verify(listener).setRecentGameHomeTeam("Team");
        verify(listener).setRecentGameHomeScore("14");
        verify(listener).setRecentGameAwayTeam("Team");
        verify(listener).setRecentGameAwayScore("7");
    }

    private Team[] anyFourTeams() {
        return new Team[]{
                mock(Team.class),
                mock(Team.class),
                mock(Team.class),
                mock(Team.class)
        };
    }

    private Team anyTeam() {
        return any(Team.class);
    }

    private SharedPrefsDataRetriever anyRetriever() {
        return mock(SharedPrefsDataRetriever.class);
    }

    private CompletedFixture anyCompletedFixture() {
        CompletedFixture completedFixture = mock(CompletedFixture.class);

        Team homeTeam = mock(Team.class);
        when(homeTeam.currentName()).thenReturn("Home Team");
        when(completedFixture.getHomeTeam()).thenReturn(homeTeam);
        when(completedFixture.getHomeTeamScore()).thenReturn(14);

        Team awayTeam = mock(Team.class);
        when(awayTeam.currentName()).thenReturn("Away Team");
        when(completedFixture.getAwayTeam()).thenReturn(awayTeam);
        when(completedFixture.getAwayTeamScore()).thenReturn(7);

        return completedFixture;
    }
}