package il.ac.jct.michaelzalman.androidproject.model.backend;

import il.ac.jct.michaelzalman.androidproject.model.DataSource.ArrayListDB;

/**
 * Created by מיכאל on 14/11/2017.
 */

class DBManagerFactory {
    private static final IDBManager ourInstance = new ArrayListDB();

    static IDBManager getInstance() {
        return ourInstance;
    }

    private DBManagerFactory() {
    }
}
