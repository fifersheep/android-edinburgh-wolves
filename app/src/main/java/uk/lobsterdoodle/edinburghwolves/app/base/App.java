package uk.lobsterdoodle.edinburghwolves.app.base;

import android.app.Application;
import android.content.Context;

import uk.lobsterdoodle.edinburghwolves.di.DaggerApplicationComponent;
import uk.lobsterdoodle.edinburghwolves.di.ApplicationComponent;
import uk.lobsterdoodle.edinburghwolves.app.di.ApplicationModule;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build();

        applicationComponent.inject(this);
    }

    public ApplicationComponent component() {
        return applicationComponent;
    }
}
