package il.ac.jct.michaelzalman.androidproject.model.entities;

import android.content.ContentValues;

import il.ac.jct.michaelzalman.androidproject.model.backend.TakeAndGoConsts;



/**
 * Created by מיכאל on 08/11/2017.
 */

public class Branch
{

    Address address;
    int parkingUnits;
    int branchId;

    public Address getAddress() {
        return address;
    }

    public int getParkingUnits() {
        return parkingUnits;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setParkingUnits(int parkingUnits) {
        this.parkingUnits = parkingUnits;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

}
