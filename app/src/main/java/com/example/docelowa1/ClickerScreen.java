package com.example.docelowa1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class ClickerScreen extends AppCompatActivity{

    Button counting_button, reset_button, goBack;
    TextView clickerText;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.clicer);

        counting_button = (Button) findViewById(R.id.counting_button);
        reset_button = (Button) findViewById(R.id.reset_button);
        goBack = (Button)findViewById(R.id.goBack);

        clickerText = (TextView) findViewById(R.id.clickerText);

        counting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countValue = clickerText.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue ++;

                clickerText.setText(String.valueOf(intCountValue));
            }
        });

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickerText.setText(String.valueOf(0));
            }
        });

       goBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

    }
}
