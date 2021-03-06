package nz.co.trademe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import nz.co.trademe.R;
import nz.co.trademe.fragment.CategoryFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CategoryFragment())
                    .commit();
        }

    }
}
