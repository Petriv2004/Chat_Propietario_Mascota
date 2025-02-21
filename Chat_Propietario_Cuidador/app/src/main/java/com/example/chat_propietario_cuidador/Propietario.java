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

public class Propietario extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    public  ArrayList<String> mensajes  = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_propietario);
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
        TextView messageView = (TextView) findViewById(R.id.converP);
        messageView.setText(messageText);
    }

    public void onSendMessageP(View view){
        EditText messageView = (EditText) findViewById(R.id.messageP);
        String messageText = "Propietario: " + messageView.getText().toString();
        mensajes.add(messageText);

        Intent intent = new Intent(this, Cuidador.class);
        //intent.putExtra(Cuidador.EXTRA_MESSAGE, messageText);
        intent.putStringArrayListExtra(Cuidador.EXTRA_MESSAGE, mensajes);
        startActivity(intent);
    }
}