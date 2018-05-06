package lightmobile.dagger.injection;

import dagger.Component;
import lightmobile.dagger.injection.scopes.PerApp;

@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {
    String provideHelloString();
}
