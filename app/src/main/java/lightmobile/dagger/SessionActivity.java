package lightmobile.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import lightmobile.dagger.injection.SessionActivityModule;
import lightmobile.dagger.injection.SessionComponent;

public class SessionActivity extends BaseSessionActivity {

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
        label.setText(presenter.getSession().getSessionName());
        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SessionActivity.this, SessionEndActivity.class));
                finish();
            }
        });
    }
}
