package lightmobile.dagger.injection;

import dagger.Subcomponent;
import lightmobile.dagger.SessionActivity;
import lightmobile.dagger.SessionEndActivity;
import lightmobile.dagger.injection.scopes.PerActivity;

@PerActivity
@Subcomponent(modules = SessionActivityModule.class)
public interface SessionActivityComponent {
    void inject(SessionActivity activity);

    void inject(SessionEndActivity activity);
}
