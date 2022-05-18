package com.dharwin;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button submit; EditText Statement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }
    public void addListenerOnButton(){
        submit = (Button) findViewById(R.id.button);
        Statement = (EditText) findViewById(R.id.editTextTextPersonName);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Sentence=Statement.getText().toString();
                StringBuilder result=new StringBuilder();
                int count=0;

                for(int i=0;i<Sentence.length();i++)
                {

                    switch(Sentence.charAt(i))
                    {
                        case 'a':
                        case 'e':
                        case 'i':
                        case 'o':
                        case 'u':
                        case 'A':
                        case 'E':
                        case 'I':
                        case 'O':
                        case 'U':
                            count++;
                            break;
                    }
                }
                result.append("\nTotal no of vowels: "+count);
                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }
        });



    }
}