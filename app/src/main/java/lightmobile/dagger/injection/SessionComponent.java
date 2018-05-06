package lightmobile.dagger.injection;

import dagger.Subcomponent;
import lightmobile.dagger.injection.scopes.PerSession;

@PerSession
@Subcomponent(modules = SessionModule.class)
public interface SessionComponent {

    @Subcomponent.Builder
    interface Builder {
        SessionComponent.Builder sessionModule(SessionModule sessionModule);
        SessionComponent build();
    }

    SessionActivityComponent plus(SessionActivityModule activityModule);
}
