package il.ac.jct.michaelzalman.androidproject.model.backend;

import android.content.ContentValues;

import java.util.Date;

import il.ac.jct.michaelzalman.androidproject.model.entities.*;

import il.ac.jct.michaelzalman.androidproject.model.entities.*;

/**
 * Created by מיכאל on 13/11/2017.
 */

public class TakeAndGoConsts {

    //region Description Branch and Address const and convertors
    public static class BranchConst{
        public static final String PARKING="parkingUnits";
        public static final String ID ="_id";
    }

    public static class AddressConst{
        public static final String CITY="city";
        public static final String STREET="street";
        public static final String NUMBER="number";

    }


    public static Branch ContentValuesToBranch(ContentValues content){

        Branch branch=new Branch();

        Address a=new Address();
        a.setCity((String) content.get(TakeAndGoConsts.AddressConst.CITY));
        a.setStreet((String) content.get(TakeAndGoConsts.AddressConst.STREET));
        a.setNumber(Integer.parseInt(content.get(TakeAndGoConsts.AddressConst.NUMBER).toString()));
        branch.setAddress(a);
        branch.setParkingUnits(Integer.parseInt(content.get(TakeAndGoConsts.BranchConst.PARKING).toString()));
        branch.setId(content.getAsInteger(BranchConst.ID));

        return branch;

    }
    public static ContentValues branchToContentValues(Branch branch){
        ContentValues content= new ContentValues();
        content.put(TakeAndGoConsts.BranchConst.ID,branch.getId());
        content.put(TakeAndGoConsts.BranchConst.PARKING,branch.getParkingUnits());
        content.put(TakeAndGoConsts.AddressConst.CITY,branch.getAddress().getCity());
        content.put(TakeAndGoConsts.AddressConst.STREET,branch.getAddress().getStreet());
        content.put(TakeAndGoConsts.AddressConst.NUMBER,branch.getAddress().getNumber());

        return content;
    }
    //endregion

    //region Description Client const and convertors
    public static class ClientConst{
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String ID = "_id";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String EMAIL = "email";
        public static final String CREDIT_CARD = "credit_card";
    }

    public static ContentValues ClientToContentValues(Client client){
        ContentValues content= new ContentValues();
        content.put(ClientConst.FIRST_NAME, client.getFirstName());
        content.put(ClientConst.LAST_NAME, client.getLastName());
        content.put(ClientConst.ID, client.getId());
        content.put(ClientConst.PHONE_NUMBER, client.getPhoneNumber());
        content.put(ClientConst.EMAIL, client.getEmail());
        content.put(ClientConst.CREDIT_CARD, client.getCreditCard());
        return content;
    }

    public static Client ContentValuesToClient(ContentValues content){

        Client client = new Client();

        client.setFirstName((String)content.get(ClientConst.FIRST_NAME));
        client.setLastName((String)content.get(ClientConst.LAST_NAME));
        client.setId((String)content.get(ClientConst.ID));
        client.setEmail((String)content.get(ClientConst.EMAIL));
        client.setPhoneNumber((String)content.get(ClientConst.PHONE_NUMBER));
        client.setCreditCard((String)content.get(ClientConst.CREDIT_CARD));

        return client;

    }
    //endregion

    //region Description CarModel const and convertors
    public static class CarModelConst{
        public static final String ID = "_id";
        public static final String BRAND = "brand";
        public static final String MODEL_NAME = "model_name";
        public static final String ENGINE_CAPACITY= "engine_capacity";
        public static final String GEAR_BOX= "gear_box";
        public static final String SITS_NUMBER = "sits_number";
    }

    public static ContentValues CarModelToContentValues(CarModel carModel){
        ContentValues content= new ContentValues();
        content.put(CarModelConst.BRAND, carModel.getBrand());
        content.put(CarModelConst.ENGINE_CAPACITY, carModel.getEngineCapacity());
        content.put(CarModelConst.GEAR_BOX, String.valueOf(carModel.getGearbox()));
        content.put(CarModelConst.ID, carModel.getId());
        content.put(CarModelConst.MODEL_NAME, carModel.getModelName());
        content.put(CarModelConst.SITS_NUMBER, carModel.getSitsNumber());
        return content;
    }


    public static CarModel ContentValuesToCarModel(ContentValues content){

        CarModel carModel = new CarModel();

//        carModel.setId((String)content.get(CarModelConst.ID));
        carModel.setBrand((String)content.get(CarModelConst.BRAND));
        carModel.setEngineCapacity(Integer.parseInt(content.get(CarModelConst.ENGINE_CAPACITY).toString()));
        carModel.setGearbox(CarModel.Gearbox.valueOf(content.get(CarModelConst.GEAR_BOX).toString()));
        carModel.setModelName((String)content.get(CarModelConst.MODEL_NAME));
        carModel.setSitsNumber(Integer.parseInt(content.get(CarModelConst.SITS_NUMBER).toString()));
        return carModel;

    }
    //endregion

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

        car.setCarBranchId(Integer.parseInt(content.get(CarConst.CAR_BRANCH_ID).toString()));
        car.setCarModel(content.get(CarConst.CAR_MODEL).toString());
        car.setId(content.get(CarConst.ID).toString());
        car.setKilometers(Integer.parseInt(content.get(CarConst.KILOMETERS).toString()));

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
