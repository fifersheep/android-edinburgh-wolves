package uk.lobsterdoodle.edinburghwolves.core.api.presenter;

import org.jsoup.nodes.Document;

/**
 * Created by Scott Laing on 05-Jan-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public interface StandingsPresenter {
    public void displayStandings(Document document);
    public void displayMostRecentGame(Document document);
}
