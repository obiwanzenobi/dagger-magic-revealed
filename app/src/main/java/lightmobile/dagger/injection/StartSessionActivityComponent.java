package lightmobile.dagger.injection;

import dagger.Subcomponent;
import lightmobile.dagger.MainActivity;
import lightmobile.dagger.injection.scopes.PerActivity;

@PerActivity
@Subcomponent(modules = StartSessionActivityModule.class)
public interface StartSessionActivityComponent {
    void inject(MainActivity activity);
}
