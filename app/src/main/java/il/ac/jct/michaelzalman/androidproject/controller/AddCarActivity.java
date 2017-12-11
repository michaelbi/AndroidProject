package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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

/**
 * Controller Class For Adding Car to DB
 */
public class AddCarActivity extends AppCompatActivity implements View.OnClickListener{

    //------------------Class Arguments--------------------//
    private LinearLayout rootView;
    private Spinner CarModelSpinner;
    private Spinner CarBranchIdSpinner;
    private EditText Kilometers;
    private Button Add;
    private List<CarModel> carModels;
    private List<Branch> branches;
    private IDBManager manager;

    private backgroundProcess<Void,Void,List<CarModel>> bgpGetCarModels;
    ProgressDialog progressDialog;
    AlertDialog alert;
    ArrayAdapter carModeladapter;

    private backgroundProcess<Void,Void,List<Branch>> bgpGetBranches;
    ArrayAdapter branchesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        findViews();    // find views in xml layout file

        //initilize Idbmanager argument
        manager = DBFactory.getIdbManager();

        carModels = new ArrayList<>();

        branches = new ArrayList<>();

        carModeladapter = new ArrayAdapter(AddCarActivity.this, R.layout.car_models_item, carModels) {
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

        CarModelSpinner.setAdapter(carModeladapter);

        branchesAdapter = new ArrayAdapter(this,R.layout.branch_item,branches){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return getCommonView(position, convertView, parent);
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return getCommonView(position, convertView, parent);
            }

            private View getCommonView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
            {
                if(convertView == null)
                {
                    convertView = View.inflate(AddCarActivity.this,R.layout.branch_item,null);
                }

                TextView tv = (TextView) convertView.findViewById(R.id.branch_item);

                tv.setText(String.valueOf(branches.get(position).getId()));

                return convertView;
            }
        };

        CarBranchIdSpinner.setAdapter(branchesAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        loadingCarModels();
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
        CarBranchIdSpinner = (Spinner)findViewById( R.id.SpinnerCarBranchId );
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
            content.put(TakeAndGoConsts.CarConst.CAR_MODEL, ((CarModel)CarModelSpinner.getSelectedItem()).getId());
            content.put(TakeAndGoConsts.CarConst.CAR_BRANCH_ID, ((Branch)(CarBranchIdSpinner.getSelectedItem())).getId());
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


    private void loadingCarModels()
    {
        progressDialog = ProgressDialog.show(this,"","טוען מודלי רכב",true);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"בטל",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
                finish();

            }
        });

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

        alert = builder1.create();

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
                    alert.show();
                }
                else
                {
                    carModeladapter.clear();
                    carModeladapter.addAll(aList);
                    carModeladapter.notifyDataSetChanged();
                    progressDialog = ProgressDialog.show(AddCarActivity.this,"","טוען רשימת סניפים",true);
                    progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"בטל",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.dismiss();
                            finish();

                        }
                    });
                    progressDialog.show();
                    loadingBranches();
                }
            }
        });

        bgpGetCarModels.execute();
    }

    private void loadingBranches()
    {
        bgpGetBranches = new backgroundProcess<>(new backgroundProcess.backgroundProcessActions<Void,Void,List<Branch>>() {

            @Override
            public List<Branch> doInBackground() {
                return manager.getAllBranchs();
            }

            @Override
            public void onPostExecute(List<Branch> aList) {

                progressDialog.cancel();
                branches = aList;

                if(branches==null || branches.isEmpty())
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddCarActivity.this);
                    builder1.setMessage("הוסף סניפים תחילה");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "מובן",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    finish();
                                }
                            });

                    alert = builder1.create();
                    alert.show();
                }
                else
                {
                    branchesAdapter.clear();
                    branchesAdapter.addAll(branches);
                    branchesAdapter.notifyDataSetChanged();
                }
            }
        });

        bgpGetBranches.execute();
    }

}




