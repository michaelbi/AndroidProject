package il.ac.jct.michaelzalman.androidproject.model.DataSource;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

import il.ac.jct.michaelzalman.androidproject.model.backend.IBackend;
import il.ac.jct.michaelzalman.androidproject.model.entities.Branch;
import il.ac.jct.michaelzalman.androidproject.model.entities.Car;
import il.ac.jct.michaelzalman.androidproject.model.entities.CarModel;
import il.ac.jct.michaelzalman.androidproject.model.entities.Client;
/**
 * DB implemented with ArrayLists
 */


public class ArrayListDB  implements IBackend {
    private static ArrayList<Client> clients;
    private static ArrayList<Car> cars;
    private static ArrayList<CarModel> carModels;
    private static ArrayList<Branch> branches;

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public static ArrayList<Car> getCars() {
        return cars;
    }

    public static ArrayList<CarModel> getCarModels() {
        return carModels;
    }

    public static ArrayList<Branch> getBranches() {
        return branches;
    }

    public void initializeLists() {
        clients = new ArrayList<>();
        cars = new ArrayList<>();
        carModels = new ArrayList<>();
        branches = new ArrayList<>();
    }

    @Override
    public boolean isClientExist(ContentValues client) {
        for (Client c:clients) {
                if(c.getId() == (String) client.get("id"))
                    return true;
        }
        return false;
    }

    @Override
    public void addClient(ContentValues client) {

    }

    @Override
    public void addCarModel(ContentValues carModel) {

    }

    @Override
    public void addCar(ContentValues car) {

    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    @Override
    public List<CarModel> getAllCarModels() {
        return carModels;
    }

    @Override
    public List<Branch> getAllBranchs() {
        return branches;
    }

    @Override
    public List<Car> getAllCars() {
        return cars;
    }
}
