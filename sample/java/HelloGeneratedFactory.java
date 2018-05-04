public final class AppModule_ProvideHelloFactory implements Factory<String> {
  private final AppModule module;

  public AppModule_ProvideHelloFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public String get() {
    return Preconditions.checkNotNull(
        module.provideHello(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static AppModule_ProvideHelloFactory create(AppModule module) {
    return new AppModule_ProvideHelloFactory(module);
  }

  public static String proxyProvideHello(AppModule instance) {
    return Preconditions.checkNotNull(
        instance.provideHello(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

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
