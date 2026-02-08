package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private TextView textViewResult;
    private Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        //кнопка сложения
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("+");
            }
        });
        //кнопка вычитания
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("-");
            }
        });
        //кнопка умножения
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("*");
            }
        });
        //кнопка деления
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("/");
            }
        });
        //кнопка очистки
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });
    }

    private void performOperation(String operation){
        //получаем значения из полей
        String strNum1 = editTextNumber1.getText().toString().trim();
        String strNum2 = editTextNumber2.getText().toString().trim();
        //проверка на пустые поля
         if (strNum1.isEmpty() || strNum2.isEmpty()){
             Toast.makeText(this,"Please enter both numbers",Toast.LENGTH_SHORT).show();
             return;
         }
         try {
             //преобразуем строки в число
             double num1 = Double.parseDouble(strNum1);
             double num2 = Double.parseDouble(strNum2);
             double result = 0;
             String operationSymbol ="";
             String operationText ="";

             //выполняем операцию в зависимости от выбраной кнопки
             switch (operation){
                 case "+":
                     result = num1 + num2;
                     operationSymbol = "+";
                     operationText = "Addition";
                     break;
                 case "-":
                     result = num1 - num2;
                     operationSymbol = "-";
                     operationText = "Subtraction";
                     break;
                 case "*":
                     result = num1 * num2;
                     operationSymbol = "*";
                     operationText = "Multiplication";
                     break;
                 case "/":
                     if (num2 == 0) {
                         textViewResult.setText("Error: Division by zero!");
                         Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                         return;
                     }
                     result = num1 / num2;
                     operationSymbol = "/";
                     operationText = "Division";
                     break;
             }
             // формируем результат для отображения
             String formattedResult;
             if (result == (long)result){
                 formattedResult = String.format("%.0f", result);
             } else {
                 formattedResult = String.format("%.2f", result);
             }
             // Формируем строку результата как в вашем примере
             String resultString = String.format("%s: %.1f %s %.1f = %s",
                     operationText, num1, operationSymbol, num2, formattedResult);
             // Отображаем результат
             textViewResult.setText(resultString);
         } catch (NumberFormatException n){
             Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
         }catch (Exception e){
             Toast.makeText(this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
         }
    }

    private void clearAll(){
        // Очищаем все поля
        editTextNumber1.setText("");
        editTextNumber2.setText("");
        textViewResult.setText("");
        //возвращаем фокус на первое поле
        editTextNumber1.requestFocus();
        Toast.makeText(this, "All fields cleared", Toast.LENGTH_SHORT).show();
    }
    private void initializeViews() {
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        textViewResult = findViewById(R.id.textViewResult);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonClear = findViewById(R.id.buttonClear);
    }



}