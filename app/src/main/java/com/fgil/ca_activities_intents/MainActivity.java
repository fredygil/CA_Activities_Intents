package com.fgil.ca_activities_intents;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.DialogFragment;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static int day=0, month=0, year=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get params from bundle
        Bundle params = getIntent().getExtras();
        if (params != null && !params.isEmpty()) {
            //Get params
            final String name = getIntent().hasExtra(getResources().getString(R.string.key_name)) ? params.getString(getResources().getString(R.string.key_name)) : "";
            final String phone = getIntent().hasExtra(getResources().getString(R.string.key_phone)) ? params.getString(getResources().getString(R.string.key_phone)) : "";
            final String email = getIntent().hasExtra(getResources().getString(R.string.key_email)) ? params.getString(getResources().getString(R.string.key_email)) : "";
            final String description = getIntent().hasExtra(getResources().getString(R.string.key_description)) ? params.getString(getResources().getString(R.string.key_description)) : "";
            int iday=0, imonth=0, iyear=0;
            if (getIntent().hasExtra(getResources().getString(R.string.key_day))) {
                iday = params.getInt(getResources().getString(R.string.key_day));
                imonth = params.getInt(getResources().getString(R.string.key_month));
                iyear = params.getInt(getResources().getString(R.string.key_year));
            }

            //Show and set params
            ((TextView)findViewById(R.id.formName)).setText(name);
            ((TextView)findViewById(R.id.formPhone)).setText(phone);
            ((TextView)findViewById(R.id.formEmail)).setText(email);
            ((TextView)findViewById(R.id.formDescription)).setText(description);
            if (iday != 0){
                MainActivity.day = iday;
                MainActivity.month = imonth;
                MainActivity.year = iyear;
            }
        }



        // Get the widgets reference from XML layout
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        Button btn = (Button) findViewById(R.id.btnDatePicker);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize a new date picker dialog fragment
                DialogFragment dFragment = new DatePickerFragment();

                // Show the date picker dialog fragment
                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        //Button Next
        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent to Confirm Activity
                Intent intent = new Intent(MainActivity.this, Confirm.class);
                intent.putExtra(getResources().getString(R.string.key_name), ((EditText)findViewById(R.id.formName)).getText().toString());
                intent.putExtra(getResources().getString(R.string.key_phone), ((EditText)findViewById(R.id.formPhone)).getText().toString());
                intent.putExtra(getResources().getString(R.string.key_email), ((EditText)findViewById(R.id.formEmail)).getText().toString());
                intent.putExtra(getResources().getString(R.string.key_description), ((EditText)findViewById(R.id.formDescription)).getText().toString());
                intent.putExtra(getResources().getString(R.string.key_day), MainActivity.day);
                intent.putExtra(getResources().getString(R.string.key_month), MainActivity.month);
                intent.putExtra(getResources().getString(R.string.key_year), MainActivity.year);

                startActivity(intent);
            }
        });
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = MainActivity.year != 0 ? MainActivity.year : calendar.get(Calendar.YEAR);
            int month = MainActivity.month != 0 ? MainActivity.month : calendar.get(Calendar.MONTH);
            int day = MainActivity.day != 0 ? MainActivity.day : calendar.get(Calendar.DAY_OF_MONTH);
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

            // Create a TextView programmatically.
            TextView tv = new TextView(getActivity());

            // Create a TextView programmatically
            LayoutParams lp = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, // Width of TextView
                    LayoutParams.WRAP_CONTENT); // Height of TextView
            tv.setLayoutParams(lp);
            tv.setPadding(10, 10, 10, 10);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            //tv.setText("This is a custom title.");
            tv.setTextColor(Color.parseColor("#ff0000"));
            tv.setBackgroundColor(Color.parseColor("#FFD2DAA7"));

            // Set the newly created TextView as a custom tile of DatePickerDialog
            //dpd.setCustomTitle(tv);

            // Or you can simply set a tile for DatePickerDialog
            /*
                setTitle(CharSequence title)
                    Set the title text for this dialog's window.
            */
            dpd.setTitle(getResources().getString(R.string.form_birthday));
            // Uncomment this line to activate it

            // Return the DatePickerDialog
            return  dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            // Do something with the chosen date
            MainActivity.day = day;
            MainActivity.month = month;
            MainActivity.year = year;
        }
    }
}