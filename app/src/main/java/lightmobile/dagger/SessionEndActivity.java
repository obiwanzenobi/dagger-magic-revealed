package lightmobile.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import lightmobile.dagger.injection.ActivityModule;
import lightmobile.dagger.injection.DaggerActivityComponent;

public class SessionEndActivity extends AppCompatActivity {

    @Inject
    SessionDisplayModel sessionDisplayModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        DaggerActivityComponent.builder()
            .sessionComponent(App.sessionComponent(this))
            .activityModule(new ActivityModule(this.getClass().getName()))
            .build()
            .inject(this);

        TextView label = findViewById(R.id.label);
        label.setText(sessionDisplayModel.getSessionName());
    }
}
