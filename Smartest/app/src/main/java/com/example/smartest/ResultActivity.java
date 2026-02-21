package com.example.smartest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView tvFinalScore, tvResultMessage;
    private Button btnPlayAgain, btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvFinalScore = findViewById(R.id.tvFinalScore);
        tvResultMessage = findViewById(R.id.tvResultMessage);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnMainMenu = findViewById(R.id.btnMainMenu);

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 5);
        int maxScore = totalQuestions * 10;

        tvFinalScore.setText("Ваш счет: " + score + " из " + maxScore);

        // Определяем сообщение в зависимости от результата
        String message;
        if (score == maxScore) {
            message = "Превосходно! Вы настоящий Smartest!";
        } else if (score >= maxScore * 0.7) {
            message = "Хороший результат! Так держать!";
        } else if (score >= maxScore * 0.4) {
            message = "Неплохо, но есть куда расти!";
        } else {
            message = "Попробуйте еще раз! У вас получится!";
        }
        tvResultMessage.setText(message);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
