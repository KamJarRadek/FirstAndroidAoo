package com.example.docelowa1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onConvertClic(View view){

        Intent converterScreen = new Intent(this,
                ThirdScreen.class);
        startActivity(converterScreen);

    }

    public void openConverteClic(View view){

        Intent clickerScreen = new Intent(this,
                ClickerScreen.class);
        startActivity(clickerScreen);

    }




    public void onGetNameClick(View view) {

        Intent getNameScreenIntent
         = new Intent(this,SecondScreen.class);
        final int result = 1;

        Human bob = new Human(170,70,"Marian");
        Intent sendBob = new Intent(this, SecondScreen.class);
       sendBob.putExtra("humanBob", bob);
        startActivityForResult(sendBob, result); ;


        getNameScreenIntent.putExtra("callingActivity", "MainActivity");
       startActivity(getNameScreenIntent);

       startActivityForResult( getNameScreenIntent, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView usersNameMessage = (TextView) findViewById(R.id.users_name_message);
        String nameSentBack = data.getStringExtra("UsersName");
        usersNameMessage.append(" " + nameSentBack);

    }

    public void onTranslator(View view) {
        Intent translatorScreen = new Intent(this,
                Translator.class);
        startActivity(translatorScreen);
    }

    public void onlampClick(View view) {

        Intent lampScreen = new Intent(this,
                LightScreen.class);
        startActivity(lampScreen);
    }
}
