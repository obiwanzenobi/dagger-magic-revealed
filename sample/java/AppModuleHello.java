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
