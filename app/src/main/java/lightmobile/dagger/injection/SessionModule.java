package lightmobile.dagger.injection;

import java.util.concurrent.ThreadLocalRandom;

import dagger.Module;
import dagger.Provides;
import lightmobile.dagger.injection.scopes.PerSession;

@Module
public class SessionModule {

    @PerSession
    @Provides
    Integer provideSessionNumber() {
        return ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }
}
