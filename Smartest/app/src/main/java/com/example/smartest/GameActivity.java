package com.example.smartest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private TextView tvQuestion,tvScore,tvQuestionNumber;
    private Button btnOption1, btnOption2;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score;
    private int totalQuestions = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViews();
        loadQuestions();
        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < totalQuestions && currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);
            tvQuestion.setText(currentQuestion.getQuestion());
            btnOption1.setText(currentQuestion.getOption1());
            btnOption2.setText(currentQuestion.getOption2());

            // Обновляем счетчик вопросов
            tvQuestionNumber.setText("Вопрос " + (currentQuestionIndex + 1) + " из " + totalQuestions);
            tvScore.setText("Счет: " + score);
        } else {
            // Игра окончена
            showGameOver();
        }
    }

    private void showGameOver() {
        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", totalQuestions);
        startActivity(intent);
        finish();
    }

    public void onOptionClick(View view) {
        Button clickedButton = (Button) view;
        String selectedAnswer = clickedButton.getText().toString();
        Question currentQuestion = questionList.get(currentQuestionIndex);

        // Блокируем кнопки на время проверки
        disableButtons();

        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            // Правильный ответ
            score += 10;
            clickedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
            Toast.makeText(this, "Правильно! +10 очков", Toast.LENGTH_SHORT).show();
        } else {
            // Неправильный ответ
            clickedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));

            // Подсвечиваем правильный ответ
            if (currentQuestion.getOption1().equals(currentQuestion.getCorrectAnswer())) {
                btnOption1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                btnOption2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
            }

            Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
        }

        tvScore.setText("Счет: " + score);

        // Задержка перед следующим вопросом
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetButtonColors();
                enableButtons();
                currentQuestionIndex++;
                displayQuestion();
            }
        }, 1500);
    }

    private void disableButtons() {
        btnOption1.setEnabled(false);
        btnOption2.setEnabled(false);
    }

    private void enableButtons() {
        btnOption1.setEnabled(true);
        btnOption2.setEnabled(true);
    }

    private void resetButtonColors() {
        btnOption1.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnOption2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }


    private void loadQuestions() {
        questionList = new ArrayList<>();

        // Добавляем вопросы
        questionList.add(new Question(
                "Сколько планет в Солнечной системе?",
                "8",
                "9"
        ));

        questionList.add(new Question(
                "Какой газ преобладает в атмосфере Земли?",
                "Азот",
                "Кислород"
        ));

        questionList.add(new Question(
                "Кто написал 'Войну и мир'?",
                "Лев Толстой",
                "Федор Достоевский"
        ));

        questionList.add(new Question(
                "Столица Франции?",
                "Париж",
                "Лондон"
        ));

        questionList.add(new Question(
                "Сколько континентов на Земле?",
                "6",
                "7"
        ));

        questionList.add(new Question(
                "Какой самый большой океан?",
                "Тихий",
                "Атлантический"
        ));

        questionList.add(new Question(
                "В каком году человек впервые высадился на Луну?",
                "1969",
                "1972"
        ));

        questionList.add(new Question(
                "Какое животное является символом Австралии?",
                "Кенгуру",
                "Коала"
        ));

        questionList.add(new Question(
                "Сколько цветов в радуге?",
                "7",
                "6"
        ));

        questionList.add(new Question(
                "Какой самый высокий горный пик в мире?",
                "Эверест",
                "К2"
        ));

        // Перемешиваем вопросы для случайного порядка
        Collections.shuffle(questionList);
    }

    private void initViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
    }
}
