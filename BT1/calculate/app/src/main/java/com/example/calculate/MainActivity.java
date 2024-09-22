package com.example.calculate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtkq;
    Button btnTong, btnHieu, btnTich, btnThuong, btnUCLN, btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtkq = findViewById(R.id.edtkq);
        btnTong = findViewById(R.id.btnTong);
        btnHieu = findViewById(R.id.btnHieu);
        btnTich = findViewById(R.id.btnTich);
        btnThuong = findViewById(R.id.btnThuong);
        btnUCLN = findViewById(R.id.btnUCLN);
        btnThoat = findViewById(R.id.btnThoat);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnTong.setOnClickListener(view -> {
            try {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int tong = a + b;
                edtkq.setText(String.valueOf(tong));
            } catch (NumberFormatException e) {
                edtkq.setText("Vui lòng nhập số hợp lệ");
            }
        });

        btnHieu.setOnClickListener(view -> {
            try {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int hieu = a - b;
                edtkq.setText(String.valueOf(hieu));
            } catch (NumberFormatException e) {
                edtkq.setText("Vui lòng nhập số hợp lệ");
            }
        });

        btnTich.setOnClickListener(view -> {
            try {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int tich = a * b;
                edtkq.setText(String.valueOf(tich));
            } catch (NumberFormatException e) {
                edtkq.setText("Vui lòng nhập số hợp lệ");
            }
        });

        btnThuong.setOnClickListener(view -> {
            try {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                if (b != 0) {
                    int thuong = a / b;
                    edtkq.setText(String.valueOf(thuong));
                } else {
                    edtkq.setText("Không thể chia cho 0");
                }
            } catch (NumberFormatException e) {
                edtkq.setText("Vui lòng nhập số hợp lệ");
            }
        });

        btnUCLN.setOnClickListener(view -> {
            try {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());

                while (b != 0) {
                    int temp = b;
                    b = a % b;
                    a = temp;
                }
                edtkq.setText(String.valueOf(a)); // Hiển thị kết quả
            } catch (NumberFormatException e) {
                edtkq.setText("Vui lòng nhập số hợp lệ");
            }
        });

        btnThoat.setOnClickListener(view -> finish());
    }
}