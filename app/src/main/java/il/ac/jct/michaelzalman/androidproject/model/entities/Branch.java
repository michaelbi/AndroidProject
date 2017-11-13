package il.ac.jct.michaelzalman.androidproject.model.entities;


/**
 * Created by מיכאל on 08/11/2017.
 */

public class Branch
{

    private Address address;
    private int parkingUnits;
    private int Id;

    public Address getAddress() {
        return address;
    }

    public int getParkingUnits() {
        return parkingUnits;
    }

    public int getId() {
        return Id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setParkingUnits(int parkingUnits) {
        this.parkingUnits = parkingUnits;
    }

    public void setId(int id) {
        this.Id = id;
    }

}
