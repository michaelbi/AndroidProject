package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.backend.IDBManager;
import il.ac.jct.michaelzalman.androidproject.model.entities.Car;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private IDBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        dbManager= DBFactory.getIdbManager();

    }
    private Button AddClientButton;
    private Button AddCarModelButton;
    private Button AddCarButton;
    private Button AddBranch;
    private Button GetAllListsButton;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-14 18:56:24 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        AddClientButton = (Button)findViewById( R.id.AddClientButton );
        AddCarModelButton = (Button)findViewById( R.id.AddCarModelButton );
        AddCarButton = (Button)findViewById( R.id.AddCarButton );
        AddBranch = (Button)findViewById( R.id.AddBranchButton );
        GetAllListsButton = (Button)findViewById( R.id.ShowClientsButton);

        AddClientButton.setOnClickListener( this );
        AddCarModelButton.setOnClickListener( this );
        AddCarButton.setOnClickListener( this );
        AddBranch.setOnClickListener( this );
        GetAllListsButton.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-14 18:56:24 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == AddClientButton ) {
            addClient();
        } else if ( v == AddCarModelButton ) {
            addCarModel();
        } else if ( v == AddCarButton ) {
            addCar();
        } else if ( v == AddBranch ) {
            addBranch();
        } else if ( v == GetAllListsButton ) {
            showClients();



        }

    }



    private void addClient() {
        Intent intent = new Intent(this,AddClientActivity.class);
        startActivity(intent); }
    private void addCarModel() {
        Intent intent = new Intent(this,AddCarModelActivity.class);
        startActivity(intent); }
    private void addCar() {
        Intent intent = new Intent(this,AddCarActivity.class);
        startActivity(intent); }
    private void addBranch() {
        Intent intent = new Intent(this,AddBranchActivity.class);
        startActivity(intent); }
    private void showClients(){
        Intent intent= new Intent(this,ShowClientsActivity.class);
        startActivity(intent);
    }
}
