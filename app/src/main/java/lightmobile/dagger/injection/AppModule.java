package lightmobile.dagger.injection;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @PerApp
    public String provideHello() {
        return "Hello world!";
    }
}
