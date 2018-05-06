package lightmobile.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import lightmobile.dagger.injection.ActivityModule;
import lightmobile.dagger.injection.DaggerActivityComponent;

public class SessionActivity extends AppCompatActivity {

    @Inject
    SessionDisplayModel sessionDisplayModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        DaggerActivityComponent.builder()
            .sessionComponent(App.startNewSession(this))
            .activityModule(new ActivityModule(this.getClass().getName()))
            .build()
            .inject(this);

        TextView label = findViewById(R.id.label);
        label.setText(sessionDisplayModel.getSessionName());
        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SessionActivity.this, SessionEndActivity.class));
                finish();
            }
        });
    }
}
