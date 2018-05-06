package lightmobile.dagger.injection;

import dagger.Component;
import lightmobile.dagger.BaseSessionActivity;
import lightmobile.dagger.injection.scopes.PerApp;

@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {

    StartSessionActivityComponent plus(StartSessionActivityModule module);

    SessionComponent.Builder sessionComponentBuilder();

    void inject(BaseSessionActivity sessionActivity);
}
