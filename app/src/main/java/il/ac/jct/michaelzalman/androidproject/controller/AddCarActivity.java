package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.*;
import il.ac.jct.michaelzalman.androidproject.model.entities.*;

public class AddCarActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout rootView;
    private Spinner CarModelSpinner;
    private EditText CarBranchId;
    private EditText Kilometers;
    private Button Add;
    private List<CarModel> carModels;
    private IDBManager manager;

    private backgroundProcess<Void,Void,List<CarModel>> bgpGetCarModels;
    ProgressDialog progressDialog;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        findViews();

        manager = DBFactory.getIdbManager();

        carModels = new ArrayList<>();

        bgpGetCarModels = new backgroundProcess<>(new backgroundProcess.backgroundProcessActions<Void,Void,List<CarModel>>() {

            @Override
            public List<CarModel> doInBackground() {
                return manager.getAllCarModels();
            }

            @Override
            public void onPostExecute(List<CarModel> aList) {
                carModels = aList;
                progressDialog.cancel();

                if(carModels == null || carModels.isEmpty()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddCarActivity.this);
                    builder1.setMessage("הוסף מודל תחילה");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "מובן",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else
                {
                    adapter.clear();
                    adapter.addAll(aList);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        adapter = new ArrayAdapter(AddCarActivity.this, R.layout.car_models_item, carModels) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return carModelView(position, convertView, parent);
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return carModelView(position, convertView, parent);
            }

            private View carModelView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(AddCarActivity.this, R.layout.car_models_item, null);
                }

                TextView textView = (TextView) convertView.findViewById(R.id.carModelItem);

                textView.setText(carModels.get(position).getModelName());

                return convertView;
            }

        };

        CarModelSpinner.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loading();

    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-20 19:15:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        rootView = (LinearLayout)findViewById( 0 );
        CarModelSpinner = (Spinner)findViewById( R.id.CarModel );
        CarBranchId = (EditText)findViewById( R.id.CarBranchId );
        Kilometers = (EditText)findViewById( R.id.Kilometers );
        Add = (Button)findViewById( R.id.Add );

        Add.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-20 19:15:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == Add ) {
            // Handle clicks for Add
            ContentValues content = new ContentValues();
            content.put(TakeAndGoConsts.CarConst.CAR_MODEL, CarModelSpinner.getSelectedItem().toString());
            content.put(TakeAndGoConsts.CarConst.CAR_BRANCH_ID, CarBranchId.getText().toString());
            content.put(TakeAndGoConsts.CarConst.KILOMETERS, Kilometers.getText().toString());

            try
            {
                DBFactory.getIdbManager().addCar(content);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private void loading()
    {
        progressDialog = ProgressDialog.show(this,"","טוען מודלי רכב",true);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"בטל",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
                finish();

            }
        });
        bgpGetCarModels.execute();
    }

}




