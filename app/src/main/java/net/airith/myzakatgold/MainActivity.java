package net.airith.myzakatgold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView linkTextView;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }

    EditText Weightgold, Valuegold;
    Button btnCalc, btnReset;
    TextView tvOutput, tvOutput2, tvOutput3;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    float weightG;
    float valueG;


    SharedPreferences sharedPref;
    SharedPreferences sharedPref2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Weightgold = (EditText) findViewById(R.id.GoldWeight);
        Valuegold = (EditText) findViewById(R.id.GoldValue);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        tvOutput = (TextView) findViewById(R.id.tvOutput);
        tvOutput2 = (TextView) findViewById(R.id.tvOutput2);
        tvOutput3 = (TextView) findViewById(R.id.tvOutput3);
        btnReset = (Button) findViewById(R.id.btnReset);

        btnCalc.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);

        sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
        weightG = sharedPref.getFloat("weight", 0.0F);

        sharedPref2 = this.getSharedPreferences("value", Context.MODE_PRIVATE);
        valueG = sharedPref2.getFloat("value", 0.0F);

        Weightgold.setText("" + weightG);
        Valuegold.setText("" + valueG);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
                //Toast.makeText(this,"This is about", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, AboutMyZakatGold.class);
                startActivity(intent);

                break;

            case R.id.instruction:

                Intent intent1 = new Intent(this, InstructionMyZakatGold.class);
                startActivity(intent1);
                break;

            }

            return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {

                case R.id.btnCalc:
                    calc();
                    break;

                case R.id.btnReset:
                    Weightgold.setText("");
                    Valuegold.setText("");
                    tvOutput.setText("");
                    tvOutput2.setText("");
                    tvOutput3.setText("");

                    break;

            }
        } catch (java.lang.NumberFormatException nfe) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();

        } catch (Exception exp) {
            Toast.makeText(this, "Unknown Exception" + exp.getMessage(), Toast.LENGTH_SHORT).show();

            Log.d("Exception", exp.getMessage());

        }
    }

    public void calc() {
        DecimalFormat df = new DecimalFormat("##.00");
        float weightG = Float.parseFloat(Weightgold.getText().toString());
        float valueG = Float.parseFloat(Valuegold.getText().toString());
        String stat = spinner.getSelectedItem().toString();
        double totalGoldvalue;
        double uruf;
        double payableZakat;
        double totalZakat;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("weight", weightG);
        editor.apply();
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putFloat("value", valueG);
        editor2.apply();


        if (stat.equals("Keep")) {
            totalGoldvalue = weightG * valueG;
            uruf = weightG - 85;

            if (uruf >= 0.0) {
                payableZakat = uruf * valueG;
                totalZakat = payableZakat * 0.025;
            } else {
                payableZakat = 0.0;
                totalZakat = payableZakat * 0.025;

            }

            tvOutput.setText("Total value of the gold: RM" + df.format(totalGoldvalue));
            tvOutput2.setText("Zakat payable: RM" + df.format(payableZakat));
            tvOutput3.setText("Total Zakat: RM" + df.format(totalZakat));
        } else {
            totalGoldvalue = weightG * valueG;
            uruf = weightG - 200;

            if (uruf >= 0.0) {
                payableZakat = uruf * valueG;
                totalZakat = payableZakat * 0.025;
            } else {
                payableZakat = 0.0;
                totalZakat = payableZakat * 0.025;

            }

            tvOutput.setText("Total value of the gold: RM" + df.format(totalGoldvalue));
            tvOutput2.setText("Zakat payable: RM" + df.format(payableZakat));
            tvOutput3.setText("Total Zakat: RM" + df.format(totalZakat));


        }

    }
}


