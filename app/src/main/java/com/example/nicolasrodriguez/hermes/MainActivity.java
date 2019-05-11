package com.example.nicolasrodriguez.hermes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv1;
    private Spinner sp1;
    private Button botonConexion;

    public void Sumar(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        if (TextUtils.isEmpty(valor1) || TextUtils.isEmpty(valor2)){

            tv1.setText("Resultado");

//            Toast.makeText(this, "Falta completar los campos!", Toast.LENGTH_LONG).show();

            if (TextUtils.isEmpty(valor1)) {
                et1.setError("Falta el primer número");
            }
            else if (TextUtils.isEmpty(valor2)) {
                et2.setError("Falta el segundo número");
            }
        }
        else {

            int number1 = Integer.parseInt(valor1);
            int number2 = Integer.parseInt(valor2);

            int suma = number1 + number2;

            String result = String.valueOf(suma);
            tv1.setText(result);
        }
    }

    public void Alarmas(View view){
        Intent alarmas = new Intent(this, Alarmas.class);
        startActivity(alarmas);
    }

    public void LineasDeColectivo(View view){
        Intent lineas = new Intent(this, LineasDeColectivo.class);
        startActivity(lineas);
    }

//    public void AcercaDeNosotros(View view){
//        Intent prueba = new Intent(this, Prueba.class);
//        startActivity(prueba);
//    }

    public void Prueba(View view){
        Intent prueba = new Intent(this, Prueba.class);
        startActivity(prueba);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        et1 = (EditText)findViewById(R.id.txt_number1);
        et2 = (EditText)findViewById(R.id.txt_number2);
        tv1 = (TextView)findViewById(R.id.txt_resultado);
        sp1 = (Spinner)findViewById(R.id.spinner);

        String[] opciones = {"UNO", "DOS", "TRES"};

        ArrayAdapter<String> adapatador = new ArrayAdapter<String>(this, R.layout.spinner_item_personalizado, opciones);
        sp1.setAdapter(adapatador);

        botonConexion = findViewById(R.id.button);

        botonConexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String webService = "";
                Conexion conexion = new Conexion();

                try {
                    String respuesta = conexion.execute(webService).get();

                    JSONArray jsonArray = new JSONArray(respuesta);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);



                } catch (JSONException e) {
                        e.printStackTrace();
                } catch (InterruptedException e){
                    e.printStackTrace();
                } catch (ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de hacerse visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }
    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show();
        // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").
    }
    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        // La actividad ya no es visible (ahora está "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de ser destruida.
    }
}
