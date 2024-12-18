package com.example.figuras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnCirculo, btnRectangulo, btnTriangulo, btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCirculo = (Button) findViewById(R.id.btnCirculo);
        btnRectangulo = (Button) findViewById(R.id.btnRectangulo);
        btnTriangulo = (Button) findViewById(R.id.btnTriangulo);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        btnCirculo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Circulo.class);
            startActivity(intent);
        });
        btnRectangulo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Rectangulo.class);
            startActivity(intent);
        });

        btnTriangulo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TrianguloRec.class);
            startActivity(intent);
        });

        btnSalir.setOnClickListener(v -> {
            finish();
        });


    }
}