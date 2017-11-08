package il.ac.jct.michaelzalman.androidproject.model.entities;

/**
 * Created by מיכאל on 08/11/2017.
 */

import java.util.Date;

public class Order
{
    String clientId;
    boolean open;
    String carId;
    Date startHiring;
    Date endHiring;
    int startKilometer;
    int endKilometer;
    boolean fuel;
    float filedFuel;
    float totalChargeSum;
    String Id;

    public String getClientId() {
        return clientId;
    }

    public boolean isOpen() {
        return open;
    }

    public String getCarId() {
        return carId;
    }

    public Date getStartHiring() {
        return startHiring;
    }

    public Date getEndHiring() {
        return endHiring;
    }

    public int getStartKilometer() {
        return startKilometer;
    }

    public int getEndKilometer() {
        return endKilometer;
    }

    public boolean isFuel() {
        return fuel;
    }

    public float getFiledFuel() {
        return filedFuel;
    }

    public float getTotalChargeSum() {
        return totalChargeSum;
    }

    public String getId() {
        return Id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setStartHiring(Date startHiring) {
        this.startHiring = startHiring;
    }

    public void setEndHiring(Date endHiring) {
        this.endHiring = endHiring;
    }

    public void setStartKilometer(int startKilometer) {
        this.startKilometer = startKilometer;
    }

    public void setEndKilometer(int endKilometer) {
        this.endKilometer = endKilometer;
    }

    public void setFuel(boolean fuel) {
        this.fuel = fuel;
    }

    public void setFiledFuel(float filedFuel) {
        this.filedFuel = filedFuel;
    }

    public void setTotalChargeSum(float totalChargeSum) {
        this.totalChargeSum = totalChargeSum;
    }

    public void setId(String id) {
        Id = id;
    }
}
