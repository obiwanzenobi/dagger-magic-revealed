package lightmobile.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import javax.inject.Inject;

import lightmobile.dagger.injection.SessionActivityModule;
import lightmobile.dagger.injection.SessionComponent;

public class SessionEndActivity extends BaseSessionActivity {

    @Inject
    SessionActivityPresenter presenter;

    @Override
    protected void onSessionComponentReady(SessionComponent sessionComponent) {
        sessionComponent.plus(new SessionActivityModule()).inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        TextView label = findViewById(R.id.label);
        label.setText("Ending " + presenter.getSession().getSessionName());
    }
}
