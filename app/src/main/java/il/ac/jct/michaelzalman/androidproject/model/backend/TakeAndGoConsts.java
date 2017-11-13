package il.ac.jct.michaelzalman.androidproject.model.backend;

import android.content.ContentValues;

import java.util.Date;

import il.ac.jct.michaelzalman.androidproject.model.entities.*;


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

    //region Car ContentValues
    public static class CarConst{
        public static final String CAR_MODEL="carModel";
        public static final String CAR_BRANCH_ID="carBranchId";
        public static final String KILOMETERS="kilometers";
        public static final String ID="_id";
    }

    public static ContentValues carToContentValues(Car car)
    {
        ContentValues content= new ContentValues();

        content.put(CarConst.CAR_MODEL,car.getCarModel());
        content.put(CarConst.CAR_BRANCH_ID,car.getCarBranchId());
        content.put(CarConst.KILOMETERS,car.getKilometers());
        content.put(CarConst.ID,car.getId());

        return content;
    }

    public static Car ContentValuesToCar(ContentValues content){

        Car car=new Car();

        car.setCarBranchId((int)content.get(CarConst.CAR_BRANCH_ID));
        car.setCarModel((String) content.get(CarConst.CAR_MODEL));
        car.setId((String) content.get(CarConst.ID));
        car.setKilometers((int)content.get(CarConst.KILOMETERS));

        return car;

    }
    //endregion

    //region Order ContentValues
    public static class OrderConst
    {
        public static final String CLIENT_ID="clientId";
        public static final String OPEN="open";
        public static final String CAR_ID="carId";
        public static final String START_HIRING="startHiring";
        public static final String END_HIRING="endHiring";
        public static final String START_KILOMETER="startKilometer";
        public static final String END_KILOMETER="endKilometer";
        public static final String FUEL="fuel";
        public static final String FILED_FUEL="filedFuel";
        public static final String TOTAL_CHARGE_SUM="totalChargeSum";
        public static final String ID="_id";
    }

    public static ContentValues orderToContentValues(Order order)
    {
        ContentValues content=new ContentValues();
        content.put(OrderConst.CAR_ID,order.getCarId());
        content.put(OrderConst.CLIENT_ID,order.getClientId());
        content.put(OrderConst.END_HIRING,String.valueOf(order.getEndHiring()));
        content.put(OrderConst.START_HIRING,String.valueOf(order.getStartHiring()));
        content.put(OrderConst.START_KILOMETER,order.getStartKilometer());
        content.put(OrderConst.END_KILOMETER,order.getEndKilometer());
        content.put(OrderConst.FUEL,order.isFuel());
        content.put(OrderConst.FILED_FUEL,order.getFiledFuel());
        content.put(OrderConst.TOTAL_CHARGE_SUM,order.getTotalChargeSum());
        content.put(OrderConst.ID,order.getId());

        return content;
    }

    public static Order ContentValuesToOrder(ContentValues content)
    {
        Order order = new Order();
        order.setId((String) content.get(OrderConst.ID));
        order.setCarId((String) content.get(OrderConst.CAR_ID));
        order.setClientId((String) content.get(OrderConst.CLIENT_ID));
        order.setStartHiring(new Date(content.get(OrderConst.START_HIRING).toString()));
        order.setEndHiring(new Date(content.get(OrderConst.END_HIRING).toString()));
        order.setStartKilometer((int) content.get(OrderConst.START_KILOMETER));
        order.setEndKilometer((int) content.get(OrderConst.END_KILOMETER));
        order.setFuel((Boolean) content.get(OrderConst.FUEL));
        order.setFiledFuel((Float) content.get(OrderConst.FILED_FUEL));
        order.setTotalChargeSum((Float) content.get(OrderConst.TOTAL_CHARGE_SUM));

        return order;
    }

    //endregion
}
