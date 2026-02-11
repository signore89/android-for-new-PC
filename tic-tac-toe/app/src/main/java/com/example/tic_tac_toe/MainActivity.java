package com.example.tic_tac_toe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText playerOne, playerTwo;
    private Button startGameButton, startGameWithBotButton;
    private RadioGroup radioGroupDifficulty;
    private RadioButton radioEasy, radioMedium, radioHard;
    private String selectedDifficulty = "Medium";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = findViewById(R.id.playerOne);
        playerTwo = findViewById(R.id.playerTwo);
        startGameButton = findViewById(R.id.startGameButton);
        startGameWithBotButton = findViewById(R.id.startGameUsBotButton);
        radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);
        radioEasy = findViewById(R.id.radioEasy);
        radioMedium = findViewById(R.id.radioMedium);
        radioHard = findViewById(R.id.radioHard);

        radioGroupDifficulty.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioEasy) {
                selectedDifficulty = "Easy";
            } else if (checkedId == R.id.radioMedium) {
                selectedDifficulty = "Medium";
            } else if (checkedId == R.id.radioHard) {
                selectedDifficulty = "Hard";
            }
        });


        startGameButton.setOnClickListener(v ->{
            startGame(false);
        });
        startGameWithBotButton.setOnClickListener(v->{
            startGame(true);

        });
        loadSavedDifficulty();
    }
    private void startGame(boolean vsBoost){
        if(!vsBoost){
            String getPlayerOneName = playerOne.getText().toString().trim();
            String getPlayerTwoName = playerTwo.getText().toString().trim();
            if(getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()){
                Toast.makeText(MainActivity.this, "Please enter player name", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, PlayingField.class);
                intent.putExtra("playerOne", getPlayerOneName);
                intent.putExtra("playerTwo", getPlayerTwoName);
                intent.putExtra("vsBoost", vsBoost);
                startActivity(intent);
            }
        }else{
            String getPlayerOneName = playerOne.getText().toString().trim();
            String getPlayerTwoName = "Bot";
            if (getPlayerOneName.isEmpty()) getPlayerOneName = "Player";
            Intent intent = new Intent(MainActivity.this, PlayingField.class);
            intent.putExtra("playerOne", getPlayerOneName);
            intent.putExtra("playerTwo", getPlayerTwoName);
            intent.putExtra("vsBoost", vsBoost);
            intent.putExtra("difficulty", selectedDifficulty);
            startActivity(intent);
        }
    }

    private void saveDifficulty(String difficulty){
        SharedPreferences preferences = getSharedPreferences("game settings",MODE_PRIVATE);
        preferences.edit().putString("difficulty",difficulty).apply();
    }

    private void loadSavedDifficulty() {
        SharedPreferences prefs = getSharedPreferences("game_settings", MODE_PRIVATE);
        String savedDifficulty = prefs.getString("difficulty", "Medium");

        switch (savedDifficulty) {
            case "Easy":
                radioEasy.setChecked(true);
                selectedDifficulty = "Easy";
                break;
            case "Medium":
                radioMedium.setChecked(true);
                selectedDifficulty = "Medium";
                break;
            case "Hard":
                radioHard.setChecked(true);
                selectedDifficulty = "Hard";
                break;
        }
    }
}