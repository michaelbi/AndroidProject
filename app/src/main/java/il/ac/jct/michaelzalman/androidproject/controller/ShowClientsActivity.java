package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.backend.IDBManager;
import il.ac.jct.michaelzalman.androidproject.model.backend.Tools;
import il.ac.jct.michaelzalman.androidproject.model.entities.Address;
import il.ac.jct.michaelzalman.androidproject.model.entities.Branch;
import il.ac.jct.michaelzalman.androidproject.model.entities.Car;
import il.ac.jct.michaelzalman.androidproject.model.entities.CarModel;
import il.ac.jct.michaelzalman.androidproject.model.entities.Client;

public class ShowClientsActivity extends AppCompatActivity implements View.OnClickListener {

    private IDBManager manager = DBFactory.getIdbManager();

    private RadioButton showClients;
    private RadioButton showBranches;
    private RadioButton showCarmodels;
    private RadioButton showCars;
    private ListView listViewClient;

    private ArrayAdapter<Client> clientAdapter;
    private ArrayAdapter<Branch> branchAdapter;
    private ArrayAdapter<CarModel> carModelAdapter;
    private ArrayAdapter<Car> carAdapter;

    private backgroundProcess<Void,Void,Void> getInfoBGP;
    private backgroundProcess.backgroundProcessActions<Void,Void,Void> action;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clients);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("טוען");
        progressDialog.setTitle("בטעינה");
        progressDialog.setCancelable(true);

        findViews();

        if(Tools.isInternetConnectedAlert(this)) {
            loading();
        }


    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-12-11 12:34:39 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        showClients = (RadioButton)findViewById( R.id.show_clients );
        showBranches = (RadioButton)findViewById( R.id.show_branches );
        showCarmodels = (RadioButton)findViewById( R.id.show_carmodels );
        showCars = (RadioButton)findViewById( R.id.show_cars );
        listViewClient = (ListView)findViewById( R.id.listViewClient );

        showClients.setOnClickListener( this );
        showBranches.setOnClickListener( this );
        showCarmodels.setOnClickListener( this );
        showCars.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-12-11 12:34:39 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == showClients ) {
            // Handle clicks for showClients
            listViewClient.setAdapter(clientAdapter);
        } else if ( v == showBranches ) {
            listViewClient.setAdapter(branchAdapter);
            // Handle clicks for showBranches
        } else if ( v == showCarmodels ) {
            listViewClient.setAdapter(carModelAdapter);
            // Handle clicks for showCarmodels
        } else if ( v == showCars ) {
            listViewClient.setAdapter(carAdapter);
            // Handle clicks for showCars
        }
    }

    private void loading()
    {

            action = new backgroundProcess.backgroundProcessActions<Void, Void, Void>() {

                private ArrayList<Client> clientArrayList = new ArrayList<>();
                private ArrayList<Branch> branchArrayList = new ArrayList<>();
                private ArrayList<CarModel> carModelArrayList = new ArrayList<>();
                private ArrayList<Car> carArrayList = new ArrayList<>();

                @Override
                public Void doInBackground() {

                    clientArrayList.addAll(manager.getAllClients());
                    branchArrayList.addAll(manager.getAllBranchs());
                    carModelArrayList.addAll(manager.getAllCarModels());
                    carArrayList.addAll(manager.getAllCars());

                    return null;

                }

                @Override
                public void onPostExecute(Void aVoid) {

                    clientAdapter = new ArrayAdapter<Client>(ShowClientsActivity.this, R.layout.client_item, clientArrayList) {
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            if (null == convertView) {
                                convertView = View.inflate(ShowClientsActivity.this, R.layout.client_item, null);
                            }

                            TextView name = (TextView) convertView.findViewById(R.id.client_item_name);
                            TextView lastName = (TextView) convertView.findViewById(R.id.client_item_last_name);
                            TextView id = (TextView) convertView.findViewById(R.id.client_item_id);

                            name.setText(clientArrayList.get(position).getFirstName());
                            lastName.setText(clientArrayList.get(position).getLastName());
                            id.setText(clientArrayList.get(position).getId());

                            return convertView;
                        }
                    };

                    branchAdapter = new ArrayAdapter<Branch>(ShowClientsActivity.this, R.layout.branch_show_item, branchArrayList) {
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                            if (null == convertView)
                            {
                                convertView = View.inflate(ShowClientsActivity.this, R.layout.branch_show_item, null);
                            }

                            TextView id = (TextView) convertView.findViewById(R.id.branch_id_item);
                            TextView address = (TextView) convertView.findViewById(R.id.branch_show_item_address);
                            TextView parkingUnits = (TextView) convertView.findViewById(R.id.branch_show_item_parking);

                            id.setText(""+branchArrayList.get(position).getId());
                            address.setText(branchArrayList.get(position).getAddress().getCity()+" "+
                                            branchArrayList.get(position).getAddress().getStreet()+" "+
                                            branchArrayList.get(position).getAddress().getNumber());
                            parkingUnits.setText(""+branchArrayList.get(position).getParkingUnits());

                            return convertView;
                        }
                    };

                    carModelAdapter = new ArrayAdapter<CarModel>(ShowClientsActivity.this, R.layout.carmodel_show_item, carModelArrayList) {
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            if (null == convertView) {
                                convertView = View.inflate(ShowClientsActivity.this, R.layout.carmodel_show_item, null);
                            }

                            if(!carModelArrayList.isEmpty()) {
                                TextView id = (TextView) convertView.findViewById(R.id.car_model_id);
                                TextView brand = (TextView) convertView.findViewById(R.id.car_model_brand);
                                TextView name = (TextView) convertView.findViewById(R.id.car_model_name);

                                id.setText(carModelArrayList.get(position).getId());
                                brand.setText(carModelArrayList.get(position).getBrand());
                                name.setText(carModelArrayList.get(position).getModelName());
                            }

                            return convertView;
                        }
                    };

                    carAdapter = new ArrayAdapter<Car>(ShowClientsActivity.this, R.layout.car_show_item, carArrayList) {
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            if (null == convertView) {
                                convertView = View.inflate(ShowClientsActivity.this, R.layout.car_show_item, null);
                            }

                            TextView id = (TextView) convertView.findViewById(R.id.car_id);
                            TextView model = (TextView) convertView.findViewById(R.id.car_model);
                            TextView kilometers = (TextView) convertView.findViewById(R.id.car_kilimeters);

                            id.setText(carArrayList.get(position).getId());
                            CarModel modelObj = new CarModel();
                            Iterator<CarModel> it = carModelArrayList.iterator();
                            while (it.hasNext())
                            {
                                CarModel carModel = it.next();
                                boolean bool=carModel.getId().compareTo(carArrayList.get(position).getCarModel())==0;
                                if(bool)
                                {
                                    modelObj = carModel;
                                    break;
                                }
                            }
                            model.setText(modelObj.getBrand()+" "+ modelObj.getModelName());
                            kilometers.setText(""+carArrayList.get(position).getKilometers());

                            return convertView;
                        }
                    };

                    progressDialog.dismiss();
                    listViewClient.setAdapter(clientAdapter);

                }
            };

            progressDialog.show();
            getInfoBGP = new backgroundProcess<>(action);
            getInfoBGP.execute();

    }


}
