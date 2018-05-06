@Module
public class AppModule {

    @Provides
    @PerApp
    public static String provideHello() {
        return "Hello world!";
    }
}

public final class DaggerAppComponent implements AppComponent {
  private Provider<String> provideHelloProvider;

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideHelloProvider = DoubleCheck.provider(AppModule_ProvideHelloFactory.create());
  }
  
  //module object is needed for non static provides 
  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideHelloProvider =
        DoubleCheck.provider(AppModule_ProvideHelloFactory.create(builder.appModule));
  }

  public static final class Builder {
    private Builder() {}

    public AppComponent build() {
      return new DaggerAppComponent(this);
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder appModule(AppModule appModule) {
      Preconditions.checkNotNull(appModule);
      return this;
    }
  }
}
