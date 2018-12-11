package e.jaime.tareasalvar;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Detalle extends AppCompatActivity {

    //Variables

    Detalle d = this;
    VentanaP vP = new VentanaP();
    DatabaseReference datosProductos;
    EditText txtNombreProducto2;
    EditText txtPrecio;
    EditText txtCantidad;
    Button btnAceptar2;

    private ArrayList<String> datos;
    private ArrayAdapter<String> adaptador1;
    @Override
    protected void onCreate (Bundle savedInstantsState){
        super.onCreate(savedInstantsState);
        setContentView(R.layout.detalle);

        //Se crea el padre 'productos' en firebase si no existe'
        datosProductos = FirebaseDatabase.getInstance().getReference("productos");
        //Se almacenan datos en variables
        txtNombreProducto2 = (EditText) findViewById(R.id.txtNombreProducto2);
        txtCantidad = (EditText) findViewById(R.id.txtCantidad);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);
        btnAceptar2 = (Button) findViewById(R.id.btnaceptar2);

        btnAceptar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                añadirProducto();
            }
        });

        configurarBoton();
    }

    private void añadirProducto(){
        String nombre = txtNombreProducto2.getText().toString().trim();
        String cantidad = txtCantidad.getText().toString().trim();
        String precio = txtCantidad.getText().toString().trim();

        if(!TextUtils.isEmpty(nombre)){

            //Obtenemos una key para nuestro producto
            String id = datosProductos.push().getKey();

            Producto p = new Producto(id, nombre, cantidad, precio);

            //Ahora creamos un hijo con el valor de id y le pasamos el producto
            datosProductos.child(id).setValue(p);
        } else {
            Toast.makeText(this, "Debes introducir un nombre", Toast.LENGTH_LONG).show();
        }
    }

    private void configurarBoton(){
        Button btnaceptar = (Button) findViewById(R.id.btnaceptar2);
        Button btnCerrar = (Button) findViewById(R.id.btncancelar2);
        final EditText nombreP = (EditText)  findViewById(R.id.txtNombreProducto2);
        final EditText edtitT = (EditText)  findViewById(R.id.txtPrecio);
        final EditText edtitT2 = (EditText)  findViewById(R.id.txtCantidad);
        btnaceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder alerta = new AlertDialog.Builder(d);
                String nombre = nombreP.getText().toString();
                String ed = edtitT.getText().toString();
                String ed2 = edtitT2.getText().toString();

                if (nombre.equals("") || ed.equals("") ||  ed2.equals("")){
                    alerta.setTitle("ALERTA");
                    alerta.setMessage("Debe rellenar todos los campos");
                    AlertDialog dialogo = alerta.create();
                    dialogo.show();


                }else {
                    ListView lista = (ListView) vP.findViewById(R.id.lista);
                    datos =new ArrayList<String>();
                    finish();
                }
            }
        });
        btnCerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }

    //Conecc. a Firebase

}
