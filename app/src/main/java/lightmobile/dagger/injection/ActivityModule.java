package lightmobile.dagger.injection;

import dagger.Module;
import dagger.Provides;
import lightmobile.dagger.SessionDisplayModel;
import lightmobile.dagger.injection.scopes.PerActivity;

@Module
public class ActivityModule {

    private String activityName;

    public ActivityModule(String activityName) {
        this.activityName = activityName;
    }

    @Provides
    @PerActivity
    SessionDisplayModel provideSessionDisplayModel(String appName, Integer sessionNumber) {
        return new SessionDisplayModel(appName + " " + activityName + sessionNumber);
    }
}
