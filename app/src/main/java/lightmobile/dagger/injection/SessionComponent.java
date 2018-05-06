package lightmobile.dagger.injection;

import dagger.Component;
import lightmobile.dagger.injection.scopes.PerSession;

@PerSession
@Component(dependencies = AppComponent.class, modules = SessionModule.class)
public interface SessionComponent {

    Integer sessionNumber();

    String originalString();
}
