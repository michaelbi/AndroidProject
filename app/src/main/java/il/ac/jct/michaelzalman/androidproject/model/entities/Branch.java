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

    public static ContentValues toContentValues(Branch branch){
        ContentValues content= new ContentValues();
        Address address;

        content.put(TakeAndGoConsts.BranchConst.ID,branch.branchId);
        content.put(TakeAndGoConsts.BranchConst.PARKING,branch.parkingUnits);
        content.put(TakeAndGoConsts.AddressConst.CITY,branch.address.city);
        content.put(TakeAndGoConsts.AddressConst.STREET,branch.address.street);
        content.put(TakeAndGoConsts.AddressConst.NUMBER,branch.address.number);

        return content;
    }

    public static Branch ContentValuesToBranch(ContentValues content){

        Branch branch=new Branch();

        Address a=new Address();
        a.setCity((String) content.get(TakeAndGoConsts.AddressConst.CITY));
        a.setStreet((String) content.get(TakeAndGoConsts.AddressConst.STREET));
        a.setNumber((int) content.get(TakeAndGoConsts.AddressConst.NUMBER));

        branch.setAddress(a);
        branch.setBranchId((int)content.get(TakeAndGoConsts.BranchConst.ID));
        branch.setParkingUnits((int) content.get(TakeAndGoConsts.BranchConst.PARKING));


        return branch;

    }

}
