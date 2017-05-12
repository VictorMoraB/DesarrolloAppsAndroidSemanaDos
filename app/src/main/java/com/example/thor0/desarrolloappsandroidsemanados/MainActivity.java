package com.example.thor0.desarrolloappsandroidsemanados;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //create variables to hold the interface elements
    private DatePicker dpBirthDate;
    private TextInputEditText tieUserName;
    private TextInputEditText tieUserEmail;
    private TextInputEditText tieUserPhone;
    private TextInputEditText tieUserDescription;
    private Button btnSubmitContactButton;

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
        btnSubmitContactButton = (Button)findViewById(R.id.form_next_button);
        //wire click event
        btnSubmitContactButton.setOnClickListener(this);

        //check if data was passed, this will happen when this activity is called
        //from the confirm activity
        Bundle parameters = getIntent().getExtras();
        if(parameters != null){
            InitializeViews(parameters);
        }

    }

    private void InitializeViews(Bundle data){

        //initialize the name
        tieUserName.setText(data.getString(
                getResources().getString(R.string.name_param)
        ));
        //initialize the birth date
        String birth = data.getString(
                getResources().getString(R.string.birth_param)
        );
        SetDatePicker(birth);

        //initialize the phone number
        tieUserPhone.setText(data.getString(
                getResources().getString(R.string.phone_param)
        ));

        //initialize the email
        tieUserEmail.setText(data.getString(
                getResources().getString(R.string.email_param)
        ));
        //intialize the description
        tieUserDescription.setText(data.getString(
                getResources().getString(R.string.description_param)
        ));
    }

    @Override
    public void onClick (View v) {
        if (v.getId() == R.id.form_next_button){
            GoToConfirmContactActivity();
        }

    }

    private void GoToConfirmContactActivity(){

        //create an intent to go to the next activity
        Intent confirmationActivityIntent = new Intent(this, confirmacion_datos.class );
        //pack information into the intent
        //pack name
        confirmationActivityIntent.putExtra(
                getResources().getString(R.string.name_param), tieUserName.getText().toString());

        //pack the birth date
        String birth = BuildDate();
        confirmationActivityIntent.putExtra(
                getResources().getString(R.string.birth_param), birth);

        //pack the phone
        confirmationActivityIntent.putExtra(
                getResources().getString(R.string.phone_param), tieUserPhone.getText().toString());

        //pack the email
        confirmationActivityIntent.putExtra(
                getResources().getString(R.string.email_param), tieUserEmail.getText().toString());

        //pack the description
        confirmationActivityIntent.putExtra(
                getResources().getString(R.string.description_param),
                tieUserDescription.getText().toString());


        //launch the activity with all the data packed
        startActivity(confirmationActivityIntent);

        //kill this activity
        finish();

    }

    private void SetDatePicker(String date){
        String[] dateParts = date.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int year = Integer.parseInt(dateParts[2]);
        int month=0;
        //obtain a reference to the days of the month array
        String[] months = getResources().getStringArray(R.array.intToMonth);
        //find the index of the received month element within the array
        for(int i=0; i<months.length; i++){
            if(months[i].equals(dateParts[1])){
                month = i;
                break;
            }
        }

        //set the instance date picker to the obtained date
        dpBirthDate.init(year, month, day, null);

    }

    private String BuildDate(){
        //read state of DatePicker view
        int year = dpBirthDate.getYear();
        int month = dpBirthDate.getMonth();
        int day = dpBirthDate.getDayOfMonth();
        //translate the month integer to a friendly text representation
        String monthAsString = GetMonthString(month);

        //concatenate all the pieces of the date day/month/year
        return day + "-" + monthAsString + "-" + year;

    }

    private String GetMonthString( int month){

        //obtain the array with the mont translation
        String[] months = getResources().getStringArray(R.array.intToMonth);
        return  months[month];

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
