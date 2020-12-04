package com.example.palabrasv2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Frag_Juego extends Fragment {


    private TextView palabraAadivinar;
    private String palabra = "CAPITAL";
    private int contFallos=0;
    private int contAciertos=0;

    public Frag_Juego() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miFrag =  inflater.inflate(R.layout.fragment_frag__juego, container, false);

        inicializarFragmento(miFrag);

        return miFrag;
    }

    public void inicializarFragmento(View miFrag){
        palabraAadivinar = miFrag.findViewById(R.id.twPalabraFrag);

        String palabraIncognita="";

        for(int i=0; i<palabra.length(); i++){
            palabraIncognita = palabraIncognita + "*";
        }

        palabraAadivinar.setText(palabraIncognita);
    }

    public void comprobarLetra(String letra){
        int posicion = palabra.indexOf(letra.toUpperCase());

        if(posicion != -1){
            char[] palabraNueva = palabraAadivinar.getText().toString().toCharArray();

            palabraNueva[posicion] = palabra.toCharArray()[posicion];

            palabraAadivinar.setText(new String(palabraNueva));

            char[] palModificada = palabra.toCharArray();
            palModificada[posicion] = 'º';

            palabra =new String(palModificada);

            contAciertos++;

            if(contAciertos == palabra.length()){
                manejador.mandarMensaje(1);
            }

        }else{
            contFallos++;

            manejador.sumarFallo(contFallos);
        }
    }

    public interface ComunicacionFragAct{
        public void sumarFallo(int f);
        public void mandarMensaje(int v);

    }

    ComunicacionFragAct manejador;

    public void establecerManejador(ComunicacionFragAct elManejador){
        manejador = elManejador;
    }

    public void reiniciar(){
        palabra = "CAPITAL";

        String palabraIncognita="";

        for(int i=0; i<palabra.length(); i++){
            palabraIncognita = palabraIncognita + "*";
        }
        palabraAadivinar.setText(palabraIncognita);

        contAciertos = 0;
        contFallos = 0;
    }
}