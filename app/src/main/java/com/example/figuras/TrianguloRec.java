package com.example.figuras;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class TrianguloRec extends AppCompatActivity {

    Button btnSalir, btnEnviar;
    EditText txtLado1, txtLado2;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_triangulo_rec);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(v -> finish());
        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(v -> calcularAreaPerimetro());
        txtLado1 = findViewById(R.id.txtAltura);
        txtLado2 = findViewById(R.id.txtBase);
        txtResultado = findViewById(R.id.btnRespuesta);
        txtLado1.setOnClickListener(
                v -> {
                    txtLado1.getText().clear();
                }
        );
        txtLado2.setOnClickListener(
                v -> {
                    txtLado2.getText().clear();
                }
        );

    }

    private void calcularAreaPerimetro() {
        String lado1 = txtLado1.getText().toString().trim();
        String lado2 = txtLado2.getText().toString().trim();

        if (lado1.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un diámetro", Toast.LENGTH_SHORT).show();
            return;
        }else if (lado2.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un diámetro", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://169.254.132.108:3000/Triangulo?l1=" + lado1 + "&l2=" + lado2;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String figura = jsonResponse.getString("Figura");
                            String hipotenusa = jsonResponse.getString("Hipotenusa");
                            String area = jsonResponse.getString("Area");
                            String perimetro = jsonResponse.getString("Perimetro");

                            String resultado = "Figura: " + figura + "\n" +
                                    "Hipotenusa: " + hipotenusa + "\n" +
                                    "Área: " + area + "\n" +
                                    "Perímetro: " + perimetro;

                            txtResultado.setText(resultado);

                        } catch (Exception e) {
                            txtResultado.setText("Error al procesar la respuesta.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtResultado.setText("Error: " + error.getMessage());
                    }
                });

        queue.add(stringRequest);
    }
}