package uk.lobsterdoodle.edinburghwolves.app.base;

import android.app.Application;
import android.content.Context;

import uk.lobsterdoodle.edinburghwolves.app.di.DaggerWolvesComponent;
import uk.lobsterdoodle.edinburghwolves.app.di.WolvesComponent;
import uk.lobsterdoodle.edinburghwolves.app.di.WolvesModule;

public class App extends Application {
    private WolvesComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerWolvesComponent
                .builder()
                .wolvesModule(new WolvesModule())
                .build();

        appComponent.inject(this);
    }

    public WolvesComponent component() {
        return appComponent;
    }
}
