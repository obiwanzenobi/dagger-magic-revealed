@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerApp
    public SessionManager provideSessionManager(SessionComponent.Builder sessionComponentBuilder) {
        return new SessionManager(sessionComponentBuilder);
    }

    @Provides
    @Reusable
    String provideRawUrl() {
        return "http://google.com";
    }

    @Provides
    @PerApp
    Uri provideUrl(String rawUrl) {
        return Uri.parse(rawUrl);
    }
}

@Module
public class SessionModule {

    private Session session;

    public SessionModule(Session session) {
        this.session = session;
    }

    @PerSession
    @Provides
    Session provideSessionNumber(Uri uri) {
        return session;
    }
}

public final class SingleCheck<T> implements Provider<T>, Lazy<T> {
  private static final Object UNINITIALIZED = new Object();

  private volatile Provider<T> provider;
  private volatile Object instance = UNINITIALIZED;

  private SingleCheck(Provider<T> provider) {
    assert provider != null;
    this.provider = provider;
  }

  @SuppressWarnings("unchecked") // cast only happens when result comes from the delegate provider
  @Override
  public T get() {
    // provider is volatile and might become null after the check to instance == UNINITIALIZED, so
    // retrieve the provider first, which should not be null if instance is UNINITIALIZED.
    // This relies upon instance also being volatile so that the reads and writes of both variables
    // cannot be reordered.
    Provider<T> providerReference = provider;
    if (instance == UNINITIALIZED) {
      instance = providerReference.get();
      // Null out the reference to the provider. We are never going to need it again, so we can make
      // it eligible for GC.
      provider = null;
    }
    return (T) instance;
  }

  /** Returns a {@link Provider} that caches the value from the given delegate provider. */
  // This method is declared this way instead of "<T> Provider<T> provider(Provider<T> provider)" 
  // to work around an Eclipse type inference bug: https://github.com/google/dagger/issues/949.
  public static <P extends Provider<T>, T> Provider<T> provider(P provider) {
    // If a scoped @Binds delegates to a scoped binding, don't cache the value again.
    if (provider instanceof SingleCheck || provider instanceof DoubleCheck) {
      return provider;
    }
    return new SingleCheck<T>(checkNotNull(provider));
  }
}

public final class DaggerAppComponent implements AppComponent {
  //...

  private Provider<Uri> provideUrlProvider;
  
  //...

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    //...
    this.provideRawUrlProvider =
        SingleCheck.provider(AppModule_ProvideRawUrlFactory.create(builder.appModule));
    //...
  }

  private final class StartSessionActivityComponentImpl implements StartSessionActivityComponent {

    private Provider<String> provideRawUrlProvider;

    //...
   
    @SuppressWarnings("unchecked")
    private void initialize(final StartSessionActivityModule module) {
      this.provideRawUrlProvider =
          SingleCheck.provider(
              AppModule_ProvideRawUrlFactory.create(DaggerAppComponent.this.appModule));
    }

    //...
  }

  //...
}

