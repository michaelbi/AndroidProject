package il.ac.jct.michaelzalman.androidproject.model.entities;

/**
 * Created by מיכאל on 08/11/2017.
 */

public class Car
{
    CarModel carModel;
    int carBranchId;
    int kilometers;
    String id;

    public CarModel getCarModel() {
        return carModel;
    }

    public int getCarBranchId() {
        return carBranchId;
    }

    public int getKilometers() {
        return kilometers;
    }

    public String getId() {
        return id;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public void setCarBranchId(int carBranchId) {
        this.carBranchId = carBranchId;
    }

    public void setKilometers(int kilometers) {
        kilometers = kilometers;
    }

    public void setId(String id) {
        this.id = id;
    }
}
