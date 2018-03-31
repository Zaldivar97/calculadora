package uesocc.edu.sv.pam135_2018.calculadora2018;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

//import uesocc.edu.sv.pam135_2018.calculadora2018.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView)findViewById(R.id.txtEntrada);
        txt.setText("0");
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //binding.setEntrada(entrada);
    }

    TextView txt;

    //ActivityMainBinding binding;
    public double numArriba=0;
    boolean limpiarEntrada = true;
    boolean primerNumero = true;
    public String entrada="0";
    public char operacion = 'n';

    public void agregarDigito(View v){
        char digito = v.getTag().toString().charAt(0);

        if(entrada=="0" || limpiarEntrada){
            entrada = String.valueOf(digito);
            limpiarEntrada = false;
        }else if( !(entrada.contains(".") && digito == '.') ) {
            entrada += String.valueOf(digito);
        }
        txt.setText(entrada.toString());
        System.out.println(entrada);

    }

    public void borrar(View v){
        if(entrada.length()==1){
            entrada="0";
        }else{
            entrada = entrada.substring(0,entrada.length()-1);
        }
        txt.setText(entrada);
    }

    public void reiniciar(){
        //borrar pantallas
        numArriba=0;
        entrada="0";
        operacion='n';
        txt.setText(entrada);
        limpiarEntrada = true;
        primerNumero = true;
    }

    public void btnClear(View v){
        reiniciar();
    }


    public void agregarOperacion(View v){

        if(!primerNumero) {
            
            numArriba = calcularResultado(operacion);
            entrada = String.valueOf(numArriba);
        }else{
            numArriba = Double.parseDouble(entrada);
            primerNumero = false;
        }
        operacion = v.getTag().toString().charAt(0);
        limpiarEntrada = true;
        txt.setText(entrada);
        //mostrar
    }

    private void igualar(){

        if(!primerNumero) {
            primerNumero = false;
            numArriba = calcularResultado(operacion);
            entrada = String.valueOf(numArriba);
            numArriba = 0;
            primerNumero = true;
            limpiarEntrada = true;
            System.out.println(entrada);
            txt.setText(entrada);

        }
    }

    public void igual(View v){
        igualar();
    }

    private double calcularResultado(char op){
        double resultado = 0;
        try {
            switch (op) {
                case '+':
                    resultado = numArriba + Double.parseDouble(entrada);
                    break;
                case '-':
                    resultado = numArriba - Double.parseDouble(entrada);
                    break;
                case '*':
                    resultado = numArriba * Double.parseDouble(entrada);
                    break;
                case '/':
                    if(!entrada.equals("0")) {
                        resultado = numArriba / Double.parseDouble(entrada);
                    }else{
                        Toast.makeText(this, "División entre cero", Toast.LENGTH_LONG).show();
                        reiniciar();
                    }
            }
            return resultado;
        }catch(NumberFormatException e){
            Toast.makeText(this, "Acción inválida", Toast.LENGTH_LONG).show();
            reiniciar();
            return 0;
        }


    }

}
