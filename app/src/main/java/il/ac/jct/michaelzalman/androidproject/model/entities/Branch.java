package il.ac.jct.michaelzalman.androidproject.model.entities;

/**
 * Created by מיכאל on 08/11/2017.
 */

public class Branch
{
    public class Address
    {
        String city;
        String street;
        int number;

        public String getCity() {
            return city;
        }

        public String getStreet() {
            return street;
        }

        public int getNumber() {
            return number;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

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
