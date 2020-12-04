package com.example.palabrasv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Act_FinPartida extends AppCompatActivity {

    private TextView mensaje;
    private Button btnVolver;
    private TextView numFallosFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__fin_partida);

        mensaje = findViewById(R.id.twMensajeFinal);
        numFallosFinal = findViewById(R.id.twNumFallos);
        btnVolver = findViewById(R.id.btnVolver);

        mensaje.setText(getIntent().getStringExtra("mensaje"));
        String fallos = getIntent().getStringExtra("fallos");
        Resources r = getResources();

        System.err.println(Integer.parseInt(fallos));

        fallos = r.getQuantityString(R.plurals.cadenaFallos, 4);

        numFallosFinal.setText(fallos);


        mostrarDialog();

    }

    public void mostrarDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Reiniciar");
        builder.setMessage("Otra partida?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

}