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
}
