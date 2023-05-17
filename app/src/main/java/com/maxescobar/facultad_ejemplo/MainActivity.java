package com.maxescobar.facultad_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etMensaje;
    private TextView tvResp, tvHablarResp, tvRespuesta;
    private String nombreA, nombreB, respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMensaje = (EditText) findViewById(R.id.etMensaje);
        tvResp = (TextView) findViewById(R.id.tvRespIndi);
        tvHablarResp = (TextView) findViewById(R.id.tvHablarIndi);
        tvRespuesta = (TextView) findViewById(R.id.tvRespuesta);

        try{
            //Primero revisamos si alguien cambio de nombre
            Intent todo = getIntent();
            Bundle misExtras = todo.getExtras();
            if(misExtras != null){
                nombreA = misExtras.getString("primer_nombre");
                nombreB = misExtras.getString("segundo_nombre");
                //Revisamos si alguien respondio
                respuesta = getIntent().getStringExtra("respuesta");

                //Logica simple
                if (nombreA != null && nombreB != null){
                    //Cargamos los nombres
                    tvHablarResp.setText("Escribe algun mensaje:" + nombreA);
                    tvResp.setText("Mensaje Respuesta:" + nombreB);
                }
                if(respuesta != null){
                    if (respuesta.isEmpty()){
                        tvRespuesta.setText(R.string.answer);
                    }else{
                        //Cargamos la respuesta
                        tvRespuesta.setText(respuesta);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Metodo boton hablar
    public void btnHablar(View vista){
        Intent hablar = new Intent(this, SegundoActivity.class);
        //Pasar valores con "putExtra"
        Bundle datos = CargarDatos();
        hablar.putExtras(datos);
        startActivity(hablar);
    }

    //Metodo boton para cambiarNombre
    public void CambiarNombre(View vista){
        Intent irCambiarNombre = new Intent(this, CambiarNombresActivity.class);
        Bundle datos = CargarDatos();
        //Colocamos nuestros extras
        irCambiarNombre.putExtras(datos);
        startActivity(irCambiarNombre);
    }

    private Bundle CargarDatos(){
        Bundle misExtras = new Bundle();
        misExtras.putString("primer_nombre",nombreA);
        misExtras.putString("segundo_nombre",nombreB);
        misExtras.putString("mensaje",etMensaje.getText().toString());
        misExtras.putString("respuesta",respuesta);
        return misExtras;
    }


}