package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;

import java.util.List;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.entities.Client;

public class ShowClientsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clients);
        List<Client> clients = new DBFactory().getIdbManager().getAllClients();

    }
}
