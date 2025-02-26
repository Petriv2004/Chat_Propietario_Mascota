package com.example.chat_propietario_cuidador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class Cuidador extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    private ArrayList<String> mensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuidador);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cargarMensajes();

        Intent intent = getIntent();
        ArrayList<String> nuevosMensajes = intent.getStringArrayListExtra(EXTRA_MESSAGE);
        if (nuevosMensajes != null) {
            mensajes = nuevosMensajes;
            guardarMensajes();
        }
        actualizarVista();
    }

    public void onSendMessageC(View view) {
        EditText messageView = findViewById(R.id.messageC);
        String messageText = "Cuidador: " + messageView.getText().toString();
        mensajes.add(messageText);
        guardarMensajes();

        Intent intent = new Intent(this, Propietario.class);
        intent.putStringArrayListExtra(Propietario.EXTRA_MESSAGE, mensajes);
        startActivity(intent);
    }

    private void guardarMensajes() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("mensajes", String.join("###", mensajes));
        editor.apply();
    }

    private void cargarMensajes() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String mensajesGuardados = prefs.getString("mensajes", "");
        if (!mensajesGuardados.isEmpty()) {
            mensajes = new ArrayList<>(Arrays.asList(mensajesGuardados.split("###")));
        } else {
            mensajes = new ArrayList<>();
        }
    }

    private void actualizarVista() {
        TextView messageView = findViewById(R.id.converC);
        String messageText = "";
        for (String m : mensajes) {
            messageText += m + "\n";
        }
        messageView.setText(messageText);
    }
}