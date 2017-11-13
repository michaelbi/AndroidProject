package il.ac.jct.michaelzalman.androidproject.model.backend;

import android.content.ContentValues;

import il.ac.jct.michaelzalman.androidproject.model.entities.Address;
import il.ac.jct.michaelzalman.androidproject.model.entities.Branch;

/**
 * Created by מיכאל on 13/11/2017.
 */

public class TakeAndGoConsts {

    public static class BranchConst{
        public static final String PARKING="parkingUnits";
        public static final String ID="_id";
    }

    public static class AddressConst{
        public static final String CITY="city";
        public static final String STREET="street";
        public static final String NUMBER="number";

    }

    public static ContentValues toContentValues(Branch branch){
        ContentValues content= new ContentValues();
        Address address;

        content.put(TakeAndGoConsts.BranchConst.ID,branch.getBranchId());
        content.put(TakeAndGoConsts.BranchConst.PARKING,branch.getParkingUnits());
        content.put(TakeAndGoConsts.AddressConst.CITY,branch.getAddress().getCity());
        content.put(TakeAndGoConsts.AddressConst.STREET,branch.getAddress().getStreet());
        content.put(TakeAndGoConsts.AddressConst.NUMBER,branch.getAddress().getNumber());

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
