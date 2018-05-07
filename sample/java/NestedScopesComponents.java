@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {
    String provideHelloString();
}

@PerSession
@Component(dependencies = AppComponent.class, modules = SessionModule.class)
public interface SessionComponent {

    Integer sessionNumber();

    String originalString();
}

public final class DaggerSessionComponent implements SessionComponent {
  private AppComponent appComponent;

  private Provider<Integer> provideSessionNumberProvider;

  private DaggerSessionComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideSessionNumberProvider =
        DoubleCheck.provider(
            SessionModule_ProvideSessionNumberFactory.create(builder.sessionModule));
    this.appComponent = builder.appComponent;
  }

  @Override
  public Integer sessionNumber() {
    return provideSessionNumberProvider.get();
  }

  @Override
  public String originalString() {
    return Preconditions.checkNotNull(
        appComponent.provideHelloString(),
        "Cannot return null from a non-@Nullable component method");
  }

  public static final class Builder {
    private SessionModule sessionModule;

    private AppComponent appComponent;

    private Builder() {}

    public SessionComponent build() {
      if (sessionModule == null) {
        this.sessionModule = new SessionModule();
      }
      if (appComponent == null) {
        throw new IllegalStateException(AppComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerSessionComponent(this);
    }

    public Builder sessionModule(SessionModule sessionModule) {
      this.sessionModule = Preconditions.checkNotNull(sessionModule);
      return this;
    }

    public Builder appComponent(AppComponent appComponent) {
      this.appComponent = Preconditions.checkNotNull(appComponent);
      return this;
    }
  }
}

@PerActivity
@Component(dependencies = SessionComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SessionActivity activity);

    void inject(SessionEndActivity activity);
}

@Module
public class ActivityModule {

    private String activityName;

    public ActivityModule(String activityName) {
        this.activityName = activityName;
    }

    @Provides
    @PerActivity
    SessionDisplayModel provideSessionDisplayModel(String appName, Integer sessionNumber) {
        return new SessionDisplayModel(appName + " " + activityName + sessionNumber);
    }
}

public class App extends Application {

    private AppComponent appComponent;
    private SessionComponent sessionComponent;

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

    public static SessionComponent sessionComponent(Context context) {
        return ((App) context.getApplicationContext()).sessionComponent();
    }

    public static SessionComponent startNewSession(Context context) {
        ((App) context.getApplicationContext()).sessionComponent = DaggerSessionComponent.builder()
            .appComponent(appComponent(context))
            .build();
        return sessionComponent(context);
    }

    private AppComponent appComponent() {
        return appComponent;
    }

    private SessionComponent sessionComponent() {
        return sessionComponent;
    }
}


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

       //..
    }
}

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

public final class DaggerActivityComponent implements ActivityComponent {
  private lightmobile_dagger_injection_SessionComponent_originalString originalStringProvider;

  private lightmobile_dagger_injection_SessionComponent_sessionNumber sessionNumberProvider;

  private Provider<SessionDisplayModel> provideSessionDisplayModelProvider;

  private DaggerActivityComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.originalStringProvider =
        new lightmobile_dagger_injection_SessionComponent_originalString(builder.sessionComponent);
    this.sessionNumberProvider =
        new lightmobile_dagger_injection_SessionComponent_sessionNumber(builder.sessionComponent);
    this.provideSessionDisplayModelProvider =
        DoubleCheck.provider(
            ActivityModule_ProvideSessionDisplayModelFactory.create(
                builder.activityModule, originalStringProvider, sessionNumberProvider));
  }

  @Override
  public void inject(SessionActivity activity) {
    injectSessionActivity(activity);
  }

  @Override
  public void inject(SessionEndActivity activity) {
    injectSessionEndActivity(activity);
  }

  private SessionActivity injectSessionActivity(SessionActivity instance) {
    SessionActivity_MembersInjector.injectSessionDisplayModel(
        instance, provideSessionDisplayModelProvider.get());
    return instance;
  }

  private SessionEndActivity injectSessionEndActivity(SessionEndActivity instance) {
    SessionEndActivity_MembersInjector.injectSessionDisplayModel(
        instance, provideSessionDisplayModelProvider.get());
    return instance;
  }

  public static final class Builder {
    private ActivityModule activityModule;

    private SessionComponent sessionComponent;

    private Builder() {}

    public ActivityComponent build() {
      if (activityModule == null) {
        throw new IllegalStateException(ActivityModule.class.getCanonicalName() + " must be set");
      }
      if (sessionComponent == null) {
        throw new IllegalStateException(SessionComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerActivityComponent(this);
    }

    public Builder activityModule(ActivityModule activityModule) {
      this.activityModule = Preconditions.checkNotNull(activityModule);
      return this;
    }

    public Builder sessionComponent(SessionComponent sessionComponent) {
      this.sessionComponent = Preconditions.checkNotNull(sessionComponent);
      return this;
    }
  }

  private static class lightmobile_dagger_injection_SessionComponent_originalString
      implements Provider<String> {
    private final SessionComponent sessionComponent;

    lightmobile_dagger_injection_SessionComponent_originalString(
        SessionComponent sessionComponent) {
      this.sessionComponent = sessionComponent;
    }

    @Override
    public String get() {
      return Preconditions.checkNotNull(
          sessionComponent.originalString(),
          "Cannot return null from a non-@Nullable component method");
    }
  }

  private static class lightmobile_dagger_injection_SessionComponent_sessionNumber
      implements Provider<Integer> {
    private final SessionComponent sessionComponent;

    lightmobile_dagger_injection_SessionComponent_sessionNumber(SessionComponent sessionComponent) {
      this.sessionComponent = sessionComponent;
    }

    @Override
    public Integer get() {
      return Preconditions.checkNotNull(
          sessionComponent.sessionNumber(),
          "Cannot return null from a non-@Nullable component method");
    }
  }
}
