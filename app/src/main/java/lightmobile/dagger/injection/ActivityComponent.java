package lightmobile.dagger.injection;

import dagger.Component;
import lightmobile.dagger.SessionActivity;
import lightmobile.dagger.SessionEndActivity;
import lightmobile.dagger.injection.scopes.PerActivity;

@PerActivity
@Component(dependencies = SessionComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SessionActivity activity);

    void inject(SessionEndActivity activity);
}
