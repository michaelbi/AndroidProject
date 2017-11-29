package il.ac.jct.michaelzalman.androidproject.controller;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.backend.TakeAndGoConsts;

public class AddClientActivity extends AppCompatActivity implements View.OnClickListener {

    private Thread addProcess;
    private EditText FirstName;
    private EditText LastName;
    private EditText CreditCard;
    private EditText EmailAddress;
    private EditText PhoneNumber;
    private EditText ID;
    private Button Add;

    private backgroundProcess<Void, Void, Boolean> addClientProcess;
    private backgroundProcess.backgroundProcessActions<Void, Void, Boolean> actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        findViews();

        actions = new backgroundProcess.backgroundProcessActions<Void, Void, Boolean>() {

            @Override
            public Boolean doInBackground() {
                return addClient();
            }

            @Override
            public void onPostExecute(Boolean aVoid) {

                if (aVoid)
                    Toast.makeText(AddClientActivity.this, R.string.confirm_client_add, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddClientActivity.this, R.string.error_client_add, Toast.LENGTH_LONG).show();
            }
        };

    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-14 20:04:49 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {

        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        CreditCard = (EditText) findViewById(R.id.CreditCard);
        EmailAddress = (EditText) findViewById(R.id.EmailAddress);
        PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        ID = (EditText) findViewById(R.id.ID);
        Add = (Button) findViewById(R.id.Add);

        Add.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-14 20:04:49 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == Add) {
            if (emptyBoxes()) {
                Toast.makeText(this, R.string.error_empty_filleds, Toast.LENGTH_LONG).show();
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(EmailAddress.getText().toString()).matches()) {
                Toast.makeText(this, R.string.error_email_ileagal, Toast.LENGTH_LONG).show();
            }
            else if(!Patterns.PHONE.matcher(PhoneNumber.getText().toString()).matches()) {
                Toast.makeText(this, R.string.error_phonenumber, Toast.LENGTH_LONG).show();
            }
            else if (!checkId()) {
                Toast.makeText(this, R.string.error_id, Toast.LENGTH_LONG).show();
            }
            else {
                if (addClientProcess == null || addClientProcess.getStatus() != AsyncTask.Status.RUNNING) {
                    addClientProcess = new backgroundProcess(actions);
                    addClientProcess.execute();

                } else
                    Toast.makeText(this, "Process is Running", Toast.LENGTH_LONG).show();

            }
        }
    }

    private boolean addClient() {

        // Handle clicks for Add
        ContentValues content = new ContentValues();
        content.put(TakeAndGoConsts.ClientConst.FIRST_NAME, FirstName.getText().toString());
        content.put(TakeAndGoConsts.ClientConst.LAST_NAME, LastName.getText().toString());
        content.put(TakeAndGoConsts.ClientConst.ID, ID.getText().toString());
        content.put(TakeAndGoConsts.ClientConst.PHONE_NUMBER, PhoneNumber.getText().toString());
        content.put(TakeAndGoConsts.ClientConst.EMAIL, EmailAddress.getText().toString());
        content.put(TakeAndGoConsts.ClientConst.CREDIT_CARD, CreditCard.getText().toString());

        try {

            DBFactory.getIdbManager().addClient(content);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean checkId() {
        int sum = 0;
        int id = Integer.parseInt(ID.getText().toString());

        if (id >= 1000000000)
            return false;

        for (int i = 1; id > 0; i = (i % 2) + 1) {
            int digit = (id % 10) * i;
            sum += digit / 10 + digit % 10;

            id=id/10;
        }

        if (sum % 10 != 0)
            return false;

        return true;
    }

    private boolean emptyBoxes() {
        return (FirstName.getText().toString().isEmpty() || LastName.getText().toString().isEmpty() ||
                ID.getText().toString().isEmpty() || PhoneNumber.getText().toString().isEmpty() ||
                EmailAddress.getText().toString().isEmpty() || CreditCard.getText().toString().isEmpty());
    }
}