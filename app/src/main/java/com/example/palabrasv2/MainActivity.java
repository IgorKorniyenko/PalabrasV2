package com.example.palabrasv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Frag_Juego.ComunicacionFragAct {

    private TextView numFallos;
    private Button btnOk;
    private EditText inptLetra;
    private Frag_Juego miFragmento;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarValores();
    }

    public void inicializarValores(){
        numFallos = this.findViewById(R.id.twNumFallosMain);

        numFallos.setText(""+0);

        btnOk = this.findViewById(R.id.btnOk);
        btnOk.setText(R.string.botonOk);

        spinner = this.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = parent.getItemAtPosition(position).toString();
                Toast t = Toast.makeText(getApplicationContext(), seleccionado, Toast.LENGTH_LONG);
                t.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast t = Toast.makeText(getApplicationContext(), "nada seleccionado", Toast.LENGTH_LONG);
                t.show();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String letra = inptLetra.getText().toString();

                if(letra.length()>1 ||letra.length() == 0){
                    if(letra.length()>1) {
                        Toast t = Toast.makeText(getApplicationContext(), "Tiene que ser una letra", Toast.LENGTH_LONG);
                        t.show();
                    }else{
                        Toast t = Toast.makeText(getApplicationContext(),"No ha introducido una letra",Toast.LENGTH_LONG);
                        t.show();
                    }
                }else{
                    miFragmento.comprobarLetra(letra);
                }

                inptLetra.setText("");
            }
        });

        inptLetra = this.findViewById(R.id.inputLetra);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if(fragment instanceof Frag_Juego){
            miFragmento = (Frag_Juego) fragment;
            miFragmento.establecerManejador(this);
        }
    }

    @Override
    public void sumarFallo(int f) {
        numFallos.setText("" + f);

        if(f == 10){
            mandarMensaje(0);
        }


    }

    public void mandarMensaje(int valor){
        if(valor == 0){
            Intent i = new Intent(getApplicationContext(), Act_FinPartida.class);
            i.putExtra("mensaje","Has perdido :(");
            i.putExtra("fallos", numFallos.getText().toString());
            startActivity(i);
        }else{
            Intent i = new Intent(getApplicationContext(), Act_FinPartida.class);
            i.putExtra("mensaje","Felicidades has ganado la partida");
            i.putExtra("fallos", numFallos.getText().toString());
            startActivity(i);
        }
    }
}