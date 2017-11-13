package il.ac.jct.michaelzalman.androidproject.model.entities;

/**
 * Car class
 */

public class Car
{
    private String carModel;
    private int carBranchId;
    private int kilometers;
    private String id;

    public String getCarModel() {
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

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarBranchId(int carBranchId) {
        this.carBranchId = carBranchId;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public void setId(String id) {
        this.id = id;
    }
}
