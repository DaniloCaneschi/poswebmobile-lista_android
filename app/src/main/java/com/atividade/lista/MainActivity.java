package com.atividade.lista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final Button btnLista = findViewById(R.id.btnLista);

    btnLista.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view){
        startActivity(new Intent(getBaseContext(), ListasActivity.class));
      }
    });
  }
}