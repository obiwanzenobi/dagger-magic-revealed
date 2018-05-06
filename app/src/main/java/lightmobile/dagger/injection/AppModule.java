package lightmobile.dagger.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import lightmobile.dagger.R;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerApp
    public String provideHello() {
        return context.getString(R.string.hello);
    }
}
