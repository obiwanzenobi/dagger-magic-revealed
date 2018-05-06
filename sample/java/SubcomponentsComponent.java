@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {

    StartSessionActivityComponent plus(StartSessionActivityModule module);

    SessionComponent.Builder sessionComponentBuilder();

    void inject(BaseSessionActivity sessionActivity);
}

@PerActivity
@Subcomponent(modules = StartSessionActivityModule.class)
public interface StartSessionActivityComponent {
    void inject(MainActivity activity);
}

@PerSession
@Subcomponent(modules = SessionModule.class)
public interface SessionComponent {

    @Subcomponent.Builder
    interface Builder {
        SessionComponent.Builder sessionModule(SessionModule sessionModule);
        SessionComponent build();
    }

    SessionActivityComponent plus(SessionActivityModule activityModule);
}

@PerActivity
@Subcomponent(modules = SessionActivityModule.class)
public interface SessionActivityComponent {
    void inject(SessionActivity activity);

    void inject(SessionEndActivity activity);
}

public final class DaggerAppComponent implements AppComponent {
//...
  @Override
  public StartSessionActivityComponent plus(StartSessionActivityModule module) {
    return new StartSessionActivityComponentImpl(module);
  }

  private final class StartSessionActivityComponentImpl implements StartSessionActivityComponent {
    private StartSessionActivityComponentImpl(StartSessionActivityModule module) {}

    @Override
    public void inject(MainActivity activity) {
      injectMainActivity(activity);
    }

    private MainActivity injectMainActivity(MainActivity instance) {
      MainActivity_MembersInjector.injectSessionManager(
          instance, DaggerAppComponent.this.provideSessionManagerProvider.get());
      return instance;
    }
  }

  private final class SessionComponentBuilder implements SessionComponent.Builder {
    private SessionModule sessionModule;

    @Override
    public SessionComponent build() {
      if (sessionModule == null) {
        throw new IllegalStateException(SessionModule.class.getCanonicalName() + " must be set");
      }
      return new SessionComponentImpl(this);
    }

    @Override
    public SessionComponentBuilder sessionModule(SessionModule sessionModule) {
      this.sessionModule = Preconditions.checkNotNull(sessionModule);
      return this;
    }
  }

  private final class SessionComponentImpl implements SessionComponent {
    private Provider<Session> provideSessionNumberProvider;

    private SessionComponentImpl(SessionComponentBuilder builder) {
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SessionComponentBuilder builder) {
      this.provideSessionNumberProvider =
          DoubleCheck.provider(
              SessionModule_ProvideSessionNumberFactory.create(builder.sessionModule));
    }

    @Override
    public SessionActivityComponent plus(SessionActivityModule activityModule) {
      return new SessionActivityComponentImpl(activityModule);
    }

    private final class SessionActivityComponentImpl implements SessionActivityComponent {
      private Provider<SessionActivityPresenter> sessionActivityPresenterProvider;

      private SessionActivityComponentImpl(SessionActivityModule activityModule) {
        initialize(activityModule);
      }

      @SuppressWarnings("unchecked")
      private void initialize(final SessionActivityModule activityModule) {
        this.sessionActivityPresenterProvider =
            DoubleCheck.provider(
                SessionActivityPresenter_Factory.create(
                    SessionComponentImpl.this.provideSessionNumberProvider));
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
        BaseSessionActivity_MembersInjector.injectSessionManager(
            instance, DaggerAppComponent.this.provideSessionManagerProvider.get());
        SessionActivity_MembersInjector.injectPresenter(
            instance, sessionActivityPresenterProvider.get());
        return instance;
      }

      private SessionEndActivity injectSessionEndActivity(SessionEndActivity instance) {
        BaseSessionActivity_MembersInjector.injectSessionManager(
            instance, DaggerAppComponent.this.provideSessionManagerProvider.get());
        SessionEndActivity_MembersInjector.injectPresenter(
            instance, sessionActivityPresenterProvider.get());
        return instance;
      }
    }
  }
}
