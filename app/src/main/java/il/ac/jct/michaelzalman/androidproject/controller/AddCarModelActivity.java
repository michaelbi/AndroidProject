package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.backend.TakeAndGoConsts;
import il.ac.jct.michaelzalman.androidproject.model.backend.Tools;
import il.ac.jct.michaelzalman.androidproject.model.entities.CarModel;

public class AddCarModelActivity extends AppCompatActivity implements View.OnClickListener{

    //-------------- Class Arguments----------------//
    private LinearLayout rootView;
    private EditText Brand;
    private EditText ModelName;
    private EditText EngineCapacity;
    private Spinner GearBox;
    private EditText SitsNumber;
    private Button Add;

    ContentValues CarModelToAdd;

    private backgroundProcess<Void,Void,Boolean> addCarModelProcess;
    private backgroundProcess.backgroundProcessActions<Void,Void,Boolean> addAction;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_model);

        findViews();

        CarModel.Gearbox gearboxlist[] = CarModel.Gearbox.values();

        GearBox.setAdapter(new ArrayAdapter<CarModel.Gearbox>(this,R.layout.gearbox_enum,gearboxlist));

        addAction = new backgroundProcess.backgroundProcessActions<Void, Void, Boolean>() {
            private String error=null;

            @Override
            public Boolean doInBackground() {
                try {

                    DBFactory.getIdbManager().addCarModel(CarModelToAdd);
                }
                catch (Exception e) {
                    error = e.getMessage();
                    return false;
                }

                return true;
            }

            @Override
            public void onPostExecute(Boolean aVoid) {

                progressDialog.dismiss();

                if(aVoid)
                {
                    Toast.makeText(AddCarModelActivity.this,"מודל הוסף בהצלחה",Toast.LENGTH_LONG).show();
                }
                else
                Toast.makeText(AddCarModelActivity.this,error,Toast.LENGTH_LONG).show();

            }
        };

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("בפעולה");
        progressDialog.setMessage("מוסיף מודל רכב");
        progressDialog.setIndeterminate(true);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "בטל", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addCarModelProcess.cancel(true);
                progressDialog.dismiss();
            }
        });
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-20 18:28:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {

        rootView = (LinearLayout)findViewById( 0 );
        Brand = (EditText)findViewById( R.id.Brand );
        ModelName = (EditText)findViewById( R.id.ModelName );
        EngineCapacity = (EditText)findViewById( R.id.EngineCapacity );
        GearBox = (Spinner) findViewById( R.id.GearBox );
        SitsNumber = (EditText)findViewById( R.id.SitsNumber );
        Add = (Button)findViewById( R.id.Add );

        Add.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-20 18:28:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == Add && !isFormEmpty() && Tools.isInternetConnectedToast(this)) {
            // Handle clicks for Add
            CarModelToAdd = new ContentValues();
            CarModelToAdd.put(TakeAndGoConsts.CarModelConst.BRAND, Brand.getText().toString());
            CarModelToAdd.put(TakeAndGoConsts.CarModelConst.MODEL_NAME, ModelName.getText().toString());
            CarModelToAdd.put(TakeAndGoConsts.CarModelConst.ENGINE_CAPACITY, EngineCapacity.getText().toString());
            CarModelToAdd.put(TakeAndGoConsts.CarModelConst.GEAR_BOX, GearBox.getSelectedItem().toString());
            CarModelToAdd.put(TakeAndGoConsts.CarModelConst.SITS_NUMBER, SitsNumber.getText().toString());
            try {
                progressDialog.show();
                addCarModelProcess = new backgroundProcess<>(addAction);
                addCarModelProcess.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isFormEmpty()
    {
        if( Brand.getText().toString().isEmpty() ||
            ModelName.getText().toString().isEmpty() ||
            EngineCapacity.getText().toString().isEmpty() ||
            GearBox.getSelectedItem().toString().isEmpty() ||
            SitsNumber.getText().toString().isEmpty())
        {
            Toast.makeText(this,getResources().getString(R.string.error_empty_filleds),Toast.LENGTH_LONG).show();
            return true;
        }

        return false;
    }

}
