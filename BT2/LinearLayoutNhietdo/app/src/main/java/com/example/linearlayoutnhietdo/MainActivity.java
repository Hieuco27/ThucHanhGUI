package com.example.linearlayoutnhietdo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edtF, edtC;
    Button btnFC, btnCF, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        // Khởi tạo các biến
        edtC = findViewById(R.id.edtC);
        edtF = findViewById(R.id.edtF);
        btnFC = findViewById(R.id.btnFC);
        btnCF = findViewById(R.id.btnCF);
        btnClear = findViewById(R.id.btnclear);

        // Áp dụng cửa sổ insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Chuyển đổi từ Celsius sang Fahrenheit
        btnFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double C = Double.parseDouble(edtC.getText().toString());
                    double F = (C * 9 / 5) + 32;
                    edtF.setText(String.format(Locale.getDefault(), "%.2f", F));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập giá trị hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Chuyển đổi từ Fahrenheit sang Celsius
        btnCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double F = Double.parseDouble(edtF.getText().toString());
                    double C = (F - 32) * 5 / 9;
                   edtC.setText(String.format(Locale.getDefault(),"%.2f", C));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập giá trị hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xóa giá trị
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtF.setText("");
                edtC.setText("");
            }
        });
    }
}