package com.example.handheldcomputer;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private StringBuilder input = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        // Thêm sự kiện cho các nút
        findViewById(R.id.btnC).setOnClickListener(view -> clearInput());
        findViewById(R.id.btnDel).setOnClickListener(view -> deleteLastCharacter());
        findViewById(R.id.btnbang).setOnClickListener(view -> calculateResult());

        // Các nút số
        setNumberButtonClickListener(R.id.btn0, "0");
        setNumberButtonClickListener(R.id.btn1, "1");
        setNumberButtonClickListener(R.id.btn2, "2");
        setNumberButtonClickListener(R.id.btn3, "3");
        setNumberButtonClickListener(R.id.btn4, "4");
        setNumberButtonClickListener(R.id.btn5, "5");
        setNumberButtonClickListener(R.id.btn6, "6");
        setNumberButtonClickListener(R.id.btn7, "7");
        setNumberButtonClickListener(R.id.btn8, "8");
        setNumberButtonClickListener(R.id.btn9, "9");


        // Các nút toán học
        setNumberButtonClickListener(R.id.btnphay, ".");
        setOperationButtonClickListener(R.id.btncong, "+");
        setOperationButtonClickListener(R.id.btntru, "-");
        setOperationButtonClickListener(R.id.btncheo, "/");
        setOperationButtonClickListener(R.id.btnnhan, "x");
    }

    private void setNumberButtonClickListener(int buttonId, String number) {
        findViewById(buttonId).setOnClickListener(view -> {
            input.append(number);
            tvResult.setText(input.toString());
        });
    }

    private void setOperationButtonClickListener(int buttonId, String operation) {
        findViewById(buttonId).setOnClickListener(view -> {
            input.append(operation);
            tvResult.setText(input.toString());
        });
    }

    private void clearInput() {
        input.setLength(0);
        tvResult.setText("");
    }

    private void deleteLastCharacter() {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
            tvResult.setText(input.toString());
        }
    }

    private void calculateResult() {
        String result = evaluateExpression(input.toString());
        tvResult.setText(result);
        input.setLength(0); // Xóa input sau khi tính toán
    }

    private String evaluateExpression(String expression) {
        try {
            // Thực hiện đánh giá biểu thức đơn giản
            // Bạn có thể sử dụng một thư viện như exp4j cho điều này
            // Hoặc viết một hàm đánh giá đơn giản
            Stack<Double> numbers = new Stack<>();
            Stack<Character> operations = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (Character.isDigit(c) || c == '.') {
                    StringBuilder num = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        num.append(expression.charAt(i++));
                    }
                    numbers.push(Double.parseDouble(num.toString()));
                    i--; // Để bù cho vòng lặp
                } else if (c == '+' || c == '-' || c == 'x' || c == '/') {
                    while (!operations.isEmpty() && precedence(c) <= precedence(operations.peek())) {
                        double b = numbers.pop();
                        double a = numbers.pop();
                        char op = operations.pop();
                        numbers.push(applyOperation(a, b, op));
                    }
                    operations.push(c);
                }
            }

            while (!operations.isEmpty()) {
                double b = numbers.pop();
                double a = numbers.pop();
                char op = operations.pop();
                numbers.push(applyOperation(a, b, op));
            }

            return String.valueOf(numbers.pop());
        } catch (Exception e) {
            return "Error";
        }
    }

    private int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case 'x':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private double applyOperation(double a, double b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'x':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }
}