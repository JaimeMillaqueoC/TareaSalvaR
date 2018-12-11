package e.jaime.tareasalvar;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Detalle extends AppCompatActivity {
    Detalle d = this;
    VentanaP vP = new VentanaP();
    private ArrayList<String> datos;
    private ArrayAdapter<String> adaptador1;
    @Override
    protected void onCreate (Bundle savedInstantsState){
        super.onCreate(savedInstantsState);
        setContentView(R.layout.detalle);

        configurarBoton();
    }
    private void configurarBoton(){
        Button btnaceptar = (Button) findViewById(R.id.btnaceptar2);
        Button btnCerrar = (Button) findViewById(R.id.btncancelar2);
        final EditText nombreP = (EditText)  findViewById(R.id.txtNombreProducto2);
        final EditText edtitT = (EditText)  findViewById(R.id.editText);
        final EditText edtitT2 = (EditText)  findViewById(R.id.editText2);
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
}
