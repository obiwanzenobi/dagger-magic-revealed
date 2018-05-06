package lightmobile.dagger.injection;

import dagger.Module;
import dagger.Provides;

@Module
public class StartSessionActivityModule {
    //empty module

    @Provides
    StringBuilder provideStringBuilder(String baseString) {
        return new StringBuilder(baseString).append("second");
    }
}
