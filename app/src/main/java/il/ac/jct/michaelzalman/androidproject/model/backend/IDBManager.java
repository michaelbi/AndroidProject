package il.ac.jct.michaelzalman.androidproject.model.backend;

import android.content.ContentProvider;
import android.content.ContentValues;

import java.util.List;

import il.ac.jct.michaelzalman.androidproject.model.entities.Branch;
import il.ac.jct.michaelzalman.androidproject.model.entities.Car;
import il.ac.jct.michaelzalman.androidproject.model.entities.CarModel;
import il.ac.jct.michaelzalman.androidproject.model.entities.Client;

/**
 * IBackent to handle data sources
 */

public interface IDBManager {
    boolean isClientExist(ContentValues client);
    void addClient(ContentValues client) throws Exception;
    void addCarModel (ContentValues carModel) throws Exception;
    void addCar(ContentValues car) throws Exception;
    void addBranch(ContentValues content) throws Exception;
    List<Client> getAllClients();
    List<CarModel> getAllCarModels();
    List<Branch> getAllBranchs();
    List<Car> getAllCars();


}
