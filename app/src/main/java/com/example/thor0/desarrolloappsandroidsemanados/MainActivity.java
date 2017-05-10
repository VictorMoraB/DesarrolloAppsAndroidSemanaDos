package com.example.thor0.desarrolloappsandroidsemanados;

import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    //create variables to hold the interface elements
    private DatePicker dpBirthDate;
    private TextInputEditText tieUserName;
    private TextInputEditText tieUserEmail;
    private TextInputEditText tieUserPhone;
    private TextInputEditText tieUserDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initialize object references
        dpBirthDate = (DatePicker)findViewById(R.id.user_birth_picker);
        DisableCalendarViewWhenPossible();

        tieUserName = (TextInputEditText)findViewById(R.id.user_name);
        tieUserPhone = (TextInputEditText)findViewById(R.id.user_phone);
        tieUserEmail =(TextInputEditText)findViewById(R.id.user_email);
        tieUserDescription = (TextInputEditText)findViewById(R.id.user_description);

    }

    private void DisableCalendarViewWhenPossible(){
        //check wether the sdk is 11 or higher, the method did not exist before and would
        //crash the app
        int currentApiVersion = Build.VERSION.SDK_INT;
        if(currentApiVersion>= 11){
            dpBirthDate.setCalendarViewShown(false);
        }

    }

}
