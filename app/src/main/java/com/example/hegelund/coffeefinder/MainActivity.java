package com.example.hegelund.coffeefinder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.startCoffeeFinderButton);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Byta activity
                Intent i = new Intent(MainActivity.this, GpsPage.class);
                startActivity(i);
                // slut byta activity
            }
        });
    }
}
