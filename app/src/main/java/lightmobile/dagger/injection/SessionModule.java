package lightmobile.dagger.injection;

import dagger.Module;
import dagger.Provides;
import lightmobile.dagger.Session;
import lightmobile.dagger.injection.scopes.PerSession;

@Module
public class SessionModule {

    private Session session;

    public SessionModule(Session session) {
        this.session = session;
    }

    @PerSession
    @Provides
    Session provideSessionNumber() {
        return session;
    }
}
