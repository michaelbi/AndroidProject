package il.ac.jct.michaelzalman.androidproject.controller;

import android.os.AsyncTask;

import il.ac.jct.michaelzalman.androidproject.model.backend.IDBManager;

/**
 * Created by מיכאל on 21/11/2017.
 */

public class backgroundProcess<Param,Prog,Res> extends AsyncTask<Param,Prog,Res> {

    private backgroundProcessActions bpa;

    public interface backgroundProcessActions<P,Pr,R> {

        public R doInBackground();
        public void onPostExecute(R aVoid);

    }

    public backgroundProcess(backgroundProcessActions aBPM) {
        super();
        bpa = aBPM;
    }

    @Override
    protected Res doInBackground(Param... params) {
        return (Res) bpa.doInBackground();
    }

    @Override
    protected void onPostExecute(Res aVoid) {
        bpa.onPostExecute(aVoid);
        super.onPostExecute(aVoid);
    }
}

//public class backgroundProcess extends AsyncTask<Void, Integer, Integer> {
//
//    private backgroundProcessActions bpa;
//
//    public interface backgroundProcessActions {
//        public Integer doInBackground();
//
//        public void onProgressUpdate(Integer... values);
//
//        public void onPostExecute(Integer aVoid);
//
//    }
//
//    public backgroundProcess(backgroundProcessActions aBPM) {
//        super();
//        bpa = aBPM;
//    }
//
//    @Override
//    protected Integer doInBackground(Void... params) {
//        bpa.doInBackground();
//
//        return 1;
//    }
//
//    @Override
//    protected void onProgressUpdate(Integer... values) {
//        bpa.onProgressUpdate(values);
//    }
//
//    @Override
//    protected void onPostExecute(Integer aVoid) {
//        bpa.onPostExecute(aVoid);
//        super.onPostExecute(aVoid);
//    }
//}

