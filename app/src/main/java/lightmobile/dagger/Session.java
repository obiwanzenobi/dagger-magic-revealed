package lightmobile.dagger;

public class Session {

    private String sessionName;

    public Session(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionName() {
        return sessionName;
    }
}
