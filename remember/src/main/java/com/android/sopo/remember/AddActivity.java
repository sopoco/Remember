package com.android.sopo.remember;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final DataBaseManager manager = new DataBaseManager(this);

        final TextView etNombre = (TextView) findViewById(R.id.etNombre);
        final Spinner spTipo = (Spinner) findViewById(R.id.spTipos);
        final Spinner spDia = (Spinner) findViewById(R.id.spDia);
        final TimePicker tpHora = (TimePicker) findViewById(R.id.tpHora);

        ArrayAdapter<CharSequence> adapTipos;
        ArrayAdapter<CharSequence> adapDia;
        Button bAddBD = (Button) findViewById(R.id.bAddBD);

        bAddBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.insertar(etNombre.getText().toString(), spTipo.getSelectedItem().toString(), spDia.getSelectedItem().toString(), getHora(tpHora)+":"+getMinuto(tpHora));
                Toast.makeText(getBaseContext(), spTipo.getSelectedItem().toString() + "Ingresada", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        adapDia = ArrayAdapter.createFromResource(this, R.array.dia, android.R.layout.simple_spinner_dropdown_item);
        adapTipos = ArrayAdapter.createFromResource(this, R.array.tipos, android.R.layout.simple_spinner_dropdown_item);

        spDia.setAdapter(adapDia);
        spTipo.setAdapter(adapTipos);

    }

    public void clickLimpiar(View view)
    {
        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        etNombre.setText("");
    }

    public String getHora(TimePicker hora)
    {
        String horaFinal = "";

        if(hora.getCurrentHour() < 10)
        {
            horaFinal = "0" + hora.getCurrentHour().toString();
        }

        if(hora.getCurrentHour() > 9)
        {
            horaFinal = hora.getCurrentHour().toString();
        }

        return horaFinal;
    }

    public String getMinuto(TimePicker hora)
    {
        String minutoFinal = "";

        if(hora.getCurrentMinute() < 10)
        {
            minutoFinal = "0" + hora.getCurrentMinute().toString();
        }

        if(hora.getCurrentMinute() > 9)
        {
            minutoFinal = hora.getCurrentMinute().toString();
        }

        return minutoFinal;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
