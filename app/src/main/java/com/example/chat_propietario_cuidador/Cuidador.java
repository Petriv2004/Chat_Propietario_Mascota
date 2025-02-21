package com.example.chat_propietario_cuidador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Cuidador extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";
    public  ArrayList<String> mensajes = null;

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
        Intent intent = getIntent();
        mensajes = intent.getStringArrayListExtra(EXTRA_MESSAGE);
        if (mensajes == null) {
            mensajes = new ArrayList<>();
        }
        //String messageText = intent.getStringExtra(EXTRA_MESSAGE);
        String messageText = "";
        for(String m : mensajes){
            messageText += m + "\n";
        }
        TextView messageView = (TextView) findViewById(R.id.converC);
        messageView.setText(messageText);
    }

    public void onSendMessageC(View view){
        EditText messageView = (EditText) findViewById(R.id.messageC);
        String messageText = "Cuidador: " + messageView.getText().toString();
        mensajes.add(messageText);

        Intent intent = new Intent(this, Propietario.class);
        //intent.putExtra(Propietario.EXTRA_MESSAGE, messageText);
        intent.putStringArrayListExtra(Propietario.EXTRA_MESSAGE, mensajes);
        startActivity(intent);
    }
}