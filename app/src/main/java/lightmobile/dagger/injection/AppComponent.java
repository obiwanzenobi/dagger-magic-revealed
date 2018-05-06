package lightmobile.dagger.injection;

import javax.inject.Singleton;

import dagger.Component;
import dagger.MembersInjector;
import lightmobile.dagger.MainActivity;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
    MembersInjector<MainActivity> activityInjector();
}
