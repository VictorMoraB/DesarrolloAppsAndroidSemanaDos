package com.example.thor0.desarrolloappsandroidsemanados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class confirmacion_datos extends AppCompatActivity implements View.OnClickListener {

    //create variables to hold the interface elements
    TextView tvName;
    TextView tvBirth;
    TextView tvPhone;
    TextView tvEmail;
    TextView tvDescription;
    Button btnEditButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_datos);
        //this will set the references to all text views so that the content can be updated
        InitializeViewsReferences();

        //obtain the bundle to extract the passed parameters
        Bundle parameters = getIntent().getExtras();

        //update all elements in the interface with the data passes from starting activity
        InitializeViewElements(parameters);
    }

    @Override
    public void onClick (View v) {
        if (v.getId() == R.id.confirm_edit_button){
            GoToEditActivity();
        }

    }

    private  void InitializeViewElements(Bundle data){
        //update displayed name
        tvName.setText(data.getString(
                getResources().getString(R.string.name_param)
        ));
        //update the birth date
        tvBirth.setText(data.getString(
                getResources().getString(R.string.birth_param)));

        //update the phone
        tvPhone.setText(data.getString(
                getResources().getString(R.string.phone_param)
        ));

        //upadte the email
        tvEmail.setText(data.getString(
                getResources().getString(R.string.email_param)
        ));

        //update the description
        tvDescription.setText(data.getString(
                getResources().getString(R.string.description_param)
        ));
    }

    private void GoToEditActivity(){
        Intent goToEditIntent = new Intent(this, MainActivity.class);
        //put all extras in the intent so that the edit activity can be
        //initialized

        goToEditIntent.putExtra(
                getResources().getString(R.string.name_param), tvName.getText().toString());

        //pack the birth date
        goToEditIntent.putExtra(
                getResources().getString(R.string.birth_param), tvBirth.getText().toString());
        //pack the phone
        goToEditIntent.putExtra(
                getResources().getString(R.string.phone_param), tvPhone.getText().toString());

        //pack the email
        goToEditIntent.putExtra(
                getResources().getString(R.string.email_param), tvEmail.getText().toString());

        //pack the description
        goToEditIntent.putExtra(
                getResources().getString(R.string.description_param),
                tvDescription.getText().toString());

        startActivity(goToEditIntent);

        //kill this activity
        finish();
    }


    private void InitializeViewsReferences(){

        tvName = (TextView)findViewById(R.id.confirm_name);
        tvBirth = (TextView)findViewById(R.id.confirm_birth);
        tvPhone = (TextView)findViewById(R.id.confirm_phone);
        tvEmail = (TextView)findViewById(R.id.confirm_email);
        tvDescription = (TextView)findViewById(R.id.confirm_description);
        btnEditButton = (Button)findViewById(R.id.confirm_edit_button);
        btnEditButton.setOnClickListener(this);

    }
}
