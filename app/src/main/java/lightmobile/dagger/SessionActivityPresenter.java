package lightmobile.dagger;

import javax.inject.Inject;

import lightmobile.dagger.injection.scopes.PerActivity;

@PerActivity
public class SessionActivityPresenter {

    private Session session;

    @Inject
    public SessionActivityPresenter(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
