package com.example.crudsqlite.Vista;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudsqlite.Controlador.ConexionHelper;
import com.example.crudsqlite.Controlador.Utility;
import com.example.crudsqlite.Modelo.Usuario;
import com.example.crudsqlite.R;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {
    ListView ListUsuario;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario>listaUsuarios;
    ConexionHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        //Configuracion listview
        ListUsuario= (ListView) findViewById(R.id.ListUsuario);
        //Inicializamos la con a la bd
        conn = new ConexionHelper(getApplicationContext(),"bd_usuarios",null,1);
        consultarListaUsuarios();

        //Creamos array adapter
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        ListUsuario.setAdapter(adaptador);

        //Configuramos el evento de click en un elemento
        ListUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion ="id: " + listaUsuarios.get(position).getId() +"\n";
                informacion+="Nombres: " + listaUsuarios.get(position).getNombre() +"\n";
                informacion+="Correo: " + listaUsuarios.get(position).getCorreo() +"\n";
                Toast.makeText(getApplicationContext(), informacion,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void consultarListaUsuarios() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();

        //La consulta se realiza mediante un cursor
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utility.TABLA_USUARIO,null);
        while(cursor.moveToNext()){
            usuario= new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setCorreo(cursor.getString(2));
            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }
    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for(int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+ " - "+ listaUsuarios.get(i).getNombre());
        }
    }
}
