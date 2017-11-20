package il.ac.jct.michaelzalman.androidproject.model.backend;

import il.ac.jct.michaelzalman.androidproject.model.DataSource.ArrayListDB;

/**
 * Created by zalman on 14/11/2017.
 */

public class DBFactory {
    private static IDBManager idbManager = null;

    public static IDBManager getIdbManager() {
        return idbManager;
    }

    public  DBFactory()
    {
        if(idbManager == null)
            idbManager = new ArrayListDB();
    }
}
