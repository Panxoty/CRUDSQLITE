package com.example.crudsqlite.Vista;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudsqlite.Controlador.ConexionHelper;
import com.example.crudsqlite.Controlador.Utility;
import com.example.crudsqlite.R;

public class RegisterActivity extends AppCompatActivity {
    EditText txtID,txtNombre, txtEmail;
    Button btnRegis;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtID =(EditText) findViewById(R.id.txtID);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        btnRegis = (Button) findViewById(R.id.btnRegis);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrarUsuarios();
            }
        });
    }

    private void registrarUsuarios() {
        ConexionHelper conn= new ConexionHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Utility.CAMPO_ID,txtID.getText().toString());
        contentValues.put(Utility.CAMPO_NOMBRE,txtNombre.getText().toString());
        contentValues.put(Utility.CAMPO_CORREO,txtEmail.getText().toString());

        Long idResultante = db.insert(Utility.TABLA_USUARIO,Utility.CAMPO_ID,contentValues);
        Toast.makeText(getApplicationContext(), "! ID YA REGISTRADO ¡"+idResultante, Toast.LENGTH_SHORT).show();

    }
    private void registrarUsuariosSql(){

    }
}
