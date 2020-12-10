package com.atividade.lista;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class AddListasActivity extends AppCompatActivity {

  @RequiresApi(api = Build.VERSION_CODES.R)
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adicionar_lista);

    final TextView edtTitulo = findViewById(R.id.edtTitulo);
    final TextView edtUrl = findViewById(R.id.edtUrl);

    final Button btnAdd = findViewById(R.id.btnAdicionar);

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view){
        Intent foto = new Intent();
        foto.putExtra("title", edtTitulo.getText().toString());
        foto.putExtra("url", edtUrl.getText().toString());
        setResult(RESULT_OK, foto);

        finish();
      }
    });
  }
}
