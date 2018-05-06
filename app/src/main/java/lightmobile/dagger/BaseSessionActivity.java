package lightmobile.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import lightmobile.dagger.injection.SessionComponent;

public abstract class BaseSessionActivity extends AppCompatActivity {

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.appComponent(this).inject(this);
        onSessionComponentReady(sessionManager.sessionComponent());
    }

    protected abstract void onSessionComponentReady(SessionComponent sessionComponent);
}
