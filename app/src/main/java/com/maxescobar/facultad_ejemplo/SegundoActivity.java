package com.maxescobar.facultad_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SegundoActivity extends AppCompatActivity {

    //Lo que se usara
    private EditText etMensaje;
    private TextView tvResp, tvHablarResp, tvRespuesta;
    private String nombreA, nombreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        etMensaje = (EditText) findViewById(R.id.etMensaje);
        tvResp = (TextView) findViewById(R.id.tvRespIndi);
        tvHablarResp = (TextView) findViewById(R.id.tvHablarIndi);
        tvRespuesta = (TextView) findViewById(R.id.tvRespuesta);

        try{
            Intent cambioCompleto = getIntent();
            Bundle misExtras = cambioCompleto.getExtras();
            if(misExtras != null){
                nombreA = misExtras.getString("primer_nombre");
                nombreB = misExtras.getString("segundo_nombre");
                //Revisamos si alguien respondio
                String respuesta = misExtras.getString("mensaje");
                //Logica simple
                if (nombreA != null && nombreB != null){
                    tvHablarResp.setText("Escribe algun mensaje:" + nombreA);
                    tvResp.setText("Mensaje Respuesta:" + nombreB);
                }
                if(!respuesta.isEmpty()){
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

    public void Responder(View vista){
        Intent responder = new Intent(this, MainActivity.class);
        //Pasar valores con "putExtra"
        Bundle misExtras = new Bundle();
        //Colocamos nuestros extras
        misExtras.putString("primer_nombre",nombreA);
        misExtras.putString("segundo_nombre",nombreB);
        misExtras.putString("mensaje","");
        misExtras.putString("respuesta",etMensaje.getText().toString());
        responder.putExtras(misExtras);
        startActivity(responder);
    }

}