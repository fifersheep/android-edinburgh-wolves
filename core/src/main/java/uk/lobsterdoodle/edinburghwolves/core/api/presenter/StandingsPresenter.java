package uk.lobsterdoodle.edinburghwolves.core.api.presenter;

import uk.lobsterdoodle.edinburghwolves.core.api.data.HtmlDocumentDataExtractor;

/**
 * Created by Scott Laing on 05-Jan-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public interface StandingsPresenter {
    public void displayStandings(HtmlDocumentDataExtractor extractor);
    public void displayMostRecentGame(HtmlDocumentDataExtractor extractor);
}
