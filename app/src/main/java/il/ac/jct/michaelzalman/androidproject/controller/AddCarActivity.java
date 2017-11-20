package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.backend.TakeAndGoConsts;

public class AddCarActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        findViews();
    }
    private LinearLayout rootView;
    private EditText CarModel;
    private EditText CarBranchId;
    private EditText Kilometers;
    private Button Add;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-20 19:15:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        rootView = (LinearLayout)findViewById( 0 );
        CarModel = (EditText)findViewById( R.id.CarModel );
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
            content.put(TakeAndGoConsts.CarConst.CAR_MODEL, CarModel.getText().toString());
            content.put(TakeAndGoConsts.CarConst.CAR_BRANCH_ID, CarBranchId.getText().toString());
            content.put(TakeAndGoConsts.CarConst.KILOMETERS, Kilometers.getText().toString());
            try {
                new DBFactory().getIdbManager().addCar(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}




