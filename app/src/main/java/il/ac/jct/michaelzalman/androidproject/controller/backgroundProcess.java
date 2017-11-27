package il.ac.jct.michaelzalman.androidproject.controller;

import android.os.AsyncTask;

import il.ac.jct.michaelzalman.androidproject.model.backend.IDBManager;

/**
 * Created by מיכאל on 21/11/2017.
 */

public class backgroundProcess extends AsyncTask<Void,Integer,Void> {

    private backgroundProcessActions bpa;

    public interface backgroundProcessActions
    {
        public void doInBackground();
        public void onProgressUpdate(Integer... values);

    }

    public backgroundProcess(backgroundProcessActions aBPM)
    {
        super();
        bpa = aBPM;
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        bpa.doInBackground();

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);

        bpa.onProgressUpdate(values);
    }
}
