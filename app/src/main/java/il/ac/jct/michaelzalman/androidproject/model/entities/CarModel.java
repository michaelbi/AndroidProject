package il.ac.jct.michaelzalman.androidproject.model.entities;

/**
 * Created by מיכאל on 08/11/2017.
 */

public class CarModel
{
    enum Gearbox{AUTOMATIC,MANUAL}

    String modelId;
    String brand;
    String modelName;
    int engineCapacity;
    Gearbox gearbox;
    int sitsNumber;

    public String getModelId() {
        return modelId;
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

    public void setModelId(String modelId) {
        this.modelId = modelId;
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
