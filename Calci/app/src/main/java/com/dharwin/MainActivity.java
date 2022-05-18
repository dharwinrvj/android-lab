package com.dharwin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText numa=findViewById(R.id.num1);
        final EditText numb=findViewById(R.id.num2);
        final TextView te=findViewById(R.id.textView);
        Button b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1=Integer.parseInt(numa.getText().toString());
                int n2=Integer.parseInt(numb.getText().toString());
                te.setText(Integer.toString(n1+n2));
            }
        });
        Button b2=findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1=Integer.parseInt(numa.getText().toString());
                int n2=Integer.parseInt(numb.getText().toString());
                te.setText(Integer.toString(n1-n2));
            }
        });
        Button b3=findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1=Integer.parseInt(numa.getText().toString());
                int n2=Integer.parseInt(numb.getText().toString());
                te.setText(Integer.toString(n1*n2));
            }
        });
    }
}

