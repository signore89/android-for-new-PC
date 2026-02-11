package com.example.myfirstappandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        EditText editText = new EditText(this);
        editText.setHint("Введите Email");
        editText.setId(View.generateViewId());
        Button button = new Button(this);
        button.setText("Отправить");
        button.setId(View.generateViewId());

        ConstraintLayout.LayoutParams editTextLayout = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
        editTextLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        editTextLayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        editTextLayout.rightToLeft = button.getId();
        editText.setLayoutParams(editTextLayout);
        constraintLayout.addView(editText);

        ConstraintLayout.LayoutParams buttonLayout = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
        buttonLayout.leftToRight = editText.getId();
        buttonLayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        button.setLayoutParams(buttonLayout);
        constraintLayout.addView(button);

        setContentView(constraintLayout);fdfd
    }
}