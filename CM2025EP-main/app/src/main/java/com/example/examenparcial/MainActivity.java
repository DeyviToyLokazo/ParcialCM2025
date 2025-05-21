package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public EditText eUser;
    public EditText eEdad;
    public EditText eCelular;
    public EditText eCorreo;
    public static final String extrauser = "userNombre";
    public static final String extrauser2 = "userEdad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.vista), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        eUser = findViewById(R.id.txtNombre);
        eEdad = findViewById(R.id.txtEdad);
        eCelular = findViewById(R.id.txtCelular);
        eCorreo = findViewById(R.id.txtCorreo);

    }
    public void ingresarSaludar(View view){
        String nombre = eUser.getText().toString();
        String edad = eEdad.getText().toString();
        String celular = eCelular.getText().toString();
        String correo = eCorreo.getText().toString();

        if(validarNombre(nombre)==true){
            if(validarEdad(edad)==3){
                Toast.makeText(this, "Error: La edad no puede estar vacio : "+edad, Toast.LENGTH_SHORT).show();
            }
            else if(validarEdad(edad)==0){
                Toast.makeText(this, "Error: La edad debe ser un número entero : "+edad, Toast.LENGTH_SHORT).show();
            }
            else if(validarEdad(edad)==1){
                Toast.makeText(this, "Error: La edad debe estar entre 0 y 99 : "+edad, Toast.LENGTH_SHORT).show();
            }
            else if(validarEdad(edad)==2){
                if(validarCelular(celular)==0){
                    Toast.makeText(this, "Error: El celular no puede estar vacio", Toast.LENGTH_SHORT).show();
                }
                else if(validarCelular(celular)==1){
                    Toast.makeText(this, "Error: el celular debe ser un numero entero", Toast.LENGTH_SHORT).show();
                }
                else if(validarCelular(celular)==2){
                    Toast.makeText(this, "Error: El celular debe contener exactamente 9 dígitos numéricos.", Toast.LENGTH_SHORT).show();
                }
                else if(validarCelular(celular)==3){
                    if(validarCorreo(correo)==false){
                        Toast.makeText(this, "Error: El correo electrónico no tiene un formato válido.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Bienvenido "+nombre, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, Secundario.class);
                        i.putExtra(extrauser2,edad);
                        i.putExtra(extrauser, nombre);
                        startActivity(i);
                    }
                }
            }
        }
        else{
            Toast.makeText(this, "Error: El nombre no puede estar vacío : "+nombre, Toast.LENGTH_SHORT).show();
        }

    }

    public boolean validarNombre(String name){
        if (name.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return false;
        }
        else{
            System.out.println("Valor nombre correcto validado");
            return true;
        }
    }

    public int validarEdad(String edadUser){
        if (edadUser.isEmpty()) {
            System.out.println("Error: La edad no puede estar vacio");
            return 3;
        }
        else if (!edadUser.trim().matches("-?\\d+")) {
            System.out.println("Error: La edad debe ser un número entero");
            return 0;
        }
        else{
            int edad = Integer.parseInt(edadUser);
            if (edad < 0 || edad > 99) {
                System.out.println("Error: La edad debe estar entre 0 y 99");
                return 1;
            }
            else{
                System.out.println("Valor edad correcto validado");
                return 2;
            }
        }
    }

    public int validarCelular(String cel){
        if (cel.isEmpty()) {
            System.out.println("Error: El celular no puede estar vacio ");
            return 0;
        }
        else if (!cel.matches("\\d+")){
            System.out.println("Error: el celular debe ser un numero entero");
            return 1;
        }
        else if (!cel.matches("\\d{9}")) {
            System.out.println("Error: El celular debe contener exactamente 9 dígitos numéricos.");
            return 2;
        }
        else{
            System.out.println("Valor celular correcto validado");
            return 3;
        }
    }

    public boolean validarCorreo(String imail){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.matches(emailRegex, imail)) {
            System.out.println("Error: El correo electrónico no tiene un formato válido.");
            return false;
        }
        else{
            System.out.println("El correo electrónico tiene un formato válido.");
            return true;
        }

    }
}