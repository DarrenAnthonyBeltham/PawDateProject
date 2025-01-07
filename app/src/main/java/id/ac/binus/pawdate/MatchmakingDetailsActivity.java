package id.ac.binus.pawdate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MatchmakingDetailsActivity extends AppCompatActivity {

    private TextView tvPetName, tvBreed, tvAge, tvDescription, tvVaccinated;
    private ImageView ivPetImage;
    private Button btnMatch, btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchmaking_details);

        tvPetName = findViewById(R.id.tvPetName);
        tvBreed = findViewById(R.id.tvBreed);
        tvAge = findViewById(R.id.tvAge);
        tvDescription = findViewById(R.id.tvDescription);
        tvVaccinated = findViewById(R.id.tvVaccinated);
        ivPetImage = findViewById(R.id.ivPetImage);
        btnMatch = findViewById(R.id.btnMatch);
        btnNo = findViewById(R.id.btnNo);

        Intent intent = getIntent();
        int petId = intent.getIntExtra("petId", -1);
        String petName = intent.getStringExtra("petName");
        String breed = intent.getStringExtra("breed");
        int age = intent.getIntExtra("age", 0);
        String description = intent.getStringExtra("description");
        boolean vaccinated = intent.getBooleanExtra("vaccinated", false);
        int imageResId = intent.getIntExtra("imageResId", R.drawable.husky);

        tvPetName.setText(petName);
        tvBreed.setText(breed);
        tvAge.setText("Age: " + age + " years");
        tvDescription.setText(description);
        tvVaccinated.setText(vaccinated ? "Vaccinated: Yes" : "Vaccinated: No");
        ivPetImage.setImageResource(imageResId);

        btnMatch.setOnClickListener(v -> {
            Toast.makeText(MatchmakingDetailsActivity.this, "Matched", Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("petId", petId);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnNo.setOnClickListener(v -> {
            Toast.makeText(MatchmakingDetailsActivity.this, "Not matched", Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("petId", petId);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
