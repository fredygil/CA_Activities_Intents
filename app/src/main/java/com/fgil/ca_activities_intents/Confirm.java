package com.fgil.ca_activities_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Bundle params = getIntent().getExtras();

        //Get params
        final String name = params.getString(getResources().getString(R.string.key_name));
        final String phone = params.getString(getResources().getString(R.string.key_phone));
        final String email = params.getString(getResources().getString(R.string.key_email));
        final String description = params.getString(getResources().getString(R.string.key_description));
        final int day = params.getInt(getResources().getString(R.string.key_day));
        final int month = params.getInt(getResources().getString(R.string.key_month))+1;
        final int year = params.getInt(getResources().getString(R.string.key_year));

        //Show params
        ((TextView)findViewById(R.id.confirm_name)).setText(name);
        ((TextView)findViewById(R.id.confirm_phone)).setText(phone);
        ((TextView)findViewById(R.id.confirm_email)).setText(email);
        ((TextView)findViewById(R.id.confirm_description)).setText(description);
        String birthday =   day + "/" + month + "/" + year;
        ((TextView)findViewById(R.id.confirm_birthday)).setText(birthday);


        //Click on edit button
        Button btnEdit = (Button)findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Confirm.this, MainActivity.class);

                intent.putExtra(getResources().getString(R.string.key_name), name);
                intent.putExtra(getResources().getString(R.string.key_phone), phone);
                intent.putExtra(getResources().getString(R.string.key_email), email);
                intent.putExtra(getResources().getString(R.string.key_description), description);
                intent.putExtra(getResources().getString(R.string.key_day), day);
                intent.putExtra(getResources().getString(R.string.key_month), month-1);
                intent.putExtra(getResources().getString(R.string.key_year), year);

                startActivity(intent);

            }
        });

    }
}
