package il.ac.jct.michaelzalman.androidproject.controller;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.backend.IDBManager;
import il.ac.jct.michaelzalman.androidproject.model.backend.TakeAndGoConsts;

public class AddClientActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        findViews();
    }
    private EditText FirstName;
    private EditText LastName;
    private EditText CreditCard;
    private EditText EmailAddress;
    private EditText PhoneNumber;
    private EditText ID;
    private Button Add;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-14 20:04:49 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        FirstName = (EditText)findViewById( R.id.FirstName );
        LastName = (EditText)findViewById( R.id.LastName );
        CreditCard = (EditText)findViewById( R.id.CreditCard );
        EmailAddress = (EditText)findViewById( R.id.EmailAddress );
        PhoneNumber = (EditText)findViewById( R.id.PhoneNumber );
        ID = (EditText)findViewById( R.id.ID );
        Add = (Button)findViewById( R.id.Add );

        Add.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-14 20:04:49 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == Add ) {
            // Handle clicks for Add
            ContentValues content = new ContentValues();
            content.put(TakeAndGoConsts.ClientConst.FIRST_NAME, FirstName.getText().toString());
            content.put(TakeAndGoConsts.ClientConst.LAST_NAME, LastName.getText().toString());
            content.put(TakeAndGoConsts.ClientConst.ID, ID.getText().toString());
            content.put(TakeAndGoConsts.ClientConst.PHONE_NUMBER, PhoneNumber.getText().toString());
            content.put(TakeAndGoConsts.ClientConst.EMAIL, EmailAddress.getText().toString());
            content.put(TakeAndGoConsts.ClientConst.CREDIT_CARD, CreditCard.getText().toString());
            try {
                new DBFactory().getIdbManager().addClient(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
