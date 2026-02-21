package com.example.smartest;

public class Question {
    private String question;
    private String option1;
    private String option2;
    private String correctAnswer;
    public Question(String question, String correctAnswer, String wrongAnswer) {
        this.question = question;
        this.option1 = correctAnswer;
        this.option2 = wrongAnswer;
        this.correctAnswer = correctAnswer;

        // Случайным образом меняем местами правильный и неправильный ответ
        if (Math.random() > 0.5) {
            this.option1 = wrongAnswer;
            this.option2 = correctAnswer;
        }
    }
    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
