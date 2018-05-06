package lightmobile.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import lightmobile.dagger.injection.StartSessionActivityModule;

public class MainActivity extends AppCompatActivity {

    @Inject
    SessionManager sessionManager;

    @Inject
    StringBuilder stringBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        App.appComponent(this)
            .plus(new StartSessionActivityModule())
            .inject(this);

        TextView label = findViewById(R.id.label);
        label.setText("Start session");
        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.startSession();
                startActivity(new Intent(MainActivity.this, SessionActivity.class));
            }
        });
    }
}
