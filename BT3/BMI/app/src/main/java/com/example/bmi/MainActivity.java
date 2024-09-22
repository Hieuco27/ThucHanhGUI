package com.example.bmi;

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

    EditText edtNhapTen, edtChieuCao, edtCanNang, edtBMI, edtChuandoan;
    Button btnTinhBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtNhapTen = findViewById(R.id.edtNhapTen); // Update with the correct ID
        edtChieuCao = findViewById(R.id.edtChieuCao); // Update with the correct ID
        edtCanNang = findViewById(R.id.edtCanNang); // Update with the correct ID
        edtBMI = findViewById(R.id.edtBMI); // Update with the correct ID
        edtChuandoan = findViewById(R.id.edtChuandoan); // Update with the correct ID
        btnTinhBMI = findViewById(R.id.btnTinhBMI); // Update with the correct ID

        btnTinhBMI.setOnClickListener(v -> {
            String ten = edtNhapTen.getText().toString();
            String chieucaoStr = edtChieuCao.getText().toString();
            String cannangStr = edtCanNang.getText().toString();

            if (ten.isEmpty() || chieucaoStr.isEmpty() || cannangStr.isEmpty()) {
                if (ten.isEmpty()) {
                    edtNhapTen.setError("Vui lòng nhập tên");
                }
                if (chieucaoStr.isEmpty()) {
                    edtChieuCao.setError("Vui lòng nhập chiều cao");
                }
                if (cannangStr.isEmpty()) {
                    edtCanNang.setError("Vui lòng nhập cân nặng");
                }
                return;
            }

            try {
                float cc = Float.parseFloat(chieucaoStr) / 100; // Convert cm to meters
                float canNang = Float.parseFloat(cannangStr);

                // Calculate BMI
                float bmi = canNang / (cc * cc); // Corrected formula
                edtBMI.setText(String.format("%.2f", bmi));

                // Determine diagnosis based on BMI
                String diagnosis;
                if (bmi < 18) {
                    diagnosis = "Bạn gầy ";
                } else if (bmi <= 24.9) {
                    diagnosis = "Bạn bình thường";
                } else if (bmi <= 29.9) {
                    diagnosis = "Bạn béo phì độ 1";
                } else if (bmi <= 34.9) {
                    diagnosis = "Bạn béo phì độ 2";
                } else {
                    diagnosis = "Bạn béo phì độ 3";
                }
                edtChuandoan.setText(diagnosis);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}