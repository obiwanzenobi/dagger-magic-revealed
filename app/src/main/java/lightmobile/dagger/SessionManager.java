package lightmobile.dagger;

import java.util.concurrent.ThreadLocalRandom;

import lightmobile.dagger.injection.SessionComponent;
import lightmobile.dagger.injection.SessionModule;

public class SessionManager {

    private SessionComponent.Builder sessionComponentBuilder;
    private SessionComponent sessionComponent;

    public SessionManager(SessionComponent.Builder sessionComponentBuilder) {
        this.sessionComponentBuilder = sessionComponentBuilder;
    }

    public void startSession() {
        sessionComponent = sessionComponentBuilder
            .sessionModule(new SessionModule(new Session("Session: " + generateSessionNumber())))
            .build();
    }

    public void stopSession() {
        sessionComponent = null;
    }

    public boolean isSessionStared() {
        return sessionComponent == null;
    }

    public SessionComponent sessionComponent() {
        return sessionComponent;
    }

    private int generateSessionNumber() {
        return ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }
}
