public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<String> initialTextProvider;

  public MainActivity_MembersInjector(Provider<String> initialTextProvider) {
    this.initialTextProvider = initialTextProvider;
  }

  public static MembersInjector<MainActivity> create(Provider<String> initialTextProvider) {
    return new MainActivity_MembersInjector(initialTextProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectInitialText(instance, initialTextProvider.get());
  }

  public static void injectInitialText(MainActivity instance, String initialText) {
    instance.initialText = initialText;
  }
}
