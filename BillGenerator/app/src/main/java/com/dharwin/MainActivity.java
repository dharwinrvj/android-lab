package com.dharwin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    Button submit;
    CheckBox chips, cakes, cookies, burgers;
    RadioButton modes;
    RadioGroup modepay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        submit = (Button) findViewById(R.id.b1);
        modepay = (RadioGroup) findViewById(R.id.mode);
        chips = (CheckBox) findViewById(R.id.chips1);
        cakes = (CheckBox) findViewById(R.id.cake1);
        cookies = (CheckBox) findViewById(R.id.cookie1);
        burgers = (CheckBox) findViewById(R.id.burger1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = modepay.getCheckedRadioButtonId();
                modes = (RadioButton) findViewById(selectedId);
                int total = 0;
                StringBuilder result=new StringBuilder();
                if (chips.isChecked()) {
                    total += 30;
                }
                if (cakes.isChecked()) {
                    total += 50;
                }
                if (cookies.isChecked()) {
                    total += 100;
                }
                if (chips.isChecked()) {
                    total += 150;
                }
                result.append("\nTotal: "+total+"Rs");
                result.append("\nyour mode of payment is:"+modes.getText());
                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
