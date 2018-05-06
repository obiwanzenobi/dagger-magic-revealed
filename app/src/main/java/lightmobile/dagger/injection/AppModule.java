package lightmobile.dagger.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import lightmobile.dagger.SessionManager;
import lightmobile.dagger.injection.scopes.PerApp;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerApp
    public SessionManager provideSessionManager(SessionComponent.Builder sessionComponentBuilder) {
        return new SessionManager(sessionComponentBuilder);
    }
}
