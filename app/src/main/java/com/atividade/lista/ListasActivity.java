package com.atividade.lista;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ListasActivity extends AppCompatActivity {

    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = new ArrayList<>();

        final ListView listaView = findViewById(R.id.listaFotos);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listaView.setOnItemLongClickListener(this.excluirItem());

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                String url = "https://jsonplaceholder.typicode.com/photos";
                Request request = new Request.Builder().url(url).build();

                try {
                    Response response = client.newCall(request).execute();
                    String json = response.body().string();
                    JSONArray jsonArray = new JSONArray(json);

                    for (int i = 0; i < 10; i++) {
                        lista.add("Título: " + jsonArray.getJSONObject(i).getString("title") + "\n" +
                                  "Url: " + jsonArray.getJSONObject(i).getString("url"));
                    }

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            listaView.setAdapter(adapter);
                        }
                    });

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        final Button btnAdd = findViewById(R.id.btnAdicionar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivityForResult(new Intent(getBaseContext(), AddListasActivity.class), 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            lista.add("Título: " + data.getStringExtra("title") + "\n" +
                      "Url: " + data.getStringExtra("url"));
            adapter.notifyDataSetChanged();
        }
    }

    public AdapterView.OnItemLongClickListener excluirItem() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                lista.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }
        };
    }
}
