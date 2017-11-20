package il.ac.jct.michaelzalman.androidproject.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.model.backend.DBFactory;
import il.ac.jct.michaelzalman.androidproject.model.entities.Client;

public class ShowClientsActivity extends Activity {

    private ListView listViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clients);
        final List<Client> clients = new DBFactory().getIdbManager().getAllClients();

        listViewClient = (ListView) findViewById(R.id.listViewClient);

        ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this,R.layout.client_item,clients)
        {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
            {
                if(null== convertView)
                {
                    convertView = View.inflate(ShowClientsActivity.this,R.layout.client_item,null);
                }

                TextView name = (TextView) convertView.findViewById(R.id.client_item_name);
                TextView lastName = (TextView) convertView.findViewById(R.id.client_item_last_name);
                TextView id = (TextView) convertView.findViewById(R.id.client_item_id);

                name.setText(clients.get(position).getFirstName());
                lastName.setText(clients.get(position).getLastName());
                id.setText(clients.get(position).getId());

                return convertView;
            }
        };

        listViewClient.setAdapter(adapter);
    }
}
