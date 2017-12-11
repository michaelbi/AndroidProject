package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.backend.IDBManager;
import il.ac.jct.michaelzalman.androidproject.model.backend.TakeAndGoConsts;

public class AddBranchActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText AddressCity;
    private EditText AddressStreet;
    private EditText AddressNumber;
    private EditText ParkingUnits;
    private Button Add;
    private IDBManager manager=DBFactory.getIdbManager();
    private ContentValues branchToAdd;

    private backgroundProcess<Void,Void,Boolean> bgpAddBranch;
    private backgroundProcess.backgroundProcessActions<Void,Void,Boolean> bgpAction;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        findViews();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("מוסיף סניף");
        progressDialog.setTitle("בתהליך");
        progressDialog.setCancelable(true);

        bgpAction = new backgroundProcess.backgroundProcessActions<Void, Void, Boolean>() {
            @Override
            public Boolean doInBackground() {
                try {
                    manager.addBranch(branchToAdd);
                }
                catch (Exception e)
                {
                    return false;
                }

                return true;

            }

            @Override
            public void onPostExecute(Boolean aVoid) {
                progressDialog.cancel();

                if(aVoid)
                {
                    Toast.makeText(AddBranchActivity.this,"סניף הוסף",Toast.LENGTH_LONG).show();
                }
                else
                Toast.makeText(AddBranchActivity.this,"חלה שגיאה",Toast.LENGTH_LONG).show();


            }
        };


    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-20 18:24:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        AddressCity = (EditText)findViewById( R.id.Address_city );
        AddressStreet = (EditText)findViewById( R.id.Address_street );
        AddressNumber = (EditText)findViewById( R.id.Address_number );
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
            content.put(TakeAndGoConsts.AddressConst.CITY, AddressCity.getText().toString());
            content.put(TakeAndGoConsts.AddressConst.STREET, AddressStreet.getText().toString());
            content.put(TakeAndGoConsts.AddressConst.NUMBER, AddressNumber.getText().toString());

            branchToAdd=content;

            try {
                progressDialog.show();
                bgpAddBranch=new backgroundProcess<>(bgpAction);
                bgpAddBranch.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


