package id.ac.binus.pawdate;

import android.database.Cursor;
import android.os.Bundle;
import android.service.autofill.FieldClassification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMatches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int userId = getIntent().getIntExtra("userId", -1);
        if (userId == -1) {
            finish();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this, "PawDateDatabase", null, 1);
        Cursor cursor = dbHelper.getMatchesForUser(userId);

        ArrayList<Match> matchList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int matchId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int petId = cursor.getInt(cursor.getColumnIndexOrThrow("pet_id"));

                String petName = "Sample Name";
                String petBreed = "Sample Breed";
                int petAge = 2;
                boolean vaccinated = true;
                int imageResId = R.drawable.husky;

                matchList.add(new Match(matchId, petId, petName, petBreed, petAge, vaccinated, imageResId));
            }
            cursor.close();
        }


        MatchesAdapter adapter = new MatchesAdapter(this, matchList, new MatchesAdapter.OnMatchActionListener() {
            @Override
            public void onViewDetailsClick(Match match) {
                // Handle "View Details" action
            }

            @Override
            public void onDeleteMatchClick(Match match) {
                // Handle "Delete Match" action
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
