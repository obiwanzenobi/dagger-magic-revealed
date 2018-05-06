package lightmobile.dagger.injection;

import dagger.Component;
import lightmobile.dagger.MainActivity;

@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
}
