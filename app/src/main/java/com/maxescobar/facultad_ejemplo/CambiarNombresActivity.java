package com.maxescobar.facultad_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CambiarNombresActivity extends AppCompatActivity {

    //Los EditText
    private EditText etName1, etName2;
    private String respuesta, nombreA, nombreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_nombres);

        //Lo de siempre lo relacionamos con la interfaz
        etName1 = (EditText) findViewById(R.id.etName1);
        etName2 = (EditText) findViewById(R.id.etName2);

        try{
            //Primero revisamos si alguien cambio de nombre
            Intent todo = getIntent();
            Bundle misExtras = todo.getExtras();
            if (misExtras != null){
                nombreA = misExtras.getString("primer_nombre");
                nombreB = misExtras.getString("segundo_nombre");
                respuesta = misExtras.getString("respuesta");
                //Logica simple
                if (nombreA != null && nombreB != null){
                    //Cargamos los nombres
                    etName1.setText(nombreA);
                    etName2.setText(nombreB);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Cambiamos de activity
    public void CambiarNombres(View vista){
        if(!etName1.getText().toString().isEmpty() && !etName2.getText().toString().isEmpty()){
            Intent cambioCompleto = new Intent(this, MainActivity.class);
            //Con esto vamos a pasar un archivo que contenga varios mensajes a otro activity
            Bundle misExtras = new Bundle();
            //Colocamos nuestros extras
            misExtras.putString("primer_nombre",etName1.getText().toString());
            misExtras.putString("segundo_nombre",etName2.getText().toString());
            misExtras.putString("mensaje","");
            misExtras.putString("respuesta",respuesta);
            //Ahora cargamos nuestro bundle con 'putExtras'
            cambioCompleto.putExtras(misExtras);
            startActivity(cambioCompleto);
        }else {
            Toast.makeText(this,"Complete los campos Por Favor",Toast.LENGTH_SHORT).show();
        }
    }
}