package il.ac.jct.michaelzalman.androidproject.model.DataSource;

import android.content.ContentValues;

import java.util.List;

import il.ac.jct.michaelzalman.androidproject.model.backend.IDBManager;
import il.ac.jct.michaelzalman.androidproject.model.entities.Branch;
import il.ac.jct.michaelzalman.androidproject.model.entities.Car;
import il.ac.jct.michaelzalman.androidproject.model.entities.CarModel;
import il.ac.jct.michaelzalman.androidproject.model.entities.Client;

/**
 * Created by מיכאל on 08/12/2017.
 */

public class MySQL_DBManager implements IDBManager {

    private static String WEB_URL="http://mbitan.vlab.jct.ac.il/DBmanage/";

    @Override
    public boolean isClientExist(ContentValues client) {


        return false;
    }

    @Override
    public void addClient(ContentValues client) throws Exception {
        try
        {
            PHPtools.POST(WEB_URL + "addClient.php", client);
        }
        catch (Exception e)
        {
            System.out.println("error "+e.getMessage());
        }

    }

    @Override
    public void addCarModel(ContentValues carModel) throws Exception {

    }

    @Override
    public void addCar(ContentValues car) throws Exception {

    }

    @Override
    public void addBranch(ContentValues content) throws Exception {

    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public List<CarModel> getAllCarModels() {
        return null;
    }

    @Override
    public List<Branch> getAllBranchs() {
        return null;
    }

    @Override
    public List<Car> getAllCars() {
        return null;
    }
}
