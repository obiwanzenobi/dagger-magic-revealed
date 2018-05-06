package lightmobile.dagger.injection;

import android.content.Context;
import android.net.Uri;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
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

    @Provides
    @Reusable
    String provideRawUrl() {
        return "http://google.com";
    }

    @Provides
    @PerApp
    Uri provideUrl(String rawUrl) {
        return Uri.parse(rawUrl);
    }
}
