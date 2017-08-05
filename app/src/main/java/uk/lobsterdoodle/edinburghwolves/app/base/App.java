package uk.lobsterdoodle.edinburghwolves.app.base;

import android.app.Application;
import android.content.Context;

import uk.lobsterdoodle.edinburghwolves.di.DaggerUiComponent;
import uk.lobsterdoodle.edinburghwolves.di.UiComponent;
import uk.lobsterdoodle.edinburghwolves.app.di.UiModule;

public class App extends Application {
    private UiComponent uiComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        uiComponent = DaggerUiComponent
                .builder()
                .uiModule(new UiModule())
                .build();

        uiComponent.inject(this);
    }

    public UiComponent component() {
        return uiComponent;
    }
}
