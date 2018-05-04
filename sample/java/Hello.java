@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public String provideHello() {
        return context.getString(R.string.hello);
    }
}

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
}

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
            .builder()
            .appModule(new AppModule(this))
            .build();
    }

    public static AppComponent appComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent();
    }

    private AppComponent appComponent() {
        return appComponent;
    }
}

public class MainActivity extends AppCompatActivity {

    @Inject String initialText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.appComponent(this).inject(this);
        TextView label = findViewById(R.id.label);
        label.setText(initialText);
    }
}
