package com.dharwin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity2 extends Activity {

    // Defining the object for button
    Button button1;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main2);

        // Bind the components to their respective objects
        // by assigning their IDs
        // with the help of findViewById() method
        button1 = (Button)findViewById(R.id.Button01);

        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View view)
            {

                // Creating explicit intent
                Intent i = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(i);
            }
        });
    }
}