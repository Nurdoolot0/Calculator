package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private String currentOperator;
    private double firstValue;
    private boolean isOperatorPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String currentText = tvResult.getText().toString();
                String newText = button.getText().toString();

                if (currentText.equals("0") || isOperatorPressed) {
                    tvResult.setText(newText);
                    isOperatorPressed = false;
                } else {
                    tvResult.setText(currentText + newText);
                }
            }
        };

        findViewById(R.id.btn0).setOnClickListener(listener);
        findViewById(R.id.btn1).setOnClickListener(listener);
        findViewById(R.id.btn2).setOnClickListener(listener);
        findViewById(R.id.btn3).setOnClickListener(listener);
        findViewById(R.id.btn4).setOnClickListener(listener);
        findViewById(R.id.btn5).setOnClickListener(listener);
        findViewById(R.id.btn6).setOnClickListener(listener);
        findViewById(R.id.btn7).setOnClickListener(listener);
        findViewById(R.id.btn8).setOnClickListener(listener);
        findViewById(R.id.btn9).setOnClickListener(listener);
        findViewById(R.id.btnDot).setOnClickListener(listener);
    }

    private void setOperatorButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentOperator = button.getText().toString();
                firstValue = Double.parseDouble(tvResult.getText().toString());
                isOperatorPressed = true;
            }
        };

        findViewById(R.id.btnPlus).setOnClickListener(listener);
        findViewById(R.id.btnMinus).setOnClickListener(listener);
        findViewById(R.id.btnMultiply).setOnClickListener(listener);
        findViewById(R.id.btnDivide).setOnClickListener(listener);

        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double secondValue = Double.parseDouble(tvResult.getText().toString());
                double result = calculate(firstValue, secondValue, currentOperator);
                displayResult(result);
                isOperatorPressed = true;
            }
        });

        findViewById(R.id.btnAC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("0");
                currentOperator = "";
                firstValue = 0;
                isOperatorPressed = false;
            }
        });

        findViewById(R.id.btnPlusMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value = Double.parseDouble(tvResult.getText().toString());
                value = -value;
                displayResult(value);  // Используем displayResult для вывода результата
            }
        });

        findViewById(R.id.btnPercent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value = Double.parseDouble(tvResult.getText().toString());
                value = value / 100;
                displayResult(value);  // Используем displayResult для вывода результата
                isOperatorPressed = true;
            }
        });
    }

    private double calculate(double firstValue, double secondValue, String operator) {
        switch (operator) {
            case "+":
                return firstValue + secondValue;
            case "−":
                return firstValue - secondValue;
            case "×":
                return firstValue * secondValue;
            case "÷":
                if (secondValue != 0) {
                    return firstValue / secondValue;
                } else {
                    return 0; // Или обработать ошибку деления на ноль
                }
            default:
                return secondValue;
        }
    }

    // Метод для отображения результата
    private void displayResult(double result) {
        if (result == (long) result) {
            tvResult.setText(String.valueOf((long) result));
        } else {
            tvResult.setText(String.valueOf(result));
        }
    }
}
