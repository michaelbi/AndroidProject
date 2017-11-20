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

public class AddBranchActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);
        findViews();
    }
    private LinearLayout rootView;
    private EditText Address;
    private EditText ParkingUnits;
    private Button Add;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-20 18:24:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        rootView = (LinearLayout)findViewById( 0 );
        Address = (EditText)findViewById( R.id.Address );
        ParkingUnits = (EditText)findViewById( R.id.ParkingUnits );
        Add = (Button)findViewById( R.id.Add );

        Add.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-20 18:24:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == Add ) {
            // Handle clicks for Add
            ContentValues content = new ContentValues();
            content.put(TakeAndGoConsts.BranchConst.PARKING, ParkingUnits.getText().toString());
            content.put(TakeAndGoConsts.AddressConst.CITY, Address.getText().toString());
            try {
                new DBFactory().getIdbManager().addBranch(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
