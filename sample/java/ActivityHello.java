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
