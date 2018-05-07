package lightmobile.dagger;

import android.app.Application;
import android.content.Context;

import lightmobile.dagger.injection.AppComponent;
import lightmobile.dagger.injection.DaggerAppComponent;

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
            .builder()
            .build();
    }

    public static AppComponent appComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent();
    }

    private AppComponent appComponent() {
        return appComponent;
    }
}
