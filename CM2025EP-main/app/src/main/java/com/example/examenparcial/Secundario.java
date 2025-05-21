package com.example.examenparcial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Secundario extends AppCompatActivity {
    public TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secundario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.vista), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv1 = findViewById(R.id.txtMensajePanel);

        String user = getIntent().getStringExtra(MainActivity.extrauser);
        String edad = getIntent().getStringExtra(MainActivity.extrauser2);
        String respuesta = "Hola " +user+" tiene: "+edad+" es un "+ValidarCriterio(Integer.parseInt(edad));
        tv1.setText(respuesta);
    }
    public String ValidarCriterio(int edad){
        if(edad>=0 && edad<=2){
            return "bebe";
        }
        else if(edad>=3 && edad<=12){
            return "niño";
        }
        else if(edad>=13 && edad<=18){
            return "adolescente";
        }
        else if(edad>=19 && edad<=50){
            return "adulto";
        }
        else{
            return "anciano";
        }
    }

}
