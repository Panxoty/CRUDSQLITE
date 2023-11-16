package com.example.crudsqlite.Vista;

import android.content.ContentValues;
import android.database.Cursor;
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

public class MaintenanceActivity extends AppCompatActivity {
    EditText txtEditID,txtEditNombre, txtEditEmail;
    Button btnUpdate,btnDelete,btnBuscar;

    ConexionHelper conn;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantener);

        //Cadena de conexion
        conn = new ConexionHelper(getApplicationContext(),"bd_usuarios",null,1);
        txtEditID = (EditText) findViewById(R.id.txtEditID);
        txtEditNombre = (EditText) findViewById(R.id.txtEditNombre);
        txtEditEmail = (EditText) findViewById(R.id.txtEditEmail);

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarUsuario();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();
            }
        });
    }
    private void consultar() {
        SQLiteDatabase db  = conn.getReadableDatabase();
        String[]parametros = {txtEditID.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT "+ Utility.CAMPO_NOMBRE+","+ Utility.CAMPO_CORREO +
                    " FROM " + Utility.TABLA_USUARIO + " WHERE " + Utility.CAMPO_ID + "=? ",parametros);
            cursor.moveToFirst();
            txtEditNombre.setText(cursor.getString(0));
            txtEditEmail.setText(cursor.getString(1));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Atención usuario no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }

    }
    private void actualizarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[]parametros = {txtEditID.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utility.CAMPO_NOMBRE,txtEditNombre.getText().toString());
        values.put(Utility.CAMPO_CORREO,txtEditEmail.getText().toString());

        db.update(Utility.TABLA_USUARIO,values,Utility.CAMPO_ID + "=?",parametros);
        Toast.makeText(getApplicationContext(), "Se actualizó el usuario", Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }
    private void eliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[]parametros = {txtEditID.getText().toString()};
        db.delete(Utility.TABLA_USUARIO, Utility.CAMPO_ID + "=?",parametros);
        Toast.makeText(getApplicationContext(), "Se eliminó el usuario!", Toast.LENGTH_SHORT).show();
        txtEditID.setText("");
        limpiar();
        db.close();
    }
    private void limpiar() {
        txtEditNombre.setText("");
        txtEditEmail.setText("");
    }




}
