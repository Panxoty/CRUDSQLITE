package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crudsqlite.Vista.ListarActivity;
import com.example.crudsqlite.Vista.MaintenanceActivity;
import com.example.crudsqlite.Vista.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    Button btnRegistrar, btnMantener, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnMantener = (Button)findViewById(R.id.btnMantener);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnMantener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MaintenanceActivity.class);
                startActivity(intent);
            }
        });
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                startActivity(intent);

            }
        });
    }
}