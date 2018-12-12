package e.jaime.tareasalvar;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Detalle extends AppCompatActivity {

    //Variables
    AlertDialog.Builder alerta;
    AlertDialog dialogo;
    Detalle d = this;
    DatabaseReference fbProductos;
    DatabaseReference fbLista;
    String nombreLista;
    EditText txtNombreLista;
    EditText txtNombreProducto2;
    EditText txtPrecio;
    EditText txtCantidad;
    Button btnAceptar2;
    Button btnCerrar;

    @Override
    protected void onCreate (Bundle savedInstantsState){
        super.onCreate(savedInstantsState);
        setContentView(R.layout.detalle);

        //Referenciar Variables
        txtNombreLista = (EditText) findViewById(R.id.txtNombreLista);
        btnAceptar2 = (Button) findViewById(R.id.btnaceptar2);
        btnCerrar = (Button) findViewById(R.id.btncancelar2);
        txtNombreProducto2 = (EditText)  findViewById(R.id.txtNombreProducto2);
        txtPrecio = (EditText)  findViewById(R.id.txtPrecio);
        txtCantidad = (EditText)  findViewById(R.id.txtCantidad);
        nombreLista = VentanaP.getNombreLista();

        //Alerta
        alerta = new AlertDialog.Builder(d);
        alerta.setTitle("ALERTA");
        alerta.setMessage("Debe rellenar todos los campos");
        dialogo = alerta.create();

        //Creamos las referencias de Firebase
        fbLista = FirebaseDatabase.getInstance().getReference("listas"); //Firebase arma una referencia a un 'listas'
        fbProductos = fbLista.child(nombreLista+"/productos"); //Esta referencia es el hijo de 'listas'

        //Configuramos botones aceptar | cancelar.
        configurarBoton();
    }

    private void añadirProducto(String nombre, String cantidad, String precio){
        //Obtenemos una key única para nuestro producto
        String id = fbProductos.push().getKey();

        Producto p = new Producto(id, nombre, cantidad, precio);

        //Ahora creamos un hijo con el valor de id y le pasamos el producto
        fbProductos.child(id).setValue(p);
    }

    private void configurarBoton(){
        //Configurar botón aceptar
        btnAceptar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String nombre = txtNombreProducto2.getText().toString().trim();
                String precio = txtPrecio.getText().toString().trim();
                String cantidad = txtCantidad.getText().toString().trim();

                //Handler en caso de que no se rellenen los campos
                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(precio) || TextUtils.isEmpty(cantidad)){
                    dialogo.show();
                }else {
                    añadirProducto(nombre, precio, cantidad);
                    finish();
                }
            }
        });
        //Configurar botón cancelar
        btnCerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

}
