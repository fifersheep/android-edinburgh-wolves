package uk.lobsterdoodle.edinburghwolves.app.network;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import uk.lobsterdoodle.edinburghwolves.core.api.data.StandingsDataExtractor;
import uk.lobsterdoodle.edinburghwolves.core.api.presenter.StandingsPresenter;

/**
 * Created by Scott Laing on 09-Jan-2015
 * Copyright (c) Scott Laing 2015. All rights reserved.
 */
public class DivisionDataDownloadTask extends AsyncTask<Void, Void, Document> {
    final private StandingsPresenter standingsPresenter;
    private static String TAG = DivisionDataDownloadTask.class.getSimpleName();

    public DivisionDataDownloadTask(StandingsPresenter standingsPresenter) {
        this.standingsPresenter = standingsPresenter;
    }

    @Override
    protected Document doInBackground(Void... params) {
        Document doc = null;

        try {
            String downloadUrl = "http://web.archive.org/web/20131031114528/http://www.bafanl.co.uk/team/43";
            doc = Jsoup.connect(downloadUrl).timeout(30 * 1000).get();
        } catch (IOException e) {
            Log.e(TAG, "Failed to download HTML document", e);
        }

        return doc;
    }

    @Override
    protected void onPostExecute(Document document) {
        StandingsDataExtractor extractor = new StandingsDataExtractor(document);
        standingsPresenter.displayStandings(extractor.getTeams());
        standingsPresenter.displayMostRecentGame(extractor.mostRecentGame());
    }
}
