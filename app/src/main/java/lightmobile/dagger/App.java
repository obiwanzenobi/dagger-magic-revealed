package lightmobile.dagger;

import android.app.Application;
import android.content.Context;

import lightmobile.dagger.injection.AppComponent;
import lightmobile.dagger.injection.AppModule;
import lightmobile.dagger.injection.DaggerAppComponent;
import lightmobile.dagger.injection.DaggerSessionComponent;
import lightmobile.dagger.injection.SessionComponent;

public class App extends Application {

    private AppComponent appComponent;
    private SessionComponent sessionComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
            .builder()
            .appModule(new AppModule(this))
            .build();
    }

    public static AppComponent appComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent();
    }

    public static SessionComponent sessionComponent(Context context) {
        return ((App) context.getApplicationContext()).sessionComponent();
    }

    public static SessionComponent startNewSession(Context context) {
        ((App) context.getApplicationContext()).sessionComponent = DaggerSessionComponent.builder()
            .appComponent(appComponent(context))
            .build();
        return sessionComponent(context);
    }

    private AppComponent appComponent() {
        return appComponent;
    }

    private SessionComponent sessionComponent() {
        return sessionComponent;
    }
}
