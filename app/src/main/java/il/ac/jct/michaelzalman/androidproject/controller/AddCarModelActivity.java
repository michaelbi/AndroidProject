package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.backend.TakeAndGoConsts;

public class AddCarModelActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_model);
        findViews();
    }
    private LinearLayout rootView;
    private EditText Brand;
    private EditText ModelName;
    private EditText EngineCapacity;
    private EditText GearBox;
    private EditText SitsNumber;
    private Button Add;

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
        GearBox = (EditText)findViewById( R.id.GearBox );
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
        if ( v == Add ) {
            // Handle clicks for Add
            ContentValues content = new ContentValues();
            content.put(TakeAndGoConsts.CarModelConst.BRAND, Brand.getText().toString());
            content.put(TakeAndGoConsts.CarModelConst.MODEL_NAME, ModelName.getText().toString());
            content.put(TakeAndGoConsts.CarModelConst.ENGINE_CAPACITY, EngineCapacity.getText().toString());
            content.put(TakeAndGoConsts.CarModelConst.GEAR_BOX, GearBox.getText().toString());
            content.put(TakeAndGoConsts.CarModelConst.SITS_NUMBER, SitsNumber.getText().toString());
            try {
                DBFactory.getIdbManager().addCarModel(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
