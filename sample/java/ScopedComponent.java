@Scope
@Retention(RUNTIME)
public @interface PerApp {
}

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerApp
    public String provideHello() {
        return context.getString(R.string.hello);
    }
}

public final class DaggerAppComponent implements AppComponent {
  private Provider<String> provideHelloProvider;

  private DaggerAppComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideHelloProvider =
        DoubleCheck.provider(AppModule_ProvideHelloFactory.create(builder.appModule));
  }

  @Override
  public void inject(MainActivity activity) {
    injectMainActivity(activity);
  }

  private MainActivity injectMainActivity(MainActivity instance) {
    MainActivity_MembersInjector.injectInitialText(
        instance,
        provideHelloProvider.get()
    );
    return instance;
  }
  
  //unscoped 
  private MainActivity injectMainActivity(MainActivity instance) {
    MainActivity_MembersInjector.injectInitialText(
        instance, AppModule_ProvideHelloFactory.proxyProvideHello(appModule));
    return instance;
  }
}
