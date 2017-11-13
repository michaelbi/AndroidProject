package il.ac.jct.michaelzalman.androidproject.model.entities;

/**
 * Created by מיכאל on 08/11/2017.
 */

public class CarModel
{
    public enum Gearbox{AUTOMATIC,MANUAL}

    private String Id;
    private String brand;
    private String modelName;
    private int engineCapacity;
    private Gearbox gearbox;
    private int sitsNumber;

    public String getId() {
        return Id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelName() {
        return modelName;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public int getSitsNumber() {
        return sitsNumber;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public void setSitsNumber(int sitsNumber) {
        this.sitsNumber = sitsNumber;
    }
}
