package com.example.docelowa1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout);

        Intent activityThatCalled = getIntent();

        Human bob = (Human) activityThatCalled.getSerializableExtra("humanBob");

        TextView callingActivityMessage = (TextView)
                findViewById(R.id.calling_activity_info_text_view);


        callingActivityMessage.append(bob.getName() + " " + bob.getHeight() + "cm"
        + bob.getWeight() + "kg");
    }

    public void onSendUsersName(View view) {

        EditText userNameET = (EditText)
                findViewById(R.id.users_name_edit_text);

        String usersName = String.valueOf(userNameET.getText());

        Intent goingBack = new Intent();

        goingBack.putExtra("UsersName", usersName);

        setResult(RESULT_OK, goingBack);

        finish();
    }
}
