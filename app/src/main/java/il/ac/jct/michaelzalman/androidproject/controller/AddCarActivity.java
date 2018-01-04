package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.*;
import il.ac.jct.michaelzalman.androidproject.model.entities.*;

/**
 * Controller Class For Adding Car to DB
 */
public class AddCarActivity extends AppCompatActivity implements View.OnClickListener {

    //------------------Class Arguments--------------------//
    private Spinner CarModelSpinner;
    private Spinner CarBranchIdSpinner;
    private EditText Kilometers;
    private Button Add;
    private List<CarModel> carModels;
    private List<Branch> branches;
    private IDBManager manager;

    // Process to Get Car Models info
    private backgroundProcess<Void, Void, List<CarModel>> bgpGetCarModels;

    // Process to Get Branches info
    private backgroundProcess<Void, Void, List<Branch>> bgpGetBranches;
    ArrayAdapter branchesAdapter;

    // Process to Add Car To DB
    private backgroundProcess<Void, Void, Boolean> addCarProcess;
    private backgroundProcess.backgroundProcessActions<Void, Void, Boolean> addActions;
    private ContentValues carToAdd;

    // Dialogs For Activity
    ProgressDialog progressDialog;
    AlertDialog alert;
    ArrayAdapter carModeladapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        findViews();    // find views in xml layout file

        //initialize Idbmanager argument
        manager = DBFactory.getIdbManager();
        //initialize CarModel Array
        carModels = new ArrayList<>();
        //initialize Branch Array
        branches = new ArrayList<>();

        // Set Action for Adding Car Process
        addActions = new backgroundProcess.backgroundProcessActions<Void, Void, Boolean>() {
            private String error = null;

            @Override
            public Boolean doInBackground() {

                try {
                    DBFactory.getIdbManager().addCar(carToAdd);
                } catch (Exception e) {

                    error = e.getMessage();
                    return false;
                }
                return true;
            }

            @Override
            public void onPostExecute(Boolean aVoid) {

                progressDialog.dismiss();
                if (aVoid) {
                    Toast.makeText(AddCarActivity.this, "רכב הוסף בהצלחה", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(AddCarActivity.this, error, Toast.LENGTH_LONG).show();

            }
        };

        // Setting Adapter for car Model
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

        //setting Adapter For Branches
        branchesAdapter = new ArrayAdapter(this, R.layout.branch_item, branches) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return getCommonView(position, convertView, parent);
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return getCommonView(position, convertView, parent);
            }

            private View getCommonView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(AddCarActivity.this, R.layout.branch_item, null);
                }

                TextView tv = (TextView) convertView.findViewById(R.id.branch_item);

                tv.setText(String.valueOf(branches.get(position).getId()));

                return convertView;
            }
        };

        CarBranchIdSpinner.setAdapter(branchesAdapter);

        if(Tools.isInternetConnectedAlert(this)) {
            loadingCarModels();
        }

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }
//
//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//    }

    /**
     * Find the Views in the layout
     */
    private void findViews() {
        CarModelSpinner = (Spinner) findViewById(R.id.CarModel);
        CarBranchIdSpinner = (Spinner) findViewById(R.id.SpinnerCarBranchId);
        Kilometers = (EditText) findViewById(R.id.Kilometers);
        Add = (Button) findViewById(R.id.Add);

        Add.setOnClickListener(this);
    }

    /**
     * Handle button click events
     */
    @Override
    public void onClick(View v) {

        if (v == Add && !isFieldsEmpty() && Tools.isInternetConnectedToast(this)) {
            // Handle clicks for Add
            carToAdd = new ContentValues();
            carToAdd.put(TakeAndGoConsts.CarConst.CAR_MODEL, ((CarModel) CarModelSpinner.getSelectedItem()).getId());
            carToAdd.put(TakeAndGoConsts.CarConst.CAR_BRANCH_ID, ((Branch) (CarBranchIdSpinner.getSelectedItem())).getId());
            carToAdd.put(TakeAndGoConsts.CarConst.KILOMETERS, Kilometers.getText().toString());

            try {
                addCarProcess = new backgroundProcess<>(addActions);

                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("טוען מודלי רכב");
                progressDialog.setMessage("בפעולה");
                progressDialog.setIndeterminate(true);
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "בטל", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog.dismiss();
                        addCarProcess.cancel(true);
                    }
                });
                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                                && keyCode == KeyEvent.KEYCODE_BACK
                                && event.getRepeatCount() == 0) {
                            progressDialog.dismiss();
                            addCarProcess.cancel(true);
                            return true;
                        }
                        return false;
                    }
                });

                progressDialog.show();
                addCarProcess.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isFieldsEmpty() {
        if (Kilometers.getText().toString().isEmpty()) {
            Toast.makeText(this, "מלא את כל התאים", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }


    private void loadingCarModels() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("טוען מודלי רכב");
        progressDialog.setMessage("בפעולה");
        progressDialog.setIndeterminate(true);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "בטל", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
                finish();

            }
        });

        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                        && keyCode == KeyEvent.KEYCODE_BACK
                        && event.getRepeatCount() == 0) {
                    progressDialog.dismiss();
                    bgpGetCarModels.cancel(true);
                    finish();
                    return true;
                }
                return false;
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

        // creating the Process For Getting car models Info
        bgpGetCarModels = new backgroundProcess<>(new backgroundProcess.backgroundProcessActions<Void, Void, List<CarModel>>() {

            @Override
            public List<CarModel> doInBackground() {
                return manager.getAllCarModels();
            }

            @Override
            public void onPostExecute(List<CarModel> aList) {
                carModels = aList;
                progressDialog.cancel();

                if (carModels == null || carModels.isEmpty()) {
                    alert.show();
                } else {
                    carModeladapter.clear();
                    carModeladapter.addAll(aList);
                    carModeladapter.notifyDataSetChanged();
                    progressDialog.setMessage("טוען רשימת סניפים");
                    progressDialog.setMessage("בפעולה");
                    progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                                    && keyCode == KeyEvent.KEYCODE_BACK
                                    && event.getRepeatCount() == 0) {
                                progressDialog.dismiss();
                                bgpGetBranches.cancel(true);
                                finish();
                                return true;
                            }
                            return false;
                        }
                    });
                    progressDialog.show();
                    loadingBranches();
                }
            }
        });

        bgpGetCarModels.execute();
    }

    private void loadingBranches() {
        bgpGetBranches = new backgroundProcess<>(new backgroundProcess.backgroundProcessActions<Void, Void, List<Branch>>() {

            @Override
            public List<Branch> doInBackground() {
                return manager.getAllBranchs();
            }

            @Override
            public void onPostExecute(List<Branch> aList) {

                progressDialog.cancel();
                branches = aList;

                if (branches == null || branches.isEmpty()) {
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
                } else {
                    branchesAdapter.clear();
                    branchesAdapter.addAll(branches);
                    branchesAdapter.notifyDataSetChanged();
                }
            }
        });

        bgpGetBranches.execute();
    }

//    /**
//     * Check if there is Connection to the internet and give Proper message
//     */
//    private boolean isNetworkConnected() {
//        if(Tools.isInternetConnectedToast(this)) {
//            AlertDialog.Builder builder1 = new AlertDialog.Builder(AddCarActivity.this);
//            builder1.setMessage("אין חיבור נתונים\nהתחבר לרשת ונסה שוב");
//            builder1.setCancelable(true);
//            builder1.setOnKeyListener(new DialogInterface.OnKeyListener() {
//                @Override
//                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                    if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
//                            && keyCode == KeyEvent.KEYCODE_BACK
//                            && event.getRepeatCount() == 0) {
//                        finish();
//                        return true;
//                    }
//                    return false;
//
//                }
//            });
//            builder1.setPositiveButton(
//                    "מובן",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                            finish();
//                        }
//                    });
//
//            alert = builder1.create();
//            alert.show();
//            return false;
//        }
//
//        return true;
//    }

}




