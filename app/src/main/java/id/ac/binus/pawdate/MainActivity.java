package id.ac.binus.pawdate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        LinearLayout matchmakingSection = findViewById(R.id.matchmaking_section);
        matchmakingSection.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MatchmakingActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.getMenu().add(Menu.NONE, 1, Menu.NONE, "Mates").setIcon(android.R.drawable.ic_menu_myplaces);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == 1) {
                Intent matesIntent = new Intent(MainActivity.this, YourMatesActivity.class);
                startActivity(matesIntent);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(MatchmakingFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
