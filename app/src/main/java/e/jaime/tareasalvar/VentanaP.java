package e.jaime.tareasalvar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class VentanaP extends AppCompatActivity {

    //Variables
    DatabaseReference fbLista;
    DatabaseReference fbProductos;
    Button agregar;
    EditText txtNombreLista;
    static String nombreLista = "";

    ArrayList<String> listas = new ArrayList<>();
    ArrayAdapter<String> ad;
    ListView lista;

    public static String getNombreLista() {
        return nombreLista;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_p);

        //Referencias Firebase
        fbLista = FirebaseDatabase.getInstance().getReference("listas");
        fbLista = fbLista.child(nombreLista);

        //Referencias locales
        txtNombreLista = (EditText) findViewById(R.id.txtNombreLista);
        agregar = (Button) findViewById(R.id.agregar);
        lista = (ListView) findViewById(R.id.lista);
        ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listas);

        //Conectar ArrayList con ArrayAdapter
        lista.setAdapter(ad);

        //Alertas
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Advertencia");
        adb.setMessage("Rellene el nombre de la lista primero");
        final AlertDialog dialog = adb.create();

        //Listener de los hijos de la referencia
        fbLista.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Imprime la key que vendría siendo el nombre de la lista
                String myChildValues = dataSnapshot.getKey();
                listas.add(myChildValues);
                ad.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ad.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                ad.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //El listener al presionar
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreLista = txtNombreLista.getText().toString().trim();
                if(TextUtils.isEmpty(nombreLista)){
                    dialog.show();
                } else {
                    startActivity(new Intent(VentanaP.this, Detalle.class));
                }
            }
        });

    }

}
