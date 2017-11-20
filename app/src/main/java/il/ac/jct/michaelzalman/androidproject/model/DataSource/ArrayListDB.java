package il.ac.jct.michaelzalman.androidproject.model.DataSource;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

import il.ac.jct.michaelzalman.androidproject.model.backend.IDBManager;
import il.ac.jct.michaelzalman.androidproject.model.backend.TakeAndGoConsts;
import il.ac.jct.michaelzalman.androidproject.model.entities.Branch;
import il.ac.jct.michaelzalman.androidproject.model.entities.Car;
import il.ac.jct.michaelzalman.androidproject.model.entities.CarModel;
import il.ac.jct.michaelzalman.androidproject.model.entities.Client;
/**
 * DB implemented with ArrayLists
 */


public class ArrayListDB  implements IDBManager {
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Car> cars = new ArrayList<>();
    private static ArrayList<CarModel> carModels = new ArrayList<>();
    private static ArrayList<Branch> branches = new ArrayList<>();



    //region Description static incremental id's
    public static int getCarId() {
        return carId++;
    }

    public static int getCarModelId() {
        return carModelId++;
    }

    public static int getBranchId() {
        return branchId++;
    }
    private static int carId;
    private static int carModelId;
    private static int branchId;

    static {
        carId = 1000;
        carModelId = 2000;
        branchId = 3000;
    }
    //endregion


    //region Description getters
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
    //endregion

    //region Description IDBManager override methods
    @Override
    public boolean isClientExist(ContentValues client) {
        if (clients.isEmpty())
            return false;
        for (Client c : clients) {
            if (c.getId() == (String) client.get(TakeAndGoConsts.ClientConst.ID))
                return true;
        }
        return false;
    }

    @Override
    public void addClient(ContentValues client) throws Exception {
        if (isClientExist(client))
            throw new Exception("trying to add client that allready exist in DB.");
        clients.add(TakeAndGoConsts.ContentValuesToClient(client));
    }

    @Override
    public void addCarModel(ContentValues carModel) throws Exception {
        if (isCarModelExist(carModel))
            throw new Exception("trying to add car model that allready exist in DB.");
        carModels.add(TakeAndGoConsts.ContentValuesToCarModel(carModel));
    }

    @Override
    public void addCar(ContentValues car) throws Exception {
        if (isCarExist(car))
            throw new Exception("trying to add car that allready exist in DB.");
        cars.add(TakeAndGoConsts.ContentValuesToCar(car));
    }

    @Override
    public void addBranch(ContentValues branch) throws Exception {
        if (isBranchExist(branch))
            throw new Exception("trying to add branch that allready exist in DB.");
        branches.add(TakeAndGoConsts.ContentValuesToBranch(branch));
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
    //endregion

    //region Description private existance methods
    private boolean isCarModelExist(ContentValues carModel) {
        if (carModels.isEmpty())
            return false;
        for (CarModel c : carModels) {
            if (c.getId() == carModel.get(TakeAndGoConsts.CarModelConst.ID))
                return true;
        }
        return false;
    }

        private boolean isBranchExist(ContentValues branch) {
        if(branches.isEmpty())
            return false;
        for (Branch b :branches) {
            if(b.getId() == (int)branch.get(TakeAndGoConsts.CarModelConst.ID))
                return true;
        }
        return false;
    }
    private boolean isCarExist(ContentValues car) {
        if (cars.isEmpty())
            return false;
        for (Car c : cars) {
            if (c.getId() ==  car.get(TakeAndGoConsts.CarConst.ID))
                return true;
        }
        return false;
    }
    //endregion

}
