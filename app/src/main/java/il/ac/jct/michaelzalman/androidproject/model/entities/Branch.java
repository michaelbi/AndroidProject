package il.ac.jct.michaelzalman.androidproject.model.entities;


/**
 * Created by מיכאל on 08/11/2017.
 */

public class Branch
{
    private static int BRANCH_ID=0;
    private Address address;
    private int parkingUnits;
    private int Id;

    public Branch()
    {
        BRANCH_ID++;
    }

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

    public static int getBranchId() {
        return BRANCH_ID;
    }

}
